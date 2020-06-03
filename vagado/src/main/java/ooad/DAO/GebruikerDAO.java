package ooad.DAO;

import ooad.DTO.GebruikerDTO;
import ooad.DTO.GebruikersVragenlijstDTO;
import ooad.Database.Database;

import java.util.stream.Collectors;

public class GebruikerDAO {

    public GebruikerDTO registreerGebruiker(String gebruikersnaam, String wachtwoord) {
        if (Database.gebruikers.stream().anyMatch((item) -> item.gebruikersnaam.equals(gebruikersnaam))) {
            return null;
        } else {
            int saldo = 100;
            var gebruiker = new GebruikerDTO(gebruikersnaam, wachtwoord, saldo);

            var vragenlijst = Database.vragenlijsten.get(0);
            gebruiker.vragenlijsten.add(new GebruikersVragenlijstDTO(vragenlijst.naam, vragenlijst.vragen, vragenlijst.thema));

            var vragenlijst1 = Database.vragenlijsten.get(6);
            gebruiker.vragenlijsten.add(new GebruikersVragenlijstDTO(vragenlijst1.naam, vragenlijst1.vragen, vragenlijst1.thema));

            Database.gebruikers.add(gebruiker);

            return gebruiker;
        }
    }

    public GebruikerDTO loginGebruiker(String gebruikersnaam, String wachtwoord) {
        var gebruiker = Database.gebruikers.stream().filter((item) -> item.gebruikersnaam.equals(gebruikersnaam) && item.wachtwoord.equals(wachtwoord)).collect(Collectors.toList());

        if (gebruiker.size() > 0) {
            return gebruiker.get(0);
        } else {
            return null;
        }
    }
}
