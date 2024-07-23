package tech.lumos.picpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lumos.picpay.entity.WalletType;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
