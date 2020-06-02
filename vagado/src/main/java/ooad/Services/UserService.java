package ooad.Services;

import ooad.DAO.GebruikerDAO;
import ooad.DTO.GebruikerDTO;

public class UserService {
    private GebruikerDAO gebruikerDAO;

    public UserService(GebruikerDAO gebruikerDAO) {
        this.gebruikerDAO = gebruikerDAO;
    }

    public GebruikerDTO registreerGebruiker(String gebruikersnaam, String wachtwoord) {
        var user = gebruikerDAO.registreerGebruiker(gebruikersnaam, wachtwoord);

        if (user == null) {
            System.out.println("Registreren gefaald, Gebruikersnaam bestaat al.");
        } else {
            System.out.println("Gebruiker geregistreerd!");
            return user;
        }

        return user;
    }

    public GebruikerDTO loginGebruiker(String gebruikersnaam, String wachtwoord) {
        var user = gebruikerDAO.loginGebruiker(gebruikersnaam, wachtwoord);

        if (user == null) {
            System.out.println("Inloggen gefaald.");
        } else {
            return user;
        }

        return user;
    }
}
