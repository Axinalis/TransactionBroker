package com.axinalis.consumer;

import com.axinalis.entity.TransactionEntity;
import com.axinalis.model.Transaction;
import com.axinalis.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionsConsumer {

    private TransactionRepository repository;

    public TransactionsConsumer(@Autowired TransactionRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "transactions")
    public void listen(Transaction message){
        TransactionEntity transactionToDb = new TransactionEntity();
        transactionToDb.setBank(message.getBank());
        transactionToDb.setClientId(message.getClientId());
        transactionToDb.setOrderType(message.getOrderType().toString());
        transactionToDb.setCreatedAt(message.getCreatedAt());
        transactionToDb.setQuantity(message.getQuantity());
        transactionToDb.setPrice(message.getPrice());

        transactionToDb.setTotalCost(message.getPrice()* message.getQuantity());

        repository.save(transactionToDb);
    }

}