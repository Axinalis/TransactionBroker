package com.axinalis.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Clients")
public class ClientEntity {

    private Long clientId;
    private String email;

    @Id
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
        ClientEntity that = (ClientEntity) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, email);
    }
}
