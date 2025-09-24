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
    }
}
