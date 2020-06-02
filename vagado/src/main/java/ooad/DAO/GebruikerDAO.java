package ooad.DAO;

import ooad.DTO.GebruikerDTO;
import ooad.Database.Database;
import java.util.stream.Collectors;

public class GebruikerDAO {

    public GebruikerDTO registreerGebruiker(String gebruikersnaam, String wachtwoord) {
        if (Database.gebruikers.stream().anyMatch((item) -> item.gebruikersnaam.equals(gebruikersnaam))) {
            return null;
        } else {
            int saldo = 100;
            var gebruiker = new GebruikerDTO(gebruikersnaam, wachtwoord, saldo);

            gebruiker.vragenlijsten.add(Database.vragenlijsten.get(0));
            gebruiker.vragenlijsten.add(Database.vragenlijsten.get(6));

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
