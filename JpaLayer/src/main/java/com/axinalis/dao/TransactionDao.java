package com.axinalis.dao;

import com.axinalis.entity.TransactionEntity;
import com.axinalis.model.Transaction;
import com.axinalis.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionDao {

    private Logger log = LoggerFactory.getLogger(TransactionDao.class);
    private TransactionRepository repository;

    public TransactionDao(@Autowired TransactionRepository repository) {
        this.repository = repository;
    }

    public long createTransaction(Transaction message){
        log.info("New transaction is creating. ClientId: {}, Bank: {}, OrderType: {}",
                message.getClientId(), message.getBank(), message.getOrderType().toString());
        TransactionEntity transactionToDb = new TransactionEntity();

        transactionToDb.setBank(message.getBank());
        transactionToDb.setClientId(message.getClientId());
        transactionToDb.setOrderType(message.getOrderType().toString());
        transactionToDb.setCreatedAt(message.getCreatedAt());
        transactionToDb.setQuantity(message.getQuantity());
        transactionToDb.setPrice(message.getPrice());
        transactionToDb.setTotalCost(message.getPrice() * message.getQuantity());

        return repository
                .save(transactionToDb)
                .getClientId();
    }
}
