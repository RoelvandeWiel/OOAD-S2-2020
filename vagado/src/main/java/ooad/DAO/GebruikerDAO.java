package ooad.DAO;

import ooad.DTO.GebruikerDTO;
import ooad.Database.Database;

import java.util.Collection;
import java.util.stream.Collectors;

public class GebruikerDAO {

    public void registreerGebruiker(String gebruikersnaam, String wachtwoord) {
        //todo: inbouwen checks of de gebruiker al bestaat etc.
        // todo: twee gratis vragenlijsten toevoegen bij registreren
        int saldo = 150;
        var gebruiker = new GebruikerDTO(gebruikersnaam, wachtwoord, saldo);

        Database.gebruikers.add(gebruiker);
    }

    public GebruikerDTO loginGebruiker(String gebruikersnaam, String wachtwoord) {
        var gebruiker = Database.gebruikers.stream().filter(u -> u.gebruikersnaam.equals(gebruikersnaam) && u.wachtwoord.equals(wachtwoord)).collect(Collectors.toList());

        if(gebruiker.size() > 0){
            return gebruiker.get(0);
        }else{
            return null;
        }
    }
}
