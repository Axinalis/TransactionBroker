package com.axinalis.dao;

import com.axinalis.entity.TransactionEntity;
import com.axinalis.model.Transaction;
import com.axinalis.model.TransactionType;
import com.axinalis.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = TransactionDao.class)
public class TransactionDaoTest {

    @Autowired
    private TransactionDao dao;
    @MockBean
    private TransactionRepository repository;

    @Test
    public void createTransactionTest(){
        LocalDateTime dateTime = LocalDateTime.now();
        Transaction message = new Transaction();
        message.setBank("SomeBank");
        message.setOrderType(TransactionType.INCOME);
        message.setPrice(10.99);
        message.setQuantity(50);
        message.setCreatedAt(dateTime);
        message.setClientId(2L);
        TransactionEntity messageToDb = new TransactionEntity();
        messageToDb.setBank("SomeBank");
        messageToDb.setOrderType(TransactionType.INCOME.toString());
        messageToDb.setPrice(10.99);
        messageToDb.setQuantity(50);
        messageToDb.setCreatedAt(dateTime);
        messageToDb.setClientId(2L);
        messageToDb.setTotalCost(10.99*50);

        when(repository.save(messageToDb)).thenReturn(messageToDb);
        dao.createTransaction(message);

        verify(repository, times(1)).save(messageToDb);
    }

}
