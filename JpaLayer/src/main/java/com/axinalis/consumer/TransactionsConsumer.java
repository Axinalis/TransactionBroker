package com.axinalis.consumer;

import com.axinalis.entity.TransactionEntity;
import com.axinalis.model.Transaction;
import com.axinalis.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionsConsumer {

    private TransactionRepository repository;
    private ObjectMapper mapper;

    public TransactionsConsumer(@Autowired TransactionRepository repository,
                                @Autowired ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @KafkaListener(topics = "transactions")
    public void listen(ConsumerRecord<?, ?> message){
        Transaction transactionModel = mapper.convertValue(message.value(), Transaction.class);
        TransactionEntity transactionToDb = new TransactionEntity();

        transactionToDb.setBank(transactionModel.getBank());
        transactionToDb.setClientId(transactionModel.getClientId());
        transactionToDb.setOrderType(transactionModel.getOrderType().toString());
        transactionToDb.setCreatedAt(transactionModel.getCreatedAt());
        transactionToDb.setQuantity(transactionModel.getQuantity());
        transactionToDb.setPrice(transactionModel.getPrice());
        transactionToDb.setTotalCost(transactionModel.getPrice() * transactionModel.getQuantity());

        repository.save(transactionToDb);
    }

}