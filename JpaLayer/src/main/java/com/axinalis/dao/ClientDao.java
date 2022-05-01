package com.axinalis.dao;

import com.axinalis.entity.ClientEntity;
import com.axinalis.model.Client;
import com.axinalis.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDao {

    private Logger log = LoggerFactory.getLogger(ClientDao.class);
    private ClientRepository repository;

    public ClientDao(@Autowired ClientRepository repository) {
        this.repository = repository;
    }

    public Client createUser(String email){
        log.info("New client with email \"{}\" is creating", email);
        ClientEntity clientToDb = new ClientEntity();

        clientToDb.setEmail(email);
        long clientId = repository
                .save(clientToDb)
                .getClientId();

        Client client = new Client();
        client.setEmail(email);
        client.setClientId(clientId);

        return client;
    }

}
