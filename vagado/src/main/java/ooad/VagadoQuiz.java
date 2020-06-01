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

import java.util.ArrayList;
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
                registreren();
                break;
            case 2:
                inloggen();
                break;
        }
    }

    private static void registreren(){
        String gebruikersnaam = askQuestion("Gebruikersnaam: ", null);
        String wachtwoord = askQuestion("Wachtwoord: ", null);

        userController.registreerGebruiker(gebruikersnaam, wachtwoord);

        mainMenu();
    }

    private static void inloggen(){
        String gebruikersnaam = askQuestion("Gebruikersnaam: ", null);
        String wachtwoord = askQuestion("Wachtwoord: ", null);

        var gebruiker = userController.loginGebruiker(gebruikersnaam, wachtwoord);

        if(gebruiker != null){
            quizMenu(gebruiker);
        }else{
            mainMenu();
        }
    }

    private static void quizMenu(GebruikerDTO gebruiker){
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
                vagadoShop(gebruiker);
                break;
            case 3:
                quiz(gebruiker);
                break;
            case 4:
                mainMenu();
                break;
        }
    }

    private static void vagadoShop(GebruikerDTO gebruiker){
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

        quizMenu(gebruiker);
    }

    private static void quiz(GebruikerDTO gebruiker){
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


            if(vraag.type == 0){
                //Open vraag
                var antwoord = askQuestion(vraag.vraag, null);
                quiz.geefAntwoord(spel.quizId, ronde.rondeNummer, antwoord);

                System.out.println("punten: " + ronde.punten);
            }else{
                // meerkeuze vraag
                var opties = new ArrayList<String>();
                for(int j=0; j<vraag.opties.size(); j++){
                   // System.out.println("[ " + j + " ] " + vraag.opties.get(j).optie);
                    opties.add(vraag.opties.get(j).optie);
                }

                //var antwoord = "A";


                var antwoord = askQuestion(vraag.vraag, opties);

                System.out.println("gegeven antwoord : " + antwoord);

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
               System.out.println();
               for (int i = 0; i < answers.size(); i++) {
                   System.out.println("[ " + i + " ] " + answers.get(i));
               }
               var input = -1;

               try {
                   input = Integer.parseInt(keyboard.nextLine());
               } catch (NumberFormatException e) {
                   System.out.println("Ongeldig karakter, alleen nummers alstublieft.");
               }

               if(input >= 0 && input < answers.size()){
                   response = answers.get(input);
               }
           }
           firstRun = false;
           if (!choices) {
               response = keyboard.nextLine();
               break;
           }
       } while (!answers.contains(response));
       return response;
   }
}
