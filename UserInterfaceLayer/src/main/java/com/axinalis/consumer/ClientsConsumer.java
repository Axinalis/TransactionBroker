package com.axinalis.consumer;

import com.axinalis.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientsConsumer {

    private Logger log = LoggerFactory.getLogger(ClientsConsumer.class);

    @KafkaListener(topics = "${RESPONSE_TOPIC}", groupId = "${GROUP_ID}")
    public void listen(String message){
        log.info("Message for user is {}", message);
    }

    public List<Client> getUsers(){
        throw new RuntimeException("Not implemented yet");
    }
}
