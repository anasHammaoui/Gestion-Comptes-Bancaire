package view;

import java.util.Scanner;

import controller.AuthController;
import model.Client;
import model.Manager;

public class ManagerMenu {
    private AuthController authController;
    public ManagerMenu(AuthController authController){
        this.authController = authController;
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
