package sc.pr.entities_repositories_and_services.transaction;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@Table(name = "transactions")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cell_phone", nullable = false)
    private String cellPhone;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "transactional_id", nullable = false)
    private UUID transactionalId;

    @Column(name = "value", nullable = false)
    private Integer value;

    @Column(name = "transaction_date", nullable = false, updatable = false)
    private Timestamp transactionDate;
}