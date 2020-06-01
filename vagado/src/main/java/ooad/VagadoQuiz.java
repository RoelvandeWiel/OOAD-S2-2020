package ooad;

import ooad.Controllers.QuizController;
import ooad.Controllers.ShopController;
import ooad.Controllers.UserController;
import ooad.DAO.GebruikerDAO;
import ooad.DAO.QuizDAO;
import ooad.DAO.ThemaDAO;
import ooad.DAO.VragenLijstDAO;
import ooad.DTO.GebruikerDTO;
import ooad.Database.Database;
import ooad.DTO.ThemaDTO;
import ooad.DTO.VragenlijstDTO;
import ooad.Services.QuizService;
import ooad.Services.ShopService;
import ooad.Services.UserService;

import java.util.List;
import java.util.Scanner;


public class VagadoQuiz {
    private boolean ingelogd = false;

    private static Database database = new Database();

    private static VragenLijstDAO vragenlijstDAO = new VragenLijstDAO();
    private static ThemaDAO themaDAO = new ThemaDAO();
    private static ShopService shopService = new ShopService(vragenlijstDAO, themaDAO);
    private static ShopController shop = new ShopController(shopService);

    private static GebruikerDAO gebruikerDAO = new GebruikerDAO();
    private static UserService userService = new UserService(gebruikerDAO);
    private static UserController userController = new UserController(userService);

    private static QuizDAO quizDAO = new QuizDAO();
    private static QuizService quizService = new QuizService(quizDAO);
    private static QuizController quiz = new QuizController(quizService);


    public static void main(String[] args) {
        database.SetupDatabase();

        mainMenu();
    }

    private static void mainMenu(){
        displayHeader("Welkom bij Vagado");

        System.out.println("[ 1 ] Registreren" );
        System.out.println("[ 2 ] Inloggen" );

        int mainMenuKeuze = getMenuChoice();

        switch (mainMenuKeuze) {
            case 1:
                String gebruikersnaam = askQuestion("Gebruikersnaam: ", null);
                String wachtwoord = askQuestion("Wachtwoord: ", null);

                userController.registreerGebruiker(gebruikersnaam, wachtwoord);

                mainMenu();
                break;
            case 2:
                String gebruikersnaam1 = askQuestion("Gebruikersnaam: ", null);
                String wachtwoord2 = askQuestion("Wachtwoord: ", null);

                var gebruiker = userController.loginGebruiker(gebruikersnaam1, wachtwoord2);

                    if(gebruiker != null){
                        QuizMenu(gebruiker);
                    }else{
                        mainMenu();
                    }
                break;
        }
    }

    private static void QuizMenu(GebruikerDTO gebruiker){
        displayHeader("Quizmenu");

        System.out.println("Ingelogd als: " + gebruiker.gebruikersnaam);

        System.out.println("[ 1 ] Profiel" );
        System.out.println("[ 2 ] Vegado Shop" );
        System.out.println("[ 3 ] Speel Quiz" );
        System.out.println("[ 4 ] Uitloggen" );

        int quizMenuKeuze = getMenuChoice();

        switch (quizMenuKeuze) {
            case 1:
                System.out.println("Test profiel");
                break;
            case 2:
                vegadoShop(gebruiker);
                break;
            case 3:
                quiz(gebruiker);
                break;
            case 4:
                mainMenu();
                break;
        }
    }

    public static void vegadoShop(GebruikerDTO gebruiker){
        displayHeader("Vagado shop");

        System.out.println("Kies een thema");

        var themas = shop.getThemas();

        for(int i=0;i<themas.size();i++){
            System.out.println("[ " + i +" ] " + themas.get(i).thema);
        }

        int themaKeuze = getMenuChoice();

        System.out.println("Kies een vragenlijsten van thema " + themas.get(themaKeuze).thema);

        var vragenLijsten = shop.getVragenLijsten(gebruiker, themas.get(themaKeuze));

        for(int i=0;i<vragenLijsten.size();i++){
            System.out.println("[ " + i +" ] " + vragenLijsten.get(i).naam + " | kosten: " + vragenLijsten.get(i).prijs);
        }

        int vragenlijstKeuze = getMenuChoice();

        shop.koopVragenLijst(vragenLijsten.get(vragenlijstKeuze), gebruiker);

        System.out.println("Nieuwe saldo is: " + gebruiker.saldo);
    }

    public static void quiz(GebruikerDTO gebruiker){
        displayHeader("Vagado quiz");

        System.out.println("Kies een vragenlijst waar je mee wilt spelen");
        var gebruikerVragenlijsten = gebruiker.vragenlijsten;

        for(int i=0;i<gebruikerVragenlijsten.size();i++){
            System.out.println("[ " + i +" ] " + gebruikerVragenlijsten.get(i).naam );
        }

        int spelVragenlijstKeuze = getMenuChoice();
        System.out.println();
        System.out.println("Start quiz: " + gebruikerVragenlijsten.get(spelVragenlijstKeuze).naam);
        System.out.println();

        var spel = quiz.speelQuiz(gebruiker, gebruikerVragenlijsten.get(spelVragenlijstKeuze));
        var rondes = spel.rondes;

        for(int i=0;i<rondes.size();i++){
            var ronde = rondes.get(i);
            var vraag = ronde.vraag;

            System.out.println("Ronde " + (i+1));

            System.out.println(vraag.vraag);

            if(vraag.type == 0){
                //Open vraag
                System.out.println("Vul u antwoord in......");
                System.out.println();
                var antwoord = "Dit is een open antwoord";

                quiz.geefAntwoord(spel.quizId, ronde.rondeNummer, antwoord);

                System.out.println("punten: " + ronde.punten);
            }else{
                // meerkeuze vraag
                for(int j=0; j<vraag.opties.size(); j++){
                    System.out.println("[ " + j + " ] " + vraag.opties.get(j).optie);
                }

                var antwoord = "A";

                quiz.geefAntwoord(spel.quizId, ronde.rondeNummer, antwoord);

                System.out.println("punten: " + ronde.punten);
            }
            System.out.println();
        }

        quiz.berekenPunten();

        System.out.println("Bedankt voor het spelen van de quiz");
        System.out.println("Gefeliciteerd! U heeft " + spel.punten + " behaald!");
        System.out.println("U deed " + spel.tijd + " minuten over de quiz.");
    }



   //private static void menu(String[] options) {
   //    int selection = 0;

   //    do{
   //        System.out.println("Kies een van de volgende opties.");
   //        for (int i=0; i<options.length; i++){
   //            System.out.println("[ " + (i+1) + " ] " + options[i]);
   //        }
   //        String input = new Scanner(System.in).nextLine();
   //        try {
   //            selection = Integer.parseInt(input);
   //        } catch (NumberFormatException e){
   //            System.out.println("Dit is geen valide optie : " + e);
   //            selection = 0;
   //        }
   //    }while(selection <= 0 || selection > options.length);

   //        switch (selection) {
   //            case 1:
   //                String gebruikersnaam = askQuestion("Gebruikersnaam: ", null);
   //                String wachtwoord = askQuestion("Wachtwoord: ", null);

   //                userController.registreerGebruiker(gebruikersnaam, wachtwoord);
   //                break;
   //            case 2:
   //                String gebruikersnaam1 = askQuestion("Gebruikersnaam: ", null);
   //                String wachtwoord2 = askQuestion("Wachtwoord: ", null);

   //                userController.loginGebruiker(gebruikersnaam1, wachtwoord2);
   //                break;
   //        }
   //}


    private VagadoQuiz mainMenu(VagadoQuiz quiz) {
        displayHeader("Vagado Quiz App");

        int selection = 0;

        do {
            System.out.println("[1] Registreren");
            System.out.println("[2] Inloggen");
            System.out.println("[3] Exit");

            selection = getMenuChoice();
            switch (selection) {
                case 1: return quiz;
                case 2: return quiz;
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

    private static int getMenuChoice() {
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

    private static void displayHeader(String message) {
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

   private static String askQuestion(String question, List<String> answers) {
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
