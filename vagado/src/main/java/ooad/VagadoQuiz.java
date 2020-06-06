package ooad;

import ooad.Controllers.QuizController;
import ooad.Controllers.ShopController;
import ooad.Controllers.UserController;
import ooad.DAO.SpelerDAO;
import ooad.DAO.QuizDAO;
import ooad.DAO.ThemaDAO;
import ooad.DAO.VragenLijstDAO;
import ooad.DTO.SpelerDTO;
import ooad.DTO.SpelerVragenlijstDTO;
import ooad.DTO.ThemaDTO;
import ooad.Database.Database;
import ooad.Services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.time.StopWatch;


public class VagadoQuiz {
    private static Database database = new Database();

    private static VragenLijstDAO vragenlijstDAO = new VragenLijstDAO();
    private static ThemaDAO themaDAO = new ThemaDAO();
    private static ShopService shopService = new ShopService(vragenlijstDAO, themaDAO);
    private static ShopController shop = new ShopController(shopService);

    private static SpelerDAO spelerDAO = new SpelerDAO();
    private static UserService userService = new UserService(spelerDAO);
    private static UserController userController = new UserController(userService);

    private static QuizDAO quizDAO = new QuizDAO();
    private static QuizService quizService = new QuizService(quizDAO);
    private static PuntenService puntenService = new PuntenA();
    private static QuizController quiz = new QuizController(quizService, puntenService);

    public static void main(String[] args) {
        database.SetupDatabase();

        mainMenu();
    }

    private static void mainMenu() {
        displayHeader("Welkom bij Vagado");

        System.out.println("[ 1 ] Registreren");
        System.out.println("[ 2 ] Inloggen");


        int menuKeuze = getMenuChoice(2);

        switch (menuKeuze) {
            case 1:
                registreren();
                break;
            case 2:
                inloggen();
                break;
        }
    }

    private static void registreren() {
        String gebruikersnaam = askQuestion("Gebruikersnaam: ", null);
        String wachtwoord = askQuestion("Wachtwoord: ", null);

        var speler = userController.registreer(gebruikersnaam, wachtwoord);

        if (speler != null) {
            mainMenu();
        } else {
            System.out.println("Probeer opnieuw:");
            registreren();
        }
    }

    private static void inloggen() {
        String gebruikersnaam = askQuestion("Gebruikersnaam: ", null);
        String wachtwoord = askQuestion("Wachtwoord: ", null);

        var speler = userController.login(gebruikersnaam, wachtwoord);

        if (speler != null) {
            quizMenu(speler);
        } else {
            System.out.println("Probeer opnieuw:");
            inloggen();
        }
    }

    private static void quizMenu(SpelerDTO speler) {
        displayHeader("Quizmenu");

        System.out.println("[ 1 ] Profiel");
        System.out.println("[ 2 ] Vegado Shop");
        System.out.println("[ 3 ] Speel Quiz");
        System.out.println("[ 4 ] Uitloggen");

        int menuKeuze = getMenuChoice(4);

        switch (menuKeuze) {
            case 1:
                profiel(speler);
                break;
            case 2:
                vagadoShopMain(speler);
                break;
            case 3:
                quiz(speler);
                break;
            case 4:
                mainMenu();
                break;
        }
    }

    private static void profiel(SpelerDTO speler) {
        displayHeader("Profiel - " + speler.gebruikersnaam);

        System.out.println("Saldo : " + speler.saldo);
        System.out.println("Vragenlijsten in bezit : " + speler.vragenlijsten.size());

        System.out.println("[ 1 ] Terug");
        int menuKeuze = getMenuChoice(1);

        if (menuKeuze == 1) {
            quizMenu(speler);
        }
    }

    private static void vagadoShopMain(SpelerDTO speler) {
        displayHeader("Vagado shop");

        System.out.println("[ 1 ] Thema");
        System.out.println("[ 2 ] Terug");

        int menuKeuze = getMenuChoice(2);

        switch (menuKeuze) {
            case 1:
                vagadoShopThema(speler);
                break;
            case 2:
                quizMenu(speler);
                break;
        }
    }

    private static void vagadoShopThema(SpelerDTO speler) {
        displayHeader("Thema's");

        var themas = shop.getThemas();

        for (int i = 1; i <= themas.size(); i++) {
            System.out.println("[ " + i + " ] " + themas.get(i - 1).thema);
        }

        System.out.println("[ " + (themas.size() + 1) + " ] Terug");

        int themaKeuze = getMenuChoice(themas.size() + 1) - 1;

        if (themaKeuze < themas.size()) {
            vagadoShopVragenlijsten(speler, themas, themaKeuze);
        } else {
            vagadoShopMain(speler);
        }
    }

    private static void vagadoShopVragenlijsten(SpelerDTO speler, List<ThemaDTO> themas, int thema) {
        displayHeader("Vragenlijsten - Thema: " + themas.get(thema).thema);

        var vragenLijsten = shop.getVragenLijsten(speler, themas.get(thema));

        for (int i = 1; i <= vragenLijsten.size(); i++) {
            System.out.println("[ " + i + " ] " + vragenLijsten.get(i - 1).naam + " | kosten: " + vragenLijsten.get(i - 1).prijs);
        }

        System.out.println("[ " + (vragenLijsten.size() + 1) + " ] Terug");

        int vragenlijstKeuze = getMenuChoice(vragenLijsten.size() + 1) - 1;

        if (vragenlijstKeuze < vragenLijsten.size()) {
            shop.koopVragenLijst(vragenLijsten.get(vragenlijstKeuze), speler);

            System.out.println("Nieuwe saldo is: " + speler.saldo);

            quizMenu(speler);
        } else {
            vagadoShopThema(speler);
        }
    }

    private static void quiz(SpelerDTO speler) {
        displayHeader("Vagado quiz");

        System.out.println("Kies een vragenlijst waar je mee wilt spelen");
        var gebruikerVragenlijsten = speler.vragenlijsten;

        for (int i = 1; i <= gebruikerVragenlijsten.size(); i++) {
            System.out.println("[ " + i + " ] " + gebruikerVragenlijsten.get(i - 1).naam + " | LifeTimeBest: " + gebruikerVragenlijsten.get(i - 1).lifeTimeBest);
        }

        System.out.println("[ " + (gebruikerVragenlijsten.size() + 1) + " ] Terug");

        int spelVragenlijstKeuze = getMenuChoice(gebruikerVragenlijsten.size() + 1) - 1;

        if (spelVragenlijstKeuze < gebruikerVragenlijsten.size()) {
            speelQuiz(speler, gebruikerVragenlijsten, spelVragenlijstKeuze);
        } else {
            quizMenu(speler);
        }
    }

    private static void speelQuiz(SpelerDTO speler, List<SpelerVragenlijstDTO> vragenlijsten, int keuze) {
        var timer = new StopWatch();
        System.out.println();
        System.out.println("Start quiz: " + vragenlijsten.get(keuze).naam);
        System.out.println();

        var spel = quiz.startQuiz(speler, vragenlijsten.get(keuze));
        var rondes = spel.rondes;

        for (int i = 0; i < rondes.size(); i++) {
            var ronde = rondes.get(i);
            var vraag = ronde.vraag;

            System.out.println("Ronde " + (i + 1));
            timer.start();
            var antwoord = "";

            if (vraag.type == 0) {
                //Open vraag
                antwoord = askQuestion(vraag.vraag, null);
            } else {
                //Meerkeuze vraag
                var opties = new ArrayList<String>();
                for (int j = 0; j < vraag.opties.size(); j++) {
                    opties.add(vraag.opties.get(j).optie);
                }
                antwoord = askQuestion(vraag.vraag, opties);
            }

            quiz.geefAntwoord(spel.quizId, ronde.rondeNummer, antwoord);
            timer.stop();

            quiz.saveRondeTijd(spel.quizId, ronde.rondeNummer, timer.getTime());
            timer.reset();

            if (ronde.punten == 1) {
                System.out.println("GOED");
            } else {
                System.out.println("FOUT");
            }

            System.out.println();
        }

        quiz.berekenPunten(spel.quizId);

        System.out.println("Bedankt voor het spelen van de quiz");
        System.out.println("Gefeliciteerd! U heeft " + spel.punten + " punten behaald!");
        System.out.println("U deed " + (spel.tijd / 1000) + " seconden over de quiz.");

        quizMenu(speler);
    }

    private static int getMenuChoice(int aantalKeuzes) {
        Scanner keyboard = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.print("Voer alstublieft uw keuze in: ");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ongeldig karakter, alleen nummers alstublieft.");
            }
            if (choice <= 0 || choice > aantalKeuzes) {
                System.out.println("Keuze is niet mogelijk, probeer een andere optie.");
            }
        } while (choice <= 0 || choice > aantalKeuzes);
        return choice;
    }

    private static void displayHeader(String message) {
        System.out.println();
        int width = message.length() + 6;
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for (int i = 0; i < width; ++i) {
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
        boolean choices = (answers != null) && answers.size() != 0;
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

                if (input >= 0 && input < answers.size()) {
                    response = answers.get(input);
                }
            }
            firstRun = false;
            if (!choices) {
                System.out.println();
                response = keyboard.nextLine();
                break;
            }
        } while (!answers.contains(response));
        return response;
    }
}
