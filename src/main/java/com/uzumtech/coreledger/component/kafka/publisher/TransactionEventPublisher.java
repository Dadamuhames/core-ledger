package com.uzumtech.coreledger.component.kafka.publisher;

import com.uzumtech.coreledger.constant.KafkaConstants;
import com.uzumtech.coreledger.dto.event.TransactionEvent;
import com.uzumtech.coreledger.dto.event.TransactionResultEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;


    public void sendTransactionResult(TransactionResultEvent event) {
        kafkaTemplate.send(KafkaConstants.TRANSACTIONS_RESULT_TOPIC, event);
    }

    public void publishTransactionEvent(TransactionEvent event) {
        kafkaTemplate.send(KafkaConstants.TRANSACTIONS_TOPIC, event);
    }
}
