package model;

import enums.Role;

import java.util.ArrayList;

public class Client extends Person {
    private ArrayList<Account> accounts;
    public Client(String nom, String prenom, String email, String motDePasse, Role role){
        super(nom, prenom, email, motDePasse, role);
        this.accounts = new ArrayList<>();
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }public void removeAccount(Account account) {
        this.accounts.remove(account);
    }

    public double getTotalBalance() {
        double total = 0.0;
        for (Account account : accounts) {
            total += account.getSold();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + getNom() + '\'' +
                ", prenom='" + getPrenom() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", AccountsSize=" + accounts.size() +
                ", totalSold=" + getTotalBalance() +
                '}';
    }
}
