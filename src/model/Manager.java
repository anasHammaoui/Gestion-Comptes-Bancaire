package model;

import enums.Departement;
import enums.Role;

import java.util.ArrayList;

public class Manager extends Person{
    ArrayList<Client> clients;
    Departement departement;
    public Manager(String nom, String prenom, String email, String motDePasse, Role role,Departement departement){
        super(nom, prenom, email, motDePasse, role);
        this.clients = new ArrayList<>();
        this.departement = departement;
    }

    public void addClient(Client client){
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public ArrayList<Client> getClients() {
        return this.clients;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "nom='" + getNom() + '\'' +
                ", prenom='" + getPrenom() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", departement=" + departement +
                ", nombreClients=" + clients.size() +
                '}';
    }
}
