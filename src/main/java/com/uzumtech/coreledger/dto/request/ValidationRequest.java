package com.uzumtech.coreledger.dto.request;


import com.uzumtech.coreledger.constant.enums.Currency;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ValidationRequest(@NotNull UUID amsAccountId, @NotNull Long amount, @NotNull Currency currency) {
}
