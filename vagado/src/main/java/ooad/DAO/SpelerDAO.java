package ooad.DAO;

import ooad.DTO.SpelerDTO;
import ooad.DTO.SpelerVragenlijstDTO;
import ooad.Database.Database;

import java.util.stream.Collectors;

public class SpelerDAO {

    public SpelerDTO registreerGebruiker(String gebruikersnaam, String wachtwoord) {
        if (Database.gebruikers.stream().anyMatch((item) -> item.gebruikersnaam.equals(gebruikersnaam))) {
            return null;
        } else {
            int saldo = 100;
            var gebruiker = new SpelerDTO(gebruikersnaam, wachtwoord, saldo);

            var vragenlijst = Database.vragenlijsten.get(0);
            gebruiker.vragenlijsten.add(new SpelerVragenlijstDTO(vragenlijst.naam, vragenlijst.vragen, vragenlijst.thema));

            var vragenlijst1 = Database.vragenlijsten.get(6);
            gebruiker.vragenlijsten.add(new SpelerVragenlijstDTO(vragenlijst1.naam, vragenlijst1.vragen, vragenlijst1.thema));

            Database.gebruikers.add(gebruiker);

            return gebruiker;
        }
    }

    public SpelerDTO loginGebruiker(String gebruikersnaam, String wachtwoord) {
        var gebruiker = Database.gebruikers.stream().filter((item) -> item.gebruikersnaam.equals(gebruikersnaam) && item.wachtwoord.equals(wachtwoord)).collect(Collectors.toList());

        if (gebruiker.size() > 0) {
            return gebruiker.get(0);
        } else {
            return null;
        }
    }
}
