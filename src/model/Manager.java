package model;

import enums.Departement;
import enums.Role;

import java.util.ArrayList;

public class Manager extends Person{
    ArrayList<Client> clients;
    Departement departement;
    public Manager(String nom, String prenom, String email, String motDePasse, Role role){
        super(nom, prenom, email, motDePasse, role);
        this.clients = new ArrayList<>();
    }

    public void addClient(Client client){
        clients.add(client);
    }

    public ArrayList<Client> getClients(){return this.clients;}
}
