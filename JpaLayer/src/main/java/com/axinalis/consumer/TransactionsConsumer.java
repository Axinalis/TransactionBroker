package com.axinalis.consumer;

import com.axinalis.dao.TransactionDao;
import com.axinalis.model.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionsConsumer {

    private Logger log = LoggerFactory.getLogger(TransactionsConsumer.class);
    private TransactionDao dao;
    private ObjectMapper mapper;

    public TransactionsConsumer(@Autowired TransactionDao dao,
                                @Autowired ObjectMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @KafkaListener(topics = "${topic.transaction}")
    public void listen(String transaction) throws JsonProcessingException {
        Transaction message = mapper.readValue(transaction, Transaction.class);
        log.info("A request for creating new transaction. ClientId: {}, Bank: {}, OrderType: {}",
                message.getClientId(), message.getBank(), message.getOrderType().toString());

        dao.createTransaction(message);
    }

}