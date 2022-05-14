package com.axinalis.model;

import java.util.Objects;

public class Client {

    private Long clientId;
    private String email;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(clientId, client.clientId) && Objects.equals(email, client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, email);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", email='" + email + '\'' +
                '}';
    }
}