package com.uzumtech.coreledger;

import com.uzumtech.coreledger.component.kafka.publisher.TransactionEventPublisher;
import com.uzumtech.coreledger.constant.enums.Currency;
import com.uzumtech.coreledger.constant.enums.TransactionType;
import com.uzumtech.coreledger.dto.event.TransactionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class CoreLedgerApplication implements CommandLineRunner {

    private final TransactionEventPublisher transactionEventPublisher;

    public static void main(String[] args) {
        SpringApplication.run(CoreLedgerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        transactionEventPublisher.publishTransactionEvent(new TransactionEvent(1L, 2L, 3L, TransactionType.P2P, 200000L, 200L, Currency.UZS));

    }
}
