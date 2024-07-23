package tech.lumos.picpay.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_wallet_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class WalletType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @AllArgsConstructor
    public enum Enum {
        USER(1L, "user"),
        MERCHANT(2L, "merchant");

        private Long id;
        private String description;

        public WalletType get() {
            return new WalletType(id, description);
        }
    }
}
