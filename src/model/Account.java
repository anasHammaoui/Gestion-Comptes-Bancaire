package model;

import enums.AccountType;

import java.util.ArrayList;
import java.util.UUID;

public class Account {
    private UUID id;
    private AccountType type;
    private double sold;
    private ArrayList<Transaction> transactions;
    private Client client;

    public Account(AccountType type, double sold, Client client) {
        this.type = type;
        this.sold = sold;
        this.client = client;
        this.transactions = new ArrayList<>();
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
