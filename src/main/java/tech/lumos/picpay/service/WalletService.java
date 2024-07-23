package tech.lumos.picpay.service;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import tech.lumos.picpay.controller.dto.CreateWalletDTO;
import tech.lumos.picpay.entity.Wallet;
import tech.lumos.picpay.exception.WalletDataAlreadyExistsException;
import tech.lumos.picpay.repository.WalletRepository;

@AllArgsConstructor
@Service
public class WalletService {
    private WalletRepository walletRepository;

    public Wallet createWallet(CreateWalletDTO dto){
        var wallet = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());

        if(wallet.isPresent()){
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }

        return walletRepository.save(dto.toWallet());
    }
}
