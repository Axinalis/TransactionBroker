package com.axinalis.consumer;

import com.axinalis.model.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class TransactionsConsumer {

    private ObjectMapper mapper;
    private final List<Transaction> list;

    public TransactionsConsumer(@Autowired ObjectMapper mapper) {
        this.mapper = mapper;
        list = new LinkedList<>();
    }

    @KafkaListener(topics = "${TRANSACTIONS_TOPIC}", groupId = "${GROUP_ID}")
    public void listen(String message) throws JsonProcessingException {
        synchronized (list){
            list.add(mapper.readValue(message, Transaction.class));
        }
    }

    public List<Transaction> getMessages(){
        return list;
    }

}
