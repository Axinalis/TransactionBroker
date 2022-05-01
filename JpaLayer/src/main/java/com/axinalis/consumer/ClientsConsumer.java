package com.axinalis.consumer;

import com.axinalis.dao.ClientDao;
import com.axinalis.model.Client;
import com.axinalis.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ClientsConsumer {

    private Logger log = LoggerFactory.getLogger(ClientsConsumer.class);
    private ClientDao dao;
    private ClientService service;

    public ClientsConsumer(@Autowired ClientDao dao,
                           @Autowired ClientService service) {
        this.dao = dao;
        this.service = service;
    }

    @KafkaListener(topics = "${topic.client}")
    public void listen(String email) throws JsonProcessingException {
        log.info("A request for creating new client with email {}", email);
        Client client = dao.createUser(email);

        service.returnCreatedClient(client);
    }
}
