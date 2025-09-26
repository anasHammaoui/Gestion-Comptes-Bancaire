package view;

import java.util.Optional;
import java.util.Scanner;

import controller.AuthController;
import enums.Departement;
import model.Client;
import model.Manager;
import model.Person;

public class Home {
    private AuthController authController;
    public Home(AuthController auth){
        this.authController = auth;
    }

    public String guestMenu(){
        String menu = """
                1)Register
                2)Login
                0)Exit""";
        return menu;
    }

    public void registerMenu(Scanner input) {
        System.out.println("*****Manager Registration******");
        System.out.println("Note: Only bank managers can register here. Client accounts are created by managers.");
        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();
        System.out.print("Enter your email: ");
        String email = input.nextLine();
        System.out.print("Enter your password: ");
        String password = input.nextLine();
        
        System.out.println("Choose your department:");
        System.out.println("1. IT");
        System.out.println("2. MARKETING");
        int deptChoice = input.nextInt();
        input.nextLine();
        
        Departement department;
        switch(deptChoice) {
            case 1:
                department = Departement.IT;
                break;
            case 2:
                department = Departement.MARKETING;
                break;
            default:
                System.err.println("Invalid department choice");
                return;
        }
        
        try {
            Manager manager = authController.registerManager(firstName, lastName, email, password, department);
            System.out.println("******Welcome Manager " + manager.getPrenom() + " " + manager.getNom() + "*******");
        } catch (IllegalArgumentException e){
            System.out.println("Registration Error: " + e.getMessage());
        }
    }

    public void loginMenu(Scanner input){
        System.out.println("*****Login Menu******");
        System.out.print("Enter your Email:");
        String email = input.nextLine();
        System.out.print("Enter your password: ");
        String password = input.nextLine();
        Optional<Person> user = authController.login(email, password);
        if (user.isPresent()){
            Person loggedInUser = user.get();
            System.out.println("*******Welcome "+ loggedInUser.getPrenom() + " " + loggedInUser.getNom() +"*******");
            
            if (loggedInUser instanceof Manager) {
                Manager manager = (Manager) loggedInUser;
                showManagerMenu(manager, input);
            } else if (loggedInUser instanceof Client) {
                Client client = (Client) loggedInUser;
                showClientMenu(client, input);
            }
        } else {
            System.out.println("Please check your credentials and try again");
        }
    }

    public void showManagerMenu(Manager manager, Scanner input) {
        boolean running = true;
        while (running) {
            System.out.println("*******MANAGER DASHBOARD*******");
            System.out.println("1) Create Client Account");
            System.out.println("2) View Profile");
            System.out.println("0) Logout");
            System.out.print("Choose an option: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    createClientAccount(input);
                    break;
                case 2:
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

    public void showClientMenu(Client client, Scanner input) {
        boolean running = true;
        while (running) {
            System.out.println("*******CLIENT DASHBOARD*******");
            System.out.println("1) View Profile");
            System.out.println("2) Transactions History");
            System.out.println("0) Logout");
            System.out.print("Choose an option: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(client);
                    break;
                case 2:
                    System.out.println("Transactions features coming soon...");
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

        try {
            Client newClient = authController.registerClient(firstName, lastName, email, password);
            System.out.println("******Client account created******");
            System.out.println("Client: " + newClient.getPrenom() + " " + newClient.getNom());
            System.out.println("Email: " + newClient.getEmail());
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
