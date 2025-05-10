package model;

import java.math.BigDecimal;

public class Account {
    private Long id;
    private Long clientId;
    private String accountType;
    private BigDecimal balance;
    private String currency;
    private boolean active;

    public Account(Long id, Long clientId, String accountType, BigDecimal balance, String currency, boolean active) {
        this.id = id;
        this.clientId = clientId;
        this.accountType = accountType;
        this.balance = balance;
        this.currency = currency;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", active=" + active +
                '}';
    }
}