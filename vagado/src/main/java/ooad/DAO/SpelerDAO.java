package ooad.DAO;

import ooad.DTO.SpelerDTO;
import ooad.DTO.SpelerVragenlijstDTO;
import ooad.Database.Database;

import java.util.stream.Collectors;

public class SpelerDAO {

    public SpelerDTO registreer(String gebruikersnaam, String wachtwoord) {
        if (Database.spelers.stream().anyMatch((item) -> item.gebruikersnaam.equals(gebruikersnaam))) {
            return null;
        } else {
            int saldo = 100;

            var vragenlijst = Database.vragenlijsten.get(0);
            var vragenlijst1 = Database.vragenlijsten.get(6);

            var speler = new SpelerDTO(gebruikersnaam, wachtwoord, saldo);

            speler.vragenlijsten.add(new SpelerVragenlijstDTO(vragenlijst.naam, vragenlijst.vragen, vragenlijst.thema));
            speler.vragenlijsten.add(new SpelerVragenlijstDTO(vragenlijst1.naam, vragenlijst1.vragen, vragenlijst1.thema));

            Database.spelers.add(speler);

            return speler;
        }
    }

    public SpelerDTO login(String gebruikersnaam, String wachtwoord) {
        var gebruiker = Database.spelers.stream().filter((item) -> item.gebruikersnaam.equals(gebruikersnaam) && item.wachtwoord.equals(wachtwoord)).collect(Collectors.toList());

        if (gebruiker.size() > 0) {
            return gebruiker.get(0);
        } else {
            return null;
        }
    }
}
