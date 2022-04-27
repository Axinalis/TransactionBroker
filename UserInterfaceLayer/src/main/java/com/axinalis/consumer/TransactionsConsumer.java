package com.axinalis.consumer;

import com.axinalis.model.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class TransactionsConsumer {

    private final List<Transaction> list;

    public TransactionsConsumer() {
        list = new LinkedList<>();
    }

    @KafkaListener(topics = "${TRANSACTIONS_TOPIC}", groupId = "${GROUP_ID}")
    public void listen(Transaction message) {
        synchronized (list){
            list.add(message);
        }
    }

    public List<Transaction> getMessages(){
        return list;
    }

}
