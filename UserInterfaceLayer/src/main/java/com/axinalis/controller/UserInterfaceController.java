package com.axinalis.controller;

import com.axinalis.consumer.ClientsConsumer;
import com.axinalis.model.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("kafka")
public class UserInterfaceController {

    @Value("${CREATE_USER_TOPIC}")
    private String createUserTopic;
    @Value("${TRANSACTIONS_TOPIC}")
    private String transactionsTopic;

    private Logger log = LoggerFactory.getLogger(UserInterfaceController.class);
    private KafkaTemplate<String, String> template;
    private ClientsConsumer clientsConsumer;
    private ObjectMapper mapper;

    public UserInterfaceController(@Autowired KafkaTemplate<String, String> template,
                                   @Autowired ClientsConsumer clientsConsumer,
                                   @Autowired ObjectMapper mapper) {
        this.template = template;
        this.clientsConsumer = clientsConsumer;
        this.mapper = mapper;
    }

    @PostMapping("/user")
    public void createUser(@RequestBody String email){

        if( ! Pattern.compile("(\\S+)@(\\w+)(\\.)(\\w+)").matcher(email).matches()){
            log.warn("New user email is not valid ({})", email);
            throw new RuntimeException("Email is not valid");
        }

        log.info("Request for creating new user with email \"{}\"", email);
        template.send(createUserTopic, email);
    }

    @PostMapping("/user/{id}/message")
    public void produce(@RequestBody Transaction message, @PathVariable long id) throws JsonProcessingException {
        message.setClientId(id);
        String stringMessage = mapper.writeValueAsString(message);
        log.info("User with id {} creates new transaction; Bank: {}, OrderType: {}",
                id, message.getBank(), message.getOrderType().toString());

        template.send(transactionsTopic, stringMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleException(){

    }
}
