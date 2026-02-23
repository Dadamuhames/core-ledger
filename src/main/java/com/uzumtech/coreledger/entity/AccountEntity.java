package com.uzumtech.coreledger.entity;

import com.uzumtech.coreledger.constant.enums.AccountStatus;
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
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class AccountEntity extends BaseEntity {

    @Column(nullable = false)
    private UUID clientId;

    @Column(nullable = false)
    private UUID amsAccountId;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "currency", nullable = false)
    private Currency currency;

    @PositiveOrZero
    @Column(nullable = false)
    private Long actualBalance;

    @Column(nullable = false)
    private Long reservedBalance;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "status", nullable = false)
    private AccountStatus status;

    @UpdateTimestamp
    @Column(insertable = false, nullable = false)
    OffsetDateTime updatedAt;
}
