package ooad.Database;

import ooad.DTO.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    public static List<GebruikerDTO> gebruikers = new ArrayList<>();
    public static List<VragenlijstDTO> vragenlijsten = new ArrayList<>();
    public static List<ThemaDTO> themas = new ArrayList<>();
    public static List<VraagDTO> vragen = new ArrayList<>();
    public static List<QuizDTO> quizen = new ArrayList<>();

    public void SetupDatabase(){
        AddVragen();
        AddThemas();
        AddVragenlijsten();
    }

    private void AddVragenlijsten(){
        vragenlijsten.add(new VragenlijstDTO("Muziek 1", vragen, themas.get(0), 50));
    }

    public static List getVragenlijsten(){
        return vragenlijsten;
    }


    private void AddThemas(){
        themas.add(new ThemaDTO("Muziek"));
        themas.add(new ThemaDTO("Sport"));
        themas.add(new ThemaDTO("Rekenen"));
    }

    public static List getThemas(){
        return themas;
    }

    public static List getGebruikers(){
        return gebruikers;
    }

    private void AddVragen(){

        //Sport

        //Muziek
        addMeerkeuzeVraag("Wat is geen bijnaam van Madonna?", Arrays.asList("Queen of pop", "Madge", "Maddy", "Mo"), "Maddy");
        addMeerkeuzeVraag("Waarvan is CD de afkorting?", Arrays.asList("Computal disc", "Classic disc", "Compact disc", "Connection disc"), "Compact disc");
        addMeerkeuzeVraag("In welk jaar overleed Michael Jackson?", Arrays.asList("2007", "2008", "2009", "2010"), "2009");
        addMeerkeuzeVraag("Hoeveel snaren heeft een gitaar gewoonlijk?", Arrays.asList("5", "6", "7", "8"), "6");
        addMeerkeuzeVraag("Wie componeerde “Fur Elise”?", Arrays.asList("Beethoven", "Mahler", "Mozart", "Schubert"), "Beethoven");

        addOpenVraag("Wie won in 2003 het eerste seizoen van Idols?", Arrays.asList("Jamai", "jamai"));
        addOpenVraag("Welke band stond in 2003 in de hitparade met het nummer 'Seven Nation Army'?", Arrays.asList("The White Stripes", "White Stripes"));
        addOpenVraag("Welke zangeres wordt ook wel J-lo genoemd?", Arrays.asList("jennifer lopez", "Jennifer Lopez"));
        addOpenVraag("Wie staat bekend als 'The King of Reggae'?", Arrays.asList("bob marley", "Bob Marley"));
        addOpenVraag("Via welke website is Justin Bieber ontdekt?", Arrays.asList("youtube", "Youtube"));


        //Rekenen

    }

    private void addMeerkeuzeVraag(String vraag, List<String> opties, String antwoord){
        List<AntwoordDTO> antwoordList = new ArrayList<>();
        List<OptieDTO> optiesList = new ArrayList<>();
        for (String item: opties) {
            optiesList.add(new OptieDTO(item));
        }
        antwoordList.add(new AntwoordDTO(antwoord));
        vragen.add(new VraagDTO(vraag, antwoordList, optiesList));
    }

    private void addOpenVraag(String vraag, List<String> antwoorden){
        List<AntwoordDTO> antwoordList = new ArrayList<>();
        for (String item: antwoorden)
        {
            antwoordList.add(new AntwoordDTO(item));
        }
        vragen.add(new VraagDTO(vraag, antwoordList));

    }

    public void RegistreerGebruiker(String gebruikersnaam, String wachtwoord) {
        int saldo = 100;

        gebruikers.add(new GebruikerDTO(gebruikersnaam, wachtwoord, saldo));
    }
}
