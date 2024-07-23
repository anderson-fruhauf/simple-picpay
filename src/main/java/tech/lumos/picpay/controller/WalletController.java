package tech.lumos.picpay.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.lumos.picpay.controller.dto.CreateWalletDTO;
import tech.lumos.picpay.entity.Wallet;
import tech.lumos.picpay.service.WalletService;

@RestController
@RequestMapping("/wallets")
@AllArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<Wallet> create(@RequestBody @Valid CreateWalletDTO createWalletDTO){
        Wallet response = walletService.createWallet(createWalletDTO);
        return ResponseEntity.ok(response);
    }
}
