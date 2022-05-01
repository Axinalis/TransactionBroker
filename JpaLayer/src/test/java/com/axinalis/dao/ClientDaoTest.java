package com.axinalis.dao;

import com.axinalis.entity.ClientEntity;
import com.axinalis.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = ClientDao.class)
public class ClientDaoTest {

    @Autowired
    private ClientDao dao;
    @MockBean
    private ClientRepository repository;

    @Test
    public void createUserTest(){
        String email = "someMail@domen.com";
        ClientEntity messageToDb = new ClientEntity();
        messageToDb.setEmail(email);
        ClientEntity messageFromDb = new ClientEntity();
        messageFromDb.setEmail(email);
        messageFromDb.setClientId(1L);

        when(repository.save(messageToDb)).thenReturn(messageFromDb);
        dao.createUser(email);

        verify(repository, times(1)).save(messageToDb);
    }

}
