package tech.lumos.picpay.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tech.lumos.picpay.entity.Wallet;
import tech.lumos.picpay.entity.WalletType;

import java.math.BigDecimal;

public record CreateWalletDTO(@NotBlank String fullName,
                              @NotBlank String cpfCnpj,
                              @NotBlank String email,
                              @NotBlank String password,
                              @NotNull WalletType.Enum walletType) {

    public Wallet toWallet(){
        return new Wallet(
            fullName,
                cpfCnpj,
                email,
                password,
                walletType.get()
        );
    }
}
