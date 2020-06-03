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

    public void SetupDatabase() {
        addThemas();
        addVragenlijsten();
    }

    private void addThemas() {
        themas.add(new ThemaDTO("Muziek"));
        themas.add(new ThemaDTO("Sport"));
        themas.add(new ThemaDTO("Rekenen"));
    }

    private void addVragenlijsten() {
        var muziek1 = new ArrayList<VraagDTO>();

        muziek1.add(addMeerkeuzeVraag("Wat is geen bijnaam van Madonna?", Arrays.asList("Queen of pop", "Madge", "Maddy", "Mo"), "Maddy"));
        muziek1.add(addMeerkeuzeVraag("Waarvan is CD de afkorting?", Arrays.asList("Computal disc", "Classic disc", "Compact disc", "Connection disc"), "Compact disc"));
        muziek1.add(addMeerkeuzeVraag("In welk jaar overleed Michael Jackson?", Arrays.asList("2007", "2008", "2009", "2010"), "2009"));
        muziek1.add(addMeerkeuzeVraag("Hoeveel snaren heeft een gitaar gewoonlijk?", Arrays.asList("5", "6", "7", "8"), "6"));
        muziek1.add(addMeerkeuzeVraag("Wie componeerde “Fur Elise”?", Arrays.asList("Beethoven", "Mahler", "Mozart", "Schubert"), "Beethoven"));
        muziek1.add(addMeerkeuzeVraag("Hoeveel zwarte toetsen heeft een standaardpiano?", Arrays.asList("35", "36", "38", "40"), "36"));

        muziek1.add(addOpenVraag("Wie won in 2003 het eerste seizoen van Idols?", Arrays.asList("Jamai", "jamai")));
        muziek1.add(addOpenVraag("Welke band stond in 2003 in de hitparade met het nummer 'Seven Nation Army'?", Arrays.asList("The White Stripes", "White Stripes")));
        muziek1.add(addOpenVraag("Welke zangeres wordt ook wel J-lo genoemd?", Arrays.asList("jennifer lopez", "Jennifer Lopez")));
        muziek1.add(addOpenVraag("Wie staat bekend als 'The King of Reggae'?", Arrays.asList("bob marley", "Bob Marley")));
        muziek1.add(addOpenVraag("Via welke website is Justin Bieber ontdekt?", Arrays.asList("youtube", "Youtube")));
        muziek1.add(addOpenVraag("Welke zangeres bleef 2 weken lang in de tophits met ‘Umberella’?", Arrays.asList("Rihana", "rihana")));

        vragenlijsten.add(new VragenlijstDTO("Muziek - Trivia", muziek1, themas.get(0), 50));


        vragenlijsten.add(new VragenlijstDTO("Muziek - Dance", vragen, themas.get(0), 50));
        vragenlijsten.add(new VragenlijstDTO("Muziek - Top 2000", vragen, themas.get(0), 50));

        vragenlijsten.add(new VragenlijstDTO("Sport - Voetbal", vragen, themas.get(1), 50));
        vragenlijsten.add(new VragenlijstDTO("Sport - Ateletiek", vragen, themas.get(1), 50));
        vragenlijsten.add(new VragenlijstDTO("Sport - Overig", vragen, themas.get(1), 50));


        var rekenenOptellen = new ArrayList<VraagDTO>();

        rekenenOptellen.add(addMeerkeuzeVraag("Wat is de som van 8, 1 en 3", Arrays.asList("4", "11", "12", "24"), "12"));

        rekenenOptellen.add(addOpenVraag("Wat is 5 + 5", Arrays.asList("10", "tien", "Tien")));
        rekenenOptellen.add(addOpenVraag("Wat is 1 + 5", Arrays.asList("6", "zes", "Zes")));
        rekenenOptellen.add(addOpenVraag("Wat is 3 + 15", Arrays.asList("18", "achttien", "Achttien")));
        rekenenOptellen.add(addOpenVraag("Wat is 2 + 9", Arrays.asList("11", "elf", "Elf")));
        rekenenOptellen.add(addOpenVraag("Wat is 0 + 8", Arrays.asList("8", "acht", "Acht")));
        rekenenOptellen.add(addOpenVraag("Wat is 2 + 7", Arrays.asList("9", "negen", "Negen")));
        rekenenOptellen.add(addOpenVraag("Wat is 4 + 15", Arrays.asList("19", "negentien", "Negentien")));
        rekenenOptellen.add(addOpenVraag("Wat is 2 + 10", Arrays.asList("12", "twaalf", "Twaalf")));
        rekenenOptellen.add(addOpenVraag("Wat is 12 + 8", Arrays.asList("20", "twintig", "Twintig")));
        rekenenOptellen.add(addOpenVraag("Wat is 9 + 7", Arrays.asList("16", "zestien", "Zestien")));

        vragenlijsten.add(new VragenlijstDTO("Rekenen - Optellen", rekenenOptellen, themas.get(2), 50));

        vragenlijsten.add(new VragenlijstDTO("Rekenen - Aftrekken", vragen, themas.get(2), 50));

    }

    private VraagDTO addMeerkeuzeVraag(String vraag, List<String> opties, String antwoord) {
        List<AntwoordDTO> antwoordList = new ArrayList<>();
        List<OptieDTO> optiesList = new ArrayList<>();
        for (String item : opties) {
            optiesList.add(new OptieDTO(item));
        }
        antwoordList.add(new AntwoordDTO(antwoord));

        var vraagDTO = new VraagDTO(vraag, antwoordList, optiesList);
        vragen.add(vraagDTO);

        return vraagDTO;
    }

    private VraagDTO addOpenVraag(String vraag, List<String> antwoorden) {
        List<AntwoordDTO> antwoordList = new ArrayList<>();
        for (String item : antwoorden) {
            antwoordList.add(new AntwoordDTO(item));
        }

        var vraagDTO = new VraagDTO(vraag, antwoordList);
        vragen.add(vraagDTO);
        return vraagDTO;
    }
}
