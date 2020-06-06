package ooad.Services;

import ooad.DAO.SpelerDAO;
import ooad.DTO.SpelerDTO;

public class UserService {
    private SpelerDAO spelerDAO;

    public UserService(SpelerDAO spelerDAO) {
        this.spelerDAO = spelerDAO;
    }

    public SpelerDTO registreerGebruiker(String gebruikersnaam, String wachtwoord) {
        var user = spelerDAO.registreerGebruiker(gebruikersnaam, wachtwoord);

        if (user == null) {
            System.out.println("Registreren gefaald, Gebruikersnaam bestaat al.");
        } else {
            System.out.println("Gebruiker geregistreerd!");
            return user;
        }

        return user;
    }

    public SpelerDTO loginGebruiker(String gebruikersnaam, String wachtwoord) {
        var user = spelerDAO.loginGebruiker(gebruikersnaam, wachtwoord);

        if (user == null) {
            System.out.println("Inloggen gefaald.");
        } else {
            return user;
        }

        return user;
    }
}
