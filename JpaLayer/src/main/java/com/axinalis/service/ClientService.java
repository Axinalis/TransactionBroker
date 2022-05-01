package com.axinalis.service;

import com.axinalis.model.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClientService {

    private KafkaTemplate<String, String> template;
    private ObjectMapper mapper;

    public ClientService(@Autowired KafkaTemplate<String, String> template,
                         @Autowired ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public void returnCreatedClient(Client client) throws JsonProcessingException {
        String stringClient = mapper.writeValueAsString(client);
        template.send("newlyGenerated", stringClient);
    }

}
