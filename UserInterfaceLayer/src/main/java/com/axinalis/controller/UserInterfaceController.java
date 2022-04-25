package com.axinalis.controller;

import com.axinalis.consumer.ClientsConsumer;
import com.axinalis.consumer.TransactionsConsumer;
import com.axinalis.model.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("kafka")
public class UserInterfaceController {

    private KafkaTemplate<String, String> template;
    private ClientsConsumer clientsConsumer;
    private TransactionsConsumer transactionsConsumer;
    @Value("${CREATE_USER_TOPIC}")
    private String createUserTopic;
    @Value("${TRANSACTIONS_TOPIC}")
    private String transactionsTopic;
    private ObjectMapper mapper;

    public UserInterfaceController(@Autowired KafkaTemplate<String, String> template,
                                   @Autowired ClientsConsumer clientsConsumer,
                                   @Autowired TransactionsConsumer transactionsConsumer,
                                   @Autowired ObjectMapper mapper) {
        this.template = template;
        this.clientsConsumer = clientsConsumer;
        this.transactionsConsumer = transactionsConsumer;
        this.mapper = mapper;
    }

    @PostMapping("/user")
    public void createUser(@RequestBody String email){
        if( ! Pattern.compile("(\\S+)@(\\w+)(\\.)(\\w+)")
                .matcher(email)
                .matches()){
            throw new RuntimeException("Email is not valid");
        }
        template.send(createUserTopic, email);
    }

    @PostMapping("/user/{id}/message")
    public void produce(@RequestBody String message) throws JsonProcessingException {
        Transaction buffer = mapper.readValue(message, Transaction.class);
        String stringMessage = mapper.writeValueAsString(buffer);
        template.send(transactionsTopic, stringMessage);
    }

    @GetMapping("/user/{id}/message")
    public List<Transaction> getMessages(@PathVariable int id){
        return transactionsConsumer.getMessages();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleException(){

    }
}
