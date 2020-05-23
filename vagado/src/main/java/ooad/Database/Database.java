package ooad.Database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    public List<GebruikerDTO> gebruikers = new ArrayList<>();
    public List<VragenlijstDTO> vragenlijsten = new ArrayList<>();
    public List<ThemaDTO> themas = new ArrayList<>();
    public List<VraagDTO> vragen = new ArrayList<>();
    public  List<QuizDTO> quizen = new ArrayList<>();

    public void SetupDatabase(){
        AddThemas();
        AddVragen();
    }

    private void AddThemas(){
        themas.add(new ThemaDTO("Sport"));
        themas.add(new ThemaDTO("Muziek"));
        themas.add(new ThemaDTO("Rekenen"));
    }
    private void AddVragen(){

        //Sport
        List<OptieDTO> opties = new ArrayList<OptieDTO>();
        List<AntwoordDTO> antwoord = new ArrayList<AntwoordDTO>();
        List<AntwoordDTO> antwoorden = new ArrayList<AntwoordDTO>();

        opties.add(new OptieDTO("Marco van Basten"));
        opties.add(new OptieDTO("Louis van Gaal"));
        opties.add(new OptieDTO("Guus Hiddink"));
        opties.add(new OptieDTO("Bert van Marwijk"));
        antwoord.add(new AntwoordDTO("Bert van Marwijk"));
        vragen.add(new VraagDTO("Wie was de bondscoach van het Nederlands Eftal tijdens het WK voetbal van 2010 in Zuid-Afrika?", antwoord, opties));
        antwoord.clear();
        opties.clear();

        //Muziek
        addMeerkeuzeVraag("Wat is geen bijnaam van Madonna?", Arrays.asList("Queen of pop", "Madge", "Maddy", "Mo"), "Maddy");
        addMeerkeuzeVraag("Waarvan is CD de afkorting?", Arrays.asList("Computal disc", "Classic disc", "Compact disc", "Connection disc"), "Compact disc");
        addMeerkeuzeVraag("In welk jaar overleed Michael Jackson?", Arrays.asList("2007", "2008", "2009", "2010"), "2009");
        addMeerkeuzeVraag("Hoeveel snaren heeft een gitaar gewoonlijk?", Arrays.asList("5", "6", "7", "8"), "6");
        addMeerkeuzeVraag("Wie componeerde “Fur Elise”?", Arrays.asList("Beethoven", "Mahler", "Mozart", "Schubert"), "Beethoven");
        
        antwoorden.add(new AntwoordDTO("The White Stripes"));
        antwoorden.add(new AntwoordDTO("White Stripes"));
        vragen.add(new VraagDTO("Welke band stond in 2003 in de hitparade met het nummer 'Seven Nation Army'", antwoorden));
        antwoorden.clear();
        antwoorden.add(new AntwoordDTO("jamai"));
        antwoorden.add(new AntwoordDTO("Jamai"));
        vragen.add(new VraagDTO("Wie won in 2003 het eerste seizoen van Idols?", antwoorden));
        antwoorden.clear();


        //Rekenen

        //4 open 1 meerkeuze

    }


    public void addMeerkeuzeVraag(String vraag, List<String> opties, String antwoord){
        List<AntwoordDTO> antwoordList = new ArrayList<>();
        List<OptieDTO> optiesList = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            optiesList.add(new OptieDTO(opties.get(i)));
        }
        antwoordList.add(new AntwoordDTO(antwoord));
        vragen.add(new VraagDTO(vraag, antwoordList, optiesList));
    }

    public void addOpenVraag(){

    }

    public void RegistreerGebruiker(String gebruikersnaam, String wachtwoord) {
        int saldo = 100;

        gebruikers.add(new GebruikerDTO(gebruikersnaam, wachtwoord, saldo));
    }
}
