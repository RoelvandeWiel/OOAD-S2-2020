package ooad.Database;

import java.util.ArrayList;
import java.util.List;

public class Database {
    public List<GebruikerDTO> gebruikers = new ArrayList<>();
    public List<VragenlijstDTO> vragenlijsten = new ArrayList<>();
    public List<ThemaDTO> themas = new ArrayList<>();
    public List<VraagDTO> vragen = new ArrayList<>();
    public  List<QuizDTO> quizen = new ArrayList<>();

    public void SetupDatabase(){
        AddThemas();
    }

    private void AddThemas(){
        themas.add(new ThemaDTO("Sport"));
        themas.add(new ThemaDTO("Muziek"));
        themas.add(new ThemaDTO("Rekenen"));
    }
    private void AddVragen(){
        //Sport
        List<OptieDTO> opties = new ArrayList<OptieDTO>();
        opties.add(new OptieDTO("Marco van Basten"));
        opties.add(new OptieDTO("Louis van Gaal"));
        opties.add(new OptieDTO("Guus Hiddink"));
        opties.add(new OptieDTO("Bert van Marwijk"));

        vragen.add(new VraagDTO("Wie was de bondscoach van het Nederlands Eftal tijdens het WK voetbal van 2010 in Zuid-Afrika?"),
                   new ArrayList<AntwoordDTO>().add(new AntwoordDTO("Bert van Marwijk")),
                   opties);
        //Muziek
        List<AntwoordDTO> antwoorden = new ArrayList<AntwoordDTO>();
        antwoorden.add(new AntwoordDTO("The White Stripes"));
        antwoorden.add(new AntwoordDTO("White Stripes"));
        vragen.add(new VraagDTO("Welke band stond in 2003 in de hitparade met het nummer 'Seven Nation Army'", antwoorden));

        //Rekenen

    }

    public void RegistreerGebruiker(String gebruikersnaam, String wachtwoord) {
        int saldo = 100;

        gebruikers.add(new GebruikerDTO(gebruikersnaam, wachtwoord, saldo));
    }
}
