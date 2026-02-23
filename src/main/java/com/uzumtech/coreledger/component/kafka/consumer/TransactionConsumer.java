package com.uzumtech.coreledger.component.kafka.consumer;

import com.uzumtech.coreledger.component.kafka.publisher.TransactionEventPublisher;
import com.uzumtech.coreledger.constant.KafkaConstants;
import com.uzumtech.coreledger.constant.enums.TransactionStatus;
import com.uzumtech.coreledger.dto.event.TransactionEvent;
import com.uzumtech.coreledger.dto.event.TransactionResultEvent;
import com.uzumtech.coreledger.exception.kafka.transients.TransientException;
import com.uzumtech.coreledger.service.LedgerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionConsumer implements EventConsumer<TransactionEvent> {

    private final LedgerService ledgerService;
    private final TransactionEventPublisher transactionEventPublisher;


    @RetryableTopic(attempts = "5", backOff = @BackOff(delay = 5000), include = {TransientException.class}, numPartitions = "3", replicationFactor = "1")
    @KafkaListener(topics = KafkaConstants.TRANSACTIONS_TOPIC, groupId = KafkaConstants.TRANSACTIONS_GROUP_ID)
    public void listen(@Payload @Valid TransactionEvent event) {

        log.info("Event processing: {}", event);

        try {

            ledgerService.performTransaction(event);

            TransactionResultEvent resultEvent = TransactionResultEvent.of(event.transactionId(), TransactionStatus.SUCCESS);
            transactionEventPublisher.sendTransactionResult(resultEvent);

        } catch (DataAccessException ex) {

            log.info("DB Error: {}", ex.getMessage());

            throw new TransientException(ex);
        }

    }

    @DltHandler
    public void dltHandler(TransactionEvent event, @Header(KafkaHeaders.EXCEPTION_MESSAGE) String exceptionMessage) {

        TransactionResultEvent resultEvent = new TransactionResultEvent(event.transactionId(), TransactionStatus.FAILED, exceptionMessage);
        transactionEventPublisher.sendTransactionResult(resultEvent);

    }
}
