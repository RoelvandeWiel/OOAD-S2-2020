package ooad;

import java.io.Console;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class VagadoQuiz
{
    public static void main( String[] args )
    {
        String menu = "Main";
        String userInput;
        Scanner sn = new Scanner(System.in);

        while(menu.equals("Main")){
            System.out.println("***** Vagado Quiz *****");
            System.out.println("===== MAIN MENU =====");
            System.out.println("*. Press 1 for Registreren");
            System.out.println("*. Press 2 for Inloggen");
            System.out.println("*. Press 3 to exit");

            System.out.println("Enter your choice:");

            userInput = sn.next();

            switch(userInput){
                case "1":
                    System.out.println("===== REGISTREREN =====");
                    System.out.println("*Gebruikersnaam: ");
                    String gebruikersnaam = sn.next();
                    System.out.println("*Wachtwoord: ");
                    String wachtwoord = sn.next();
                    registreren(gebruikersnaam, wachtwoord);
                    break;
                case "2":
                    System.out.println("===== INLOGGEN =====");
                    System.out.println("*Gebruikersnaam: ");
                    String login_gebruikersnaam = sn.next();
                    System.out.println("*Wachtwoord: ");
                    String login_wachtwoord = sn.next();
                    login(login_gebruikersnaam, login_wachtwoord);
                    break;
                case "3":
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Read the options carefully...");
            }
        }
    }

    private static void registreren(String gebruikersnaam, String wachtwoord){
        //implement
    }

    private static void login(String gebruikersnaam, String wachtwoord){
        //implement
    }
}
