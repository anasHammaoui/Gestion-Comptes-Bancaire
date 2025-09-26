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
    private ManagerMenu managerMenu;
    private ClientMenu clientMenu;
    public Home(AuthController auth, ManagerMenu managerMenu, ClientMenu clientMenu ){
        this.authController = auth;
        this.managerMenu =managerMenu ;
        this.clientMenu = clientMenu;
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
        
        Manager manager = authController.registerManager(firstName, lastName, email, password, department);
        if (manager != null) {
            System.out.println("******Welcome Manager " + manager.getPrenom() + " " + manager.getNom() + "*******");
        }
        // Error message already printed by controller if registration failed
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
                managerMenu.showManagerMenu(manager, input);
            } else if (loggedInUser instanceof Client) {
                Client client = (Client) loggedInUser;
                clientMenu.showClientMenu(client, input);
            }
        } else {
            System.out.println("Please check your credentials and try again");
        }
    }



}
