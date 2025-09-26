package view;

import java.util.Scanner;

import controller.AuthController;
import model.Client;

public class ClientMenu {
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
}
