package ooad;

import ooad.Database.Database;
import ooad.Database.Menu;
import ooad.Database.ThemaDTO;
import java.util.List;
import java.util.Scanner;

public class VagadoQuiz {
    private static Database database = new Database();
    boolean exit;
    boolean ingelogd = false;

   // public static void main(String[] args) {
   //     database.SetupDatabase();
   //     VagadoQuiz quiz = new VagadoQuiz();
   //     Menu main = new MainMenu();
   //     main.runMenu();
   // }

    public static void main(String[] args) {
        VagadoQuiz quiz = new VagadoQuiz();
        quiz = quiz.mainMenu(quiz);
        System.out.println("Application has been shut down");
    }

    private VagadoQuiz mainMenu(VagadoQuiz quiz) {
        displayHeader("Vagado Quiz App");

        int selection = 0;

        do {
            System.out.println("[1] Registreren");
            System.out.println("[2] Inloggen");
            System.out.println("[3] Exit");

            selection = getMenuChoice();
            switch (selection) {
                case 1: return quiz.registreren(quiz);
                case 2: return quiz.inloggen(quiz);
                case 3: return quiz;
                default:
                    System.out.println("The selection was invalid!");
            }
        } while (selection != 3);
        return quiz;
    }

    private VagadoQuiz quizMenu(VagadoQuiz quiz) {
        displayHeader("Quiz menu");

        int selection = 0;

        do {
            System.out.println("[1] SUBMENU_1");
            System.out.println("[2] SUBMENU_2");
            System.out.println("[3] SUBMENU_3");
            System.out.println("[4] Return");

            selection = getMenuChoice();

            switch (selection) {
                case 1:
                    return quiz.mainMenu(quiz);
                case 2:
                    return quiz.mainMenu(quiz);
                case 3:
                    return quiz.mainMenu(quiz);
                case 4:
                    return quiz.mainMenu(quiz);
                default:
                    System.out.println("The selection was invalid!");
            }
        } while (selection != 4);
        return quiz;
    }

    private VagadoQuiz inloggen(VagadoQuiz quiz) {
        displayHeader("Login");

        while(!ingelogd){
            inloggen();
        }

        return quiz.quizMenu(quiz);
    }

    private VagadoQuiz registreren(VagadoQuiz quiz) {
        displayHeader("registreer");

        while(!ingelogd){
            registreren();
        }

        return quiz.quizMenu(quiz);
    }


    public int getMenuChoice() {
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

    public void displayHeader(String message) {
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


    //public void runMenu() {
//
    //}
//
    //private void printHeader() {
    ////
    //}
//
    //private void printMenu() {
//
    //}
//
    //private int getMenuChoice() {
//
    //}
//
    //private void performAction(int choice) {
//
    //}
//
    //private void displayHeader(String message){
//
    //}

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

   //private void vragenlijstKopen() {
   //    //displayHeader("Winkel");
   //    System.out.println("Kies een van onderstaande thema's");
   //    List<String> ls = ThemaDTO.getThemas();
   //    int i = 0;
   //    while (i <ls.size()) {
   //        System.out.println(i+1 + ") " + ls.get(i));
   //        i++;
   //    }
   //    String keuze = askQuestion("Kies een thema: ", null);
   //    kiesVragenlijst(keuze);
   //}

   //private void quizSpelen() {
   //   // displayHeader("Quiz");

   //}

   //private void kiesVragenlijst(String thema){

   //}

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
