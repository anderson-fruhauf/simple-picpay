package tech.lumos.picpay.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import tech.lumos.picpay.controller.dto.TransferDTO;
import tech.lumos.picpay.entity.Transfer;
import tech.lumos.picpay.entity.Wallet;
import tech.lumos.picpay.exception.InsufficientBalanceException;
import tech.lumos.picpay.exception.TransferNotAllowedForWalletTypeException;
import tech.lumos.picpay.exception.TransferNotAuthorizedException;
import tech.lumos.picpay.exception.WalletNotFoundException;
import tech.lumos.picpay.repository.TransferRepository;
import tech.lumos.picpay.repository.WalletRepository;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class TransferService {
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;

    @Transactional
    public Transfer transfer(@NotNull TransferDTO transferDTO) {
        var sender = walletRepository.findById(transferDTO.payer()).orElseThrow(WalletNotFoundException::new);
        var receiver = walletRepository.findById(transferDTO.payee()).orElseThrow(WalletNotFoundException::new);

        validateTransfer(transferDTO, sender);

        sender.debit(transferDTO.value());
        receiver.credit(transferDTO.value());

        Transfer transfer = new Transfer(sender, receiver, transferDTO.value());
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));
        return transferResult;
    }

    private void validateTransfer(TransferDTO transferDTO, @NotNull Wallet sender) {
        if (!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForWalletTypeException();
        }

        if (!sender.isBalanceEqualOrGreaterThan(transferDTO.value())) {
            throw new InsufficientBalanceException();
        }

        if (authorizationService.isAuthorized(transferDTO)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
