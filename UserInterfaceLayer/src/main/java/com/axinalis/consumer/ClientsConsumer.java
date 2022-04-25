package com.axinalis.consumer;

import com.axinalis.model.Client;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class ClientsConsumer {

    private Logger logger = Logger.getAnonymousLogger();

    @KafkaListener(topics = "${CREATE_USER_TOPIC}", groupId = "${GROUP_ID}")
    public void listen(String message){
        logger.info(message);
    }

    public List<Client> getUsers(){
        throw new RuntimeException("Not implemented yet");
    }
}
