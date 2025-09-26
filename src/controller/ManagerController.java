package controller;

import java.util.List;
import java.util.UUID;

import enums.AccountType;
import model.Account;
import model.Client;
import model.Manager;
import service.AccountService;
import service.ClientService;

public class ManagerController {
    private final AccountService accountService;
    private final ClientService clientService;

    public ManagerController(AccountService accountService, ClientService clientService) {
        this.accountService = accountService;
        this.clientService = clientService;
    }

    public Account createAccount(Manager manager, UUID clientId, AccountType type, double initialBalance) {
        try {
            return accountService.createAccount(manager, clientId, type, initialBalance);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            return null;
        }
    }
    public Account updateAccount(Manager manager, UUID clientId, UUID accountId, AccountType newType) {
        try {
            return accountService.updateAccount(manager, clientId, accountId, newType);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    public void deleteAccount(Manager manager, UUID accountId, UUID clientId) {
        try {
            accountService.deleteAccount(manager, accountId, clientId);
            System.out.println("Account deleted successfully.");
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public List<Account> getAllAccounts(Manager manager) {
        try {
            return accountService.getAllAccounts(manager);
        } catch (Exception e) {
            System.err.println("Error retrieving accounts: " + e.getMessage());
            return List.of();
        }
    }

    public List<Account> getAccountsByClientId(UUID clientId) {
        try {
            return accountService.getAccountsByClientId(clientId);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            return List.of(); 
        }
    }

    public List<Account> getAccountsByType(Manager manager, AccountType type) {
        try {
            return accountService.getAccountsByType(manager, type);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            return List.of();
        }
    }

    public double getTotalBalance(UUID clientId) {
        try {
            return accountService.getTotalBalance(clientId);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            return 0.0;
        }
    }

    public Account findAccountById(UUID accountId) {
        try {
            return accountService.findAccountById(accountId);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    public List<Client> getAllClients() {
        try {
            return clientService.getAllClients();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            return List.of();
        }
    }
}
