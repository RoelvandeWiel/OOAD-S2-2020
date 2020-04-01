package ooad;

import ooad.Database.Database;
import ooad.Database.GebruikerDTO;

import javax.xml.crypto.Data;
import java.io.Console;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class VagadoQuiz
{
    public static void main(String[] args)
    {
        Database database = new Database();

        do {
        String userInput;
        Scanner sn = new Scanner(System.in);

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
                    registreren(database, gebruikersnaam, wachtwoord);
                    break;
                case "2":
                    System.out.println("===== INLOGGEN =====");
                    System.out.println("*Gebruikersnaam: ");
                    String login_gebruikersnaam = sn.next();
                    System.out.println("*Wachtwoord: ");
                    String login_wachtwoord = sn.next();
                    login(database, login_gebruikersnaam, login_wachtwoord);
                    break;
                case "3":
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Read the options carefully...");
            }
        } while (true);
    }

    private static void registreren(Database database, String gebruikersnaam, String wachtwoord){
        //implement
        database.SetUser(gebruikersnaam, wachtwoord);
    }

    private static void login(Database database, String gebruikersnaam, String wachtwoord){

        GebruikerDTO gebruiker = new GebruikerDTO(gebruikersnaam, wachtwoord, 100);

        if(database.gebruikers.contains(gebruiker)){
            System.out.println("login true");
        }else{
            System.out.println("login false");
        }
    }
}
