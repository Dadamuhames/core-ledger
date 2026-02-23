package com.uzumtech.coreledger.entity;

import com.uzumtech.coreledger.constant.enums.HoldStatus;
import com.uzumtech.coreledger.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "holds")
public class HoldEntity extends BaseEntity {

    @Column(nullable = false)
    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", updatable = false, foreignKey = @ForeignKey(name = "fk_account"), nullable = false)
    private AccountEntity account;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "status", nullable = false)
    private HoldStatus status;

    @Positive
    @Column(nullable = false)
    private Long amount;

    @Column(insertable = false, nullable = false, updatable = false)
    OffsetDateTime expiresAt;

    @UpdateTimestamp
    @Column(insertable = false, nullable = false)
    OffsetDateTime updatedAt;
}
