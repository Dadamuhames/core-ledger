package com.uzumtech.coreledger.mapper;

import com.uzumtech.coreledger.dto.response.AccountBalanceResponse;
import com.uzumtech.coreledger.dto.response.AccountResponse;
import com.uzumtech.coreledger.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    AccountBalanceResponse entityToBalanceResponse(AccountEntity entity);


    @Mapping(target = "ledgerAccountId", source = "entity.id")
    AccountResponse entityToResponse(AccountEntity entity);
}
