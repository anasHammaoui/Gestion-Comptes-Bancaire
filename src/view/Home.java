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
        System.out.println("*****Register Menu******");
        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();
        System.out.print("Enter your email: ");
        String email = input.nextLine();
        System.out.print("Enter your password: ");
        String password = input.nextLine();
        
        System.out.println("Choose your role:");
        System.out.println("1. MANAGER");
        System.out.println("2. CLIENT");
        int roleChoice = input.nextInt();
        input.nextLine();
        
        switch(roleChoice) {
            case 1:
                System.out.println("Choose department:");
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
                       Manager manager =  authController.registerManager(firstName, lastName, email, password, department);
                    System.out.println("******Welcome " + manager.getNom()+"*******");
                    } catch (IllegalArgumentException e){
                         System.out.println(e.getMessage());
                    }
                break;
                
            case 2:
                try {
                   Client client =  authController.registerClient(firstName, lastName, email, password);
                    System.out.println("******Welcome " +  client.getPrenom() + " " + client.getNom() +"*******");
                 } catch(IllegalArgumentException e){
                   System.out.println(e.getMessage());
                 }
                break;
                
            default:
                System.err.println("Invalid role choice");
                return;
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
            System.out.println("*******Welcome "+ user.get().getPrenom() + " " +user.get().getNom() +"*******");
        } else {
            System.out.println("Please check your credential and try again");
        }
    }
}
