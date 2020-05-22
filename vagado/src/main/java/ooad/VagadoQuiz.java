package ooad;

import ooad.Database.Database;
import ooad.Database.ThemaDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VagadoQuiz {

    private static Database database = new Database();
    boolean exit;
    boolean ingelogd = false;

    public static void main(String[] args) {
        database.SetupDatabase();
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
        System.out.println("|          Welkom bij de            |");
        System.out.println("|         Vagado Quiz App           |");
        System.out.println("+-----------------------------------+");
    }

    private void printMenu() {
        displayHeader("Maak alstublieft een keuze");
        System.out.println("1) Registreren");
        System.out.println("2) Inloggen");
        System.out.println("3) Winkel");
        System.out.println("4) Quiz spelen");
        System.out.println("0) Exit");
    }

    private int getMenuChoice() {
        Scanner keyboard = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.print("Voer alstublieft uw keuze in: ");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ongeldig karakter, alleen nummers alstublieft.");
            }
            if (choice < 0 || choice > 4) {
                System.out.println("Keuze is niet mogelijk, probeer een andere optie.");
            }
        } while (choice < 0 || choice > 4);
        return choice;
    }

    private void performAction(int choice) {

        switch (choice) {
            case 1:
                if(ingelogd){
                    System.out.println("U bent reeds ingelogd, maak een andere keuze");
                } else {
                    registreren();
                }
                break;
            case 2:
                if(ingelogd){
                    System.out.println("U bent reeds ingelogd, maak een andere keuze");
                } else {
                    inloggen();
                }
                break;
            case 3:
                if(ingelogd) {
                    vragenlijstKopen();
                } else {
                    System.out.println("U bent nog niet ingelogd, doe dit eerst.");
                }
                break;
            case 4:
                if(ingelogd) {
                    quizSpelen();
                } else {
                    System.out.println("U bent nog niet ingelogd, doe dit eerst.");
                }
                break;
            case 0:
                System.out.println("Bedankt voor het gebruiken van onze applicatie.");
                System.exit(0);
                break;
            default:
                System.out.println("Error");
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
                System.out.println("Ongeldige keuze. Probeer het alstublieft opnieuw.");
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

        ingelogd = login(gebruikersnaam, wachtwoord);
    }

    private void vragenlijstKopen() {
        displayHeader("Winkel");
        System.out.println("Kies een van onderstaande thema's");
        List<String> ls = ThemaDTO.getThemas();
        int i = 0;
        while (i <ls.size()) {
            System.out.println(i+1 + ") " + ls.get(i));
            i++;
        }
        String keuze = askQuestion("Kies een thema: ", null);
        kiesVragenlijst(keuze);
    }

    private void quizSpelen() {
        displayHeader("Quiz");

    }

    private void kiesVragenlijst(String thema){

    }

    private void registreren(String gebruikersnaam, String wachtwoord){
        boolean registratie = database.gebruikers.stream().anyMatch(u -> u.gebruikersnaam.equals(gebruikersnaam));

        if(registratie){
            System.out.println("Gebruikersnaam al in gebruik, probeer het nog eens.");
        }else{
            database.RegistreerGebruiker(gebruikersnaam, wachtwoord);
            System.out.println("Registratie geslaagd.");
            ingelogd = login(gebruikersnaam, wachtwoord);
        }
    }

    private static boolean login(String gebruikersnaam, String wachtwoord){
        boolean login = database.gebruikers.stream().anyMatch(u -> u.gebruikersnaam.equals(gebruikersnaam) && u.wachtwoord.equals(wachtwoord));

        if(login){
            System.out.println("Login geslaagd, welkom!");
        }else{
            System.out.println("Login gefaald, probeer het opnieuw.");
        }
        return login;
    }
}
