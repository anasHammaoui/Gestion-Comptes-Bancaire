package model;

import enums.TransactionType;

import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private TransactionType type;
    private double montant;
    private LocalDate date;
    private String motif;
    private Account source;
    private Account destination;

    public Transaction(TransactionType type, double montant, LocalDate date, String motif, Account source, Account destination) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.montant = montant;
        this.date = LocalDate.now();
        this.motif = motif;
        this.source = source;
        this.destination = destination;
    }

    public UUID getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Account getSource() {
        return source;
    }

    public void setSource(Account source) {
        this.source = source;
    }

    public Account getDestination() {
        return destination;
    }

    public void setDestination(Account destination) {
        this.destination = destination;
    }
}
