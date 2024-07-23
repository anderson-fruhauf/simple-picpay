package tech.lumos.picpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.lumos.picpay.entity.Transfer;

import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
