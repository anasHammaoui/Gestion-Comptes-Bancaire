package service;

// import helpers.Utils;
import model.Account;
import model.Client;
import model.Manager;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import enums.AccountType;


public class AccountService {

    private final List<Account> accounts;
    private final ClientService clientService;

    public AccountService(ClientService clientService) {
        this.accounts = new ArrayList<>();
        this.clientService = clientService;
    }

    public Account findAccountById(UUID id) {
        return accounts
                .stream()
                .filter(account -> account.getId() ==id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No Account Found with ID:" + id));
    }

    public Account createAccount(Manager manager, UUID clientId, AccountType type, double initialBalance){
        if ( clientId == null) {
            throw new IllegalArgumentException("The Client ID is required");
        }
        Client client = clientService.findClientById(clientId);
        if (!Utils.isManager(manager)) {
            throw new SecurityException("Access denied, You need manager permissions.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance can't be negative");
        }
        Account account = new Account(type,initialBalance,client);
        accounts.add(account);
        client.addAccount(account);
        return account;
    };

    public Account updateAccount(Manager manager,UUID clientId, UUID id, AccountType newType){
        if (!Utils.isManager(manager)) {
            throw new SecurityException("Access denied, You need manager permissions.");
        }
        if ( id == null) {
            throw new IllegalArgumentException("The account ID is required");
        }
        Client client = clientService.findClientById(clientId);
        if (newType == null) {
            throw new IllegalArgumentException("The account Type can not be null");
        }

        Account account = findAccountById(id);
        if (!account.getClient().equals(client)) {
            throw new SecurityException("Access denied.The account does not belong to the specified Client.");
        }
        account.setType(newType);
        return account;
    };

    public void deleteAccount(Manager manager, UUID accountId, UUID clientId) {
        if (!Utils.isManager(manager)) {
            throw new SecurityException("Access denied, You need manager permissions.");
        }
        if (accountId == null) {
            throw new IllegalArgumentException("Account ID required");
        }
        if (clientId == null) {
            throw new IllegalArgumentException("Account ID required");
        }
        Client client = clientService.findClientById(clientId);
        Account account = findAccountById(accountId);
        if (!account.getClient().equals(client)) {
            throw new SecurityException("Access denied.The account does not belong to the specified Client.");
        }
        accounts.remove(account);
        client.getAccounts().remove(account);
    }

    public List<Account> getAllAccounts(Manager manager) {
        if (!Utils.isManager(manager)) {
            throw new SecurityException("Access denied.Operation allowed only for managers");
        }
        return new ArrayList<>(accounts);
    }

    public List<Account> getAccountsByClientId(UUID clientId) {

        if (clientId == null) {
            throw new IllegalArgumentException("The client ID is required");
        }
        return accounts
                .stream()
                .filter(account -> account.getClient().getId().equals(clientId))
                .collect(Collectors.toList());

    }

    public List<Account> getAccountsByType(Manager manager, AccountType type) {

        if (type == null) {
            throw new IllegalArgumentException("Account type is required");
        }
        return accounts
                .stream()
                .filter(account -> account.getType() == type)
                .collect(Collectors.toList());
    }

    public double getTotalBalance(UUID clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("The client ID is required");
        }
        return getAccountsByClientId(clientId)
                .stream()
                .mapToDouble(Account::getSold)
                .sum();
    }

}