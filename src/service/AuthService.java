package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import enums.Departement;
import enums.Role;
import model.Client;
import model.Manager;
import model.Person;
import utils.Utils;

public class AuthService {
    private List<Person> users;

    public AuthService(){
        users = new ArrayList<>();
    }

    public Optional<Person> login(String email, String password){
        return users.stream()
        .filter(us -> us.getEmail().equalsIgnoreCase(email) && us.getMotDePasse().equalsIgnoreCase(password)).findFirst();
    }

      // Register client
    public Client registerClient(String firstName, String lastName, String email, String password) {
        if (Utils.isEmailInUse(users, email)) {
            throw new IllegalArgumentException("Email is already in use");
        }
        Client client = new Client(lastName, firstName, email, password, Role.CLIENT);
        users.add(client);
        return client;
    }

    // Register manager
    public Manager registerManager(String firstName, String lastName, String email, String password, Departement department) {
        if (Utils.isEmailInUse(users, email)) {
            throw new IllegalArgumentException("Email is already in use");
        }
        Manager manager = new Manager(lastName, firstName, email, password, Role.MANAGER, department);
        users.add(manager);
        return manager;
    }

}
