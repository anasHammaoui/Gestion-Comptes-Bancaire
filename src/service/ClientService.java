package service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import model.Client;

public class ClientService {

    private List<Client> clients;

    public ClientService() {
        this.clients = new ArrayList<>();
    }

    public Client findClientById(UUID id) {
        return clients.stream()
                .filter(client -> client.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Client not found with ID: " + id));
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public List<Client> getAllClients() {
        return new ArrayList<>(clients);
    }
}
