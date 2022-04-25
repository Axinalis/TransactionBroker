package com.axinalis.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    private String bank;
    private Long clientId;
    private TransactionType orderType;
    private Integer quantity;
    private Double price;
    private LocalDateTime createdAt;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public TransactionType getOrderType() {
        return orderType;
    }

    public void setOrderType(TransactionType orderType) {
        this.orderType = orderType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(bank, that.bank) && Objects.equals(clientId, that.clientId) && orderType == that.orderType && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bank, clientId, orderType, quantity, price, createdAt);
    }

}
