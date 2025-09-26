import java.util.Scanner;

import controller.AuthController;
import service.AuthService;
import service.ClientService;
import view.ClientMenu;
import view.Home;
import view.ManagerMenu;

public class Main {
    public static void main(String[] args) {
 
        Scanner input = new Scanner(System.in);
        AuthService authService = new AuthService();
        ClientService clientService = new ClientService();
        AuthController authController = new AuthController(authService, clientService);
        ClientMenu clientMenu = new ClientMenu(authController);
        ManagerMenu managerMenu = new ManagerMenu(authController);
        Home menu = new Home(authController, managerMenu, clientMenu);
        int choice;
        while(true){
            System.out.println("*****Main Menu******");
            System.out.println(menu.guestMenu());
            choice = input.nextInt();
            input.nextLine();
            switch(choice){
                case 1 -> menu.registerMenu(input);
                case 2 -> menu.loginMenu(input);
                case 0 -> System.exit(0);
                default ->  System.out.println("Invalid choice. Please try again.");
            }

        }
    }
}
