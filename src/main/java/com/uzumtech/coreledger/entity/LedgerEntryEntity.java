package com.uzumtech.coreledger.entity;

import com.uzumtech.coreledger.constant.enums.Currency;
import com.uzumtech.coreledger.constant.enums.EntryType;
import com.uzumtech.coreledger.constant.enums.TransactionType;
import com.uzumtech.coreledger.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ledger_entries")
public class LedgerEntryEntity extends BaseEntity {

    @Column(nullable = false)
    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", updatable = false, foreignKey = @ForeignKey(name = "fk_account"), nullable = false)
    private AccountEntity account;

    @Positive
    @Column(nullable = false)
    private Long amount;

    @PositiveOrZero
    @Column(nullable = false)
    private Long actualBalance;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "currency", nullable = false)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "entryType", nullable = false)
    private EntryType entryType;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "transaction_type", nullable = false)
    private TransactionType transactionType;
}
