package com.uzumtech.coreledger.mapper;

import com.uzumtech.coreledger.dto.event.TransactionEvent;
import com.uzumtech.coreledger.entity.AccountEntity;
import com.uzumtech.coreledger.entity.LedgerEntryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LedgerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "account", source = "account")
    @Mapping(target = "currency", source = "event.currency")
    @Mapping(target = "entryType", constant = "DEBIT")
    @Mapping(target = "amount", expression = "java(calculateFullDebitAmount(event))")
    LedgerEntryEntity eventToDebitEntity(TransactionEvent event, AccountEntity account);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "account", source = "account")
    @Mapping(target = "currency", source = "event.currency")
    @Mapping(target = "entryType", constant = "CREDIT")
    LedgerEntryEntity eventToCreditEntity(TransactionEvent event, AccountEntity account);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "account", source = "account")
    @Mapping(target = "currency", source = "event.currency")
    @Mapping(target = "entryType", constant = "CREDIT")
    @Mapping(target = "amount", source = "event.fee")
    LedgerEntryEntity eventToFeeEntity(TransactionEvent event, AccountEntity account);


    default Long calculateFullDebitAmount(TransactionEvent event) {
        return event.amount() + event.fee();
    }
}
