package com.axinalis.controller;

import com.axinalis.consumer.ClientsConsumer;
import com.axinalis.model.Transaction;
import com.axinalis.model.TransactionType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserInterfaceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private KafkaTemplate<String, String> template;
    @MockBean
    private ClientsConsumer cConsumer;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void contextLoads(){
    }

    @Test
    public void testMockMvc(){
        assertNotEquals(null, mockMvc);
    }

    @Test
    public void createUserTest() throws Exception {
        mockMvc.perform(post("/kafka/user")
                .contentType("application/json")
                .content("lalala@gmail.com"))
                .andExpect(status().isOk());
    }

    @Test
    public void createUserInvalidEmailTest() throws Exception {
        mockMvc.perform(post("/kafka/user")
                .contentType("application/json")
                .content("emailWithoutThatASign"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createUserInvalidEmail2Test() throws Exception {
        mockMvc.perform(post("/kafka/user")
                .contentType("application/json")
                .content("lalala@gmailcom"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createNewInvalidMessage() throws Exception {
        mockMvc.perform(post("/kafka/user/1/message")
                .contentType("application/json")
                .content(""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createNewMessage() throws Exception {
        mockMvc.perform(post("/kafka/user/1/message")
                .contentType("application/json")
                .content("{\n" +
                        "    \"bank\" : \"Techno\",\n" +
                        "    \"clientId\" : 1,\n" +
                        "    \"orderType\" : \"INCOME\",\n" +
                        "    \"quantity\" : 100,\n" +
                        "    \"price\" : 10.10,\n" +
                        "    \"createdAt\" : \"2022-04-23T17:29:15.483050\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testJsonParse() throws JsonProcessingException {
        String message = "{\n" +
                "    \"bank\" : \"Techno\",\n" +
                "    \"clientId\" : 1,\n" +
                "    \"orderType\" : \"INCOME\",\n" +
                "    \"quantity\" : 100,\n" +
                "    \"price\" : 10.10,\n" +
                "    \"createdAt\" : \"2022-04-23T17:29:15.483050\"\n" +
                "}";

        Transaction transaction2 = mapper.readValue(message, Transaction.class);

        assertEquals("Techno", transaction2.getBank());
        assertEquals(1L, transaction2.getClientId());
        assertEquals(TransactionType.INCOME, transaction2.getOrderType());
        assertEquals(100, transaction2.getQuantity());
        assertEquals(10.10, transaction2.getPrice());
        assertEquals(LocalDateTime.parse("2022-04-23T17:29:15.483050"), transaction2.getCreatedAt());
    }
}
