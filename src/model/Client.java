package model;

import enums.Role;

public class Client extends Person {
    public Client(String nom, String prenom, String email, String motDePasse, Role role){
        super(nom, prenom, email, motDePasse, role);
    }
}
