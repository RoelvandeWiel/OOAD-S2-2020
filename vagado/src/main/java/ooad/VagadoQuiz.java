package ooad;

import ooad.DAO.VragenLijstDAO;
import ooad.DTO.GebruikerDTO;
import ooad.Database.Database;
import ooad.DTO.ThemaDTO;
import ooad.DTO.VragenlijstDTO;
import java.util.List;
import java.util.Scanner;

import static ooad.Database.Database.vragen;

public class VagadoQuiz {
    private static Database database = new Database();
    boolean ingelogd = false;
    private static VragenLijstDAO vragenLijstDAO = new VragenLijstDAO();


    public static void main(String[] args) {
        var gebruiker = new GebruikerDTO("jesse-28", "geheim123", 150);
        var gebruiker1 = new GebruikerDTO("dfgssfg", "gehegfdsgimfdgfd123", 150);
        Database.gebruikers.add(gebruiker);
        Database.gebruikers.add(gebruiker1);
        var vragenlijst = new VragenlijstDTO("Muziek 1", vragen, new ThemaDTO("dieren"), 50);
        Database.vragenlijsten.add(vragenlijst);


        vragenLijstDAO.koopVragenLijst(vragenlijst, gebruiker);

        var x = Database.gebruikers;

        //database.SetupDatabase();
        //VagadoQuiz quiz = new VagadoQuiz();
        //quiz = quiz.mainMenu(quiz);
        //System.out.println("Application has been shut down");
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

    private VagadoQuiz inloggen(VagadoQuiz quiz) {
        displayHeader("Inloggen");

        while(!ingelogd){
            inloggen();
        }

        return quiz.quizMenu(quiz);
    }

    private void inloggen(){
        String gebruikersnaam = askQuestion("Gebruikersnaam: ", null);
        String wachtwoord = askQuestion("Wachtwoord: ", null);

        ingelogd = login(gebruikersnaam, wachtwoord);
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

    private VagadoQuiz registreren(VagadoQuiz quiz) {
        displayHeader("Registreren");

        while(!ingelogd){
            registreren();
        }

        return quiz.quizMenu(quiz);
    }

    private void registreren(){
        String gebruikersnaam = askQuestion("Gebruikersnaam: ", null);
        String wachtwoord = askQuestion("Wachtwoord: ", null);

        registreren(gebruikersnaam, wachtwoord);
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

    private VagadoQuiz quizMenu(VagadoQuiz quiz) {
        displayHeader("Quiz menu");

        int selection = 0;

        do {
            System.out.println("[1] Vagado shop");
            System.out.println("[2] Quiz spelen");
            System.out.println("[3] SUBMENU_3");
            System.out.println("[4] Uitloggen");

            selection = getMenuChoice();

            switch (selection) {
                case 1:
                    return quiz.winkelmenu(quiz);
                case 2:
                    return quiz.quizKeuzeMenu(quiz);
                case 3:
                    return quiz.mainMenu(quiz);
                case 4:
                    ingelogd = false;
                    return quiz.mainMenu(quiz);
                default:
                    System.out.println("The selection was invalid!");
            }
        } while (selection != 4);
        return quiz;
    }

    private VagadoQuiz winkelmenu(VagadoQuiz quiz) {
        displayHeader("Winkel");

        int selection = 0;

        do {

            System.out.println("Kies een van onderstaande thema's");
            List<ThemaDTO> themas = Database.getThemas();
            int i = 0;
            while (i < themas.size()) {
                System.out.println("[" + (i+1) + "] " + themas.get(i).thema);
                i++;
            }

            int keuze = getMenuChoice();
            String thema = themas.get(keuze - 1).thema;

            System.out.println("Kies een van onderstaande vragenlijsten binnen het thema : " + thema);
            List<VragenlijstDTO> vragenlijsten = Database.getVragenlijsten();

            int j = 0;
            while (j < vragenlijsten.size()) {
                VragenlijstDTO vragenlijst = vragenlijsten.get(j);
                if(vragenlijst.thema.thema.equals(thema)) {
                    System.out.println("[" + (j + 1) + "] " + vragenlijst.naam + " | Prijs: " + vragenlijst.prijs);
                    j++;
                }
            }

            selection = getMenuChoice();

            switch (selection) {
                case 1:
                    return quizMenu(quiz);
                case 2:
                    return quizMenu(quiz);
                case 3:
                    return quizMenu(quiz);
                case 4:
                    return quizMenu(quiz);
                default:
                    System.out.println("The selection was invalid!");
            }
        } while (selection != 4);
        return quiz;
    }

    private VagadoQuiz quizKeuzeMenu(VagadoQuiz quiz){
        displayHeader("Vragenlijst keuze");

        int selection = 0;

        do {
            System.out.println("Kies een van onderstaande vragenlijsten om een quiz te starten");
            List vragenlijsten = Database.getGebruikers();
            int i = 0;

            while (i < vragenlijsten.size()) {
                System.out.println("[" + (i + 1) + "] " );
                i++;

            }

            selection = getMenuChoice();

            switch (selection) {
                case 1:
                    return quizMenu(quiz);
                case 2:
                    return quizMenu(quiz);
                case 3:
                    return quizMenu(quiz);
                case 4:
                    return quizMenu(quiz);
                default:
                    System.out.println("The selection was invalid!");
            }
        }
        while (selection != 4);

        return quiz;
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

    private void displayHeader(String message) {
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
}
