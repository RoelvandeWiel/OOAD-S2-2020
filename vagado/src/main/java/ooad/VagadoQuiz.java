package ooad;

import ooad.Database.Database;
import ooad.Database.GebruikerDTO;

import javax.xml.crypto.Data;
import java.io.Console;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class VagadoQuiz
{
    private static Database database = new Database();
    boolean exit;

    public static void main(String[] args) {
        VagadoQuiz quiz = new VagadoQuiz();
        quiz.runMenu();

    }

    public void runMenu() {
        printHeader();
        while (!exit) {
            printMenu();
            int choice = getMenuChoice();
            performAction(choice);
        }
    }

    private void printHeader() {
        System.out.println("+-----------------------------------+");
        System.out.println("|        Welcome to the             |");
        System.out.println("|        Vagado Quiz App            |");
        System.out.println("+-----------------------------------+");
    }

    private void printMenu() {
        displayHeader("Please make a selection");
        System.out.println("1) Registreren");
        System.out.println("2) Inloggen");
        System.out.println("3) Optie 3");
        System.out.println("4) Optie 4");
        System.out.println("0) Exit");
    }

    private int getMenuChoice() {
        Scanner keyboard = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Numbers only please.");
            }
            if (choice < 0 || choice > 4) {
                System.out.println("Choice outside of range. Please chose again.");
            }
        } while (choice < 0 || choice > 4);
        return choice;
    }

    private void performAction(int choice) {
        switch (choice) {
            case 0:
                System.out.println("Thank you for using our application.");
                System.exit(0);
                break;
            case 1: {
                registreren();
            }
            break;
            case 2:
                inloggen();
                break;
            case 3:

                break;
            case 4:

                break;
            default:
                System.out.println("Unknown error has occured.");
        }
    }

    private void displayHeader(String message){
        System.out.println();
        int width = message.length() + 6;
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for(int i = 0; i < width; ++i){
            sb.append("-");
        }
        sb.append("+");
        System.out.println(sb.toString());
        System.out.println("|   " + message + "   |");
        System.out.println(sb.toString());
    }

    private String askQuestion(String question, List<String> answers) {
        String response = "";
        Scanner keyboard = new Scanner(System.in);
        boolean choices = ((answers == null) || answers.size() == 0) ? false : true;
        boolean firstRun = true;
        do {
            if (!firstRun) {
                System.out.println("Invalid selection. Please try again.");
            }
            System.out.print(question);
            if (choices) {
                System.out.print("(");
                for (int i = 0; i < answers.size() - 1; ++i) {
                    System.out.print(answers.get(i) + "/");
                }
                System.out.print(answers.get(answers.size() - 1));
                System.out.print("): ");
            }
            response = keyboard.nextLine();
            firstRun = false;
            if (!choices) {
                break;
            }
        } while (!answers.contains(response));
        return response;
    }

    private void registreren(){
        displayHeader("Registreren");
        String gebruikersnaam = askQuestion("Gebruikersnaam: ", null);
        String wachtwoord = askQuestion("Wachtwoord: ", null);

        registreren(gebruikersnaam, wachtwoord);
    }

    private void inloggen(){
        displayHeader("Inloggen");
        String gebruikersnaam = askQuestion("Gebruikersnaam: ", null);
        String wachtwoord = askQuestion("Wachtwoord: ", null);

        login(gebruikersnaam, wachtwoord);
    }

    private static void registreren(String gebruikersnaam, String wachtwoord){
        database.RegistreerGebruiker(gebruikersnaam, wachtwoord);
    }

    private static void login(final String gebruikersnaam, String wachtwoord){
        boolean login = database.gebruikers.stream().anyMatch(u -> u.gebruikersnaam.equals(gebruikersnaam) && u.wachtwoord.equals(wachtwoord));

        if(login){
            System.out.println("login true");
        }else{
            System.out.println("login false");
        }
    }
}
