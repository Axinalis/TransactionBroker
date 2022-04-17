package com.axinalis.consumer;

import com.axinalis.entity.ClientEntity;
import com.axinalis.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class ClientsConsumer {

    private ClientRepository repository;

    public ClientsConsumer(@Autowired ClientRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "clients")
    public void listen(String email){
        ClientEntity clientToDb = new ClientEntity();

        clientToDb.setEmail(email);
        repository.save(clientToDb);
    }
}
