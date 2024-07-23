package tech.lumos.picpay.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import tech.lumos.picpay.entity.WalletType;
import tech.lumos.picpay.repository.WalletTypeRepository;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final WalletTypeRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(WalletType.Enum.values()).forEach(walletType-> repository.save(walletType.get()));
    }
}
