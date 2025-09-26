package view;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import controller.AuthController;
import controller.ManagerController;
import enums.AccountType;
import model.Account;
import model.Client;
import model.Manager;

public class ManagerMenu {
    private AuthController authController;
    private ManagerController managerController;
    
    public ManagerMenu(AuthController authController, ManagerController managerController){
        this.authController = authController;
        this.managerController = managerController;
    }
    
    public void showManagerMenu(Manager manager, Scanner input) {
        boolean running = true;
        while (running) {
            System.out.println("*******MANAGER DASHBOARD*******");
            System.out.println("1) Create Client Account");
            System.out.println("2) Manage Bank Accounts");
            System.out.println("3) View Profile");
            System.out.println("0) Logout");
            System.out.print("Choose an option: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    createClientAccount(input);
                    break;
                case 2:
                    manageAccounts(manager, input);
                    break;
                case 3:
                    System.out.println(manager);
                    break;
                case 0:
                    running = false;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void createClientAccount(Scanner input) {
        System.out.println("*******CREATE CLIENT ACCOUNT*******");
        System.out.print("Enter client's first name: ");
        String firstName = input.nextLine();
        System.out.print("Enter client's last name: ");
        String lastName = input.nextLine();
        System.out.print("Enter client's email: ");
        String email = input.nextLine();
        System.out.print("Enter client's password: ");
        String password = input.nextLine();

        Client newClient = authController.registerClient(firstName, lastName, email, password);
        if (newClient != null) {
            System.out.println("******Client account created******");
            System.out.println("Client: " + newClient.getPrenom() + " " + newClient.getNom());
            System.out.println("Email: " + newClient.getEmail());
        }
        // Error message already printed by controller if registration failed
    }

    private void manageAccounts(Manager manager, Scanner input) {
        boolean running = true;
        while (running) {
            System.out.println("*******ACCOUNT MANAGEMENT*******");
            System.out.println("1) Create Bank Account");
            System.out.println("2) View All Accounts");
            System.out.println("3) View Client Accounts");
            System.out.println("4) Update Account");
            System.out.println("5) Delete Account");
            System.out.println("6) View Accounts by Type");
            System.out.println("0) Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    createBankAccount(manager, input);
                    break;
                case 2:
                    viewAllAccounts(manager);
                    break;
                case 3:
                    viewClientAccounts(input);
                    break;
                case 4:
                    updateAccount(manager, input);
                    break;
                case 5:
                    deleteAccount(manager, input);
                    break;
                case 6:
                    viewAccountsByType(manager, input);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void createBankAccount(Manager manager, Scanner input) {
        System.out.println("*******CREATE BANK ACCOUNT*******");
        
        // Display available clients
        try {
            List<Client> clients = managerController.getAllClients();
            if (clients.isEmpty()) {
                System.out.println("No clients found. Create a client first.");
                return;
            }
            
            System.out.println("Available Clients:");
            for (int i = 0; i < clients.size(); i++) {
                Client client = clients.get(i);
                System.out.println((i + 1) + ") " + client.getPrenom() + " " + client.getNom() + " (" + client.getEmail() + ")");
            }
            
            System.out.print("Select client (number): ");
            int clientChoice = input.nextInt();
            input.nextLine();
            
            if (clientChoice < 1 || clientChoice > clients.size()) {
                System.out.println("Invalid client selection.");
                return;
            }
            
            Client selectedClient = clients.get(clientChoice - 1);
            
            // Select account type
            System.out.println("Account Types:");
            AccountType[] types = AccountType.values();
            for (int i = 0; i < types.length; i++) {
                System.out.println((i + 1) + ") " + types[i]);
            }
            
            System.out.print("Select account type (number): ");
            int typeChoice = input.nextInt();
            input.nextLine();
            
            if (typeChoice < 1 || typeChoice > types.length) {
                System.out.println("Invalid account type selection.");
                return;
            }
            
            AccountType selectedType = types[typeChoice - 1];
            
            System.out.print("Enter initial balance: ");
            double initialBalance = input.nextDouble();
            input.nextLine();
            
            Account newAccount = managerController.createAccount(manager, selectedClient.getId(), selectedType, initialBalance);
            if (newAccount != null) {
                System.out.println("******Bank Account Created Successfully******");
                System.out.println("Account ID: " + newAccount.getId());
                System.out.println("Client: " + selectedClient.getPrenom() + " " + selectedClient.getNom());
                System.out.println("Type: " + newAccount.getType());
                System.out.println("Initial Balance: " + newAccount.getSold());
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void viewAllAccounts(Manager manager) {
        try {
            List<Account> accounts = managerController.getAllAccounts(manager);
            if (accounts.isEmpty()) {
                System.out.println("No accounts found.");
                return;
            }
            
            System.out.println("*******ALL BANK ACCOUNTS*******");
            for (Account account : accounts) {
                System.out.println("ID: " + account.getId());
                System.out.println("Client: " + account.getClient().getPrenom() + " " + account.getClient().getNom());
                System.out.println("Type: " + account.getType());
                System.out.println("Balance: " + account.getSold());
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void viewClientAccounts(Scanner input) {
        try {
            List<Client> clients = managerController.getAllClients();
            if (clients.isEmpty()) {
                System.out.println("No clients found.");
                return;
            }
            
            System.out.println("Available Clients:");
            for (int i = 0; i < clients.size(); i++) {
                Client client = clients.get(i);
                System.out.println((i + 1) + ") " + client.getPrenom() + " " + client.getNom());
            }
            
            System.out.print("Select client (number): ");
            int clientChoice = input.nextInt();
            input.nextLine();
            
            if (clientChoice < 1 || clientChoice > clients.size()) {
                System.out.println("Invalid client selection.");
                return;
            }
            
            Client selectedClient = clients.get(clientChoice - 1);
            List<Account> accounts = managerController.getAccountsByClientId(selectedClient.getId());
            
            if (accounts.isEmpty()) {
                System.out.println("No accounts found for this client.");
                return;
            }
            
            System.out.println("*******ACCOUNTS FOR " + selectedClient.getPrenom() + " " + selectedClient.getNom() + "*******");
            for (Account account : accounts) {
                System.out.println("ID: " + account.getId());
                System.out.println("Type: " + account.getType());
                System.out.println("Balance: " + account.getSold());
                System.out.println("------------------------");
            }
            
            System.out.println("Total Balance: " + managerController.getTotalBalance(selectedClient.getId()));
            
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void updateAccount(Manager manager, Scanner input) {
        System.out.println("*******UPDATE ACCOUNT*******");
        System.out.print("Enter Account ID: ");
        String accountIdStr = input.nextLine();
        
        try {
            UUID accountId = UUID.fromString(accountIdStr);
            Account account = managerController.findAccountById(accountId);
            
            if (account == null) {
                return; 
            }
            
            System.out.println("Current Account Details:");
            System.out.println("Client: " + account.getClient().getPrenom() + " " + account.getClient().getNom());
            System.out.println("Current Type: " + account.getType());
            System.out.println("Balance: " + account.getSold());
            
            System.out.println("New Account Types:");
            AccountType[] types = AccountType.values();
            for (int i = 0; i < types.length; i++) {
                System.out.println((i + 1) + ") " + types[i]);
            }
            
            System.out.print("Select new account type (number): ");
            int typeChoice = input.nextInt();
            input.nextLine();
            
            if (typeChoice < 1 || typeChoice > types.length) {
                System.out.println("Invalid account type selection.");
                return;
            }
            
            AccountType newType = types[typeChoice - 1];
            Account updatedAccount = managerController.updateAccount(manager, account.getClient().getId(), accountId, newType);
            
            if (updatedAccount != null) {
                System.out.println("******Account Updated Successfully******");
                System.out.println("Account ID: " + updatedAccount.getId());
                System.out.println("New Type: " + updatedAccount.getType());
            }
            
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: Invalid Account ID format.");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void deleteAccount(Manager manager, Scanner input) {
        System.out.println("*******DELETE ACCOUNT*******");
        System.out.print("Enter Account ID: ");
        String accountIdStr = input.nextLine();
        
        try {
            UUID accountId = UUID.fromString(accountIdStr);
            Account account = managerController.findAccountById(accountId);
            
            if (account == null) {
                return;
            }
            
            System.out.println("Account to Delete:");
            System.out.println("Client: " + account.getClient().getPrenom() + " " + account.getClient().getNom());
            System.out.println("Type: " + account.getType());
            System.out.println("Balance: " + account.getSold());
            
            System.out.print("Are you sure you want to delete this account? (y/n): ");
            String confirmation = input.nextLine();
            
            if (confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes")) {
                managerController.deleteAccount(manager, accountId, account.getClient().getId());
            } else {
                System.out.println("Account deletion cancelled.");
            }
            
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: Invalid Account ID format.");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void viewAccountsByType(Manager manager, Scanner input) {
        System.out.println("*******VIEW ACCOUNTS BY TYPE*******");
        System.out.println("Account Types:");
        AccountType[] types = AccountType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ") " + types[i]);
        }
        
        System.out.print("Select account type (number): ");
        int typeChoice = input.nextInt();
        input.nextLine();
        
        if (typeChoice < 1 || typeChoice > types.length) {
            System.out.println("Invalid account type selection.");
            return;
        }
        
        AccountType selectedType = types[typeChoice - 1];
        
        try {
            List<Account> accounts = managerController.getAccountsByType(manager, selectedType);
            
            if (accounts.isEmpty()) {
                System.out.println("No " + selectedType + " accounts found.");
                return;
            }
            
            System.out.println("*******" + selectedType + " ACCOUNTS*******");
            for (Account account : accounts) {
                System.out.println("ID: " + account.getId());
                System.out.println("Client: " + account.getClient().getPrenom() + " " + account.getClient().getNom());
                System.out.println("Balance: " + account.getSold());
                System.out.println("------------------------");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
