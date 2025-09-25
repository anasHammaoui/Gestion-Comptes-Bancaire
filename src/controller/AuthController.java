package controller;

import java.util.Optional;

import enums.Departement;
import model.Client;
import model.Manager;
import model.Person;
import service.AuthService;
import service.ClientService;

public class AuthController {
    private final AuthService authService;
    private final ClientService clientService;

    public AuthController(AuthService authService, ClientService clientService) {
        this.authService = authService;
        this.clientService = clientService;
    }
  
     public Optional<Person> login(String email, String password) {
        return authService.login(email, password);
    }
    
        public Client registerClient(String firstName, String lastName, String email, String password) {
        Client client = authService.registerClient(firstName, lastName, email, password);
        clientService.addClient(client);
        return client;
    }

        public Manager registerManager(String firstName, String lastName, String email, String password, Departement departmentType) {
        return authService.registerManager(firstName, lastName, email, password, departmentType);
    }
}

