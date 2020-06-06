package ooad.Services;

import ooad.DAO.SpelerDAO;
import ooad.DTO.SpelerDTO;

public class UserService {
    private SpelerDAO spelerDAO;

    public UserService(SpelerDAO spelerDAO) {
        this.spelerDAO = spelerDAO;
    }

    public SpelerDTO registreer(String gebruikersnaam, String wachtwoord) {
        var user = spelerDAO.registreer(gebruikersnaam, wachtwoord);

        if (user == null) {
            System.out.println("Registreren gefaald, Gebruikersnaam bestaat al.");
        } else {
            System.out.println("Gebruiker geregistreerd!");
            return user;
        }

        return user;
    }

    public SpelerDTO login(String gebruikersnaam, String wachtwoord) {
        var user = spelerDAO.login(gebruikersnaam, wachtwoord);

        if (user == null) {
            System.out.println("Inloggen gefaald.");
        } else {
            return user;
        }

        return user;
    }
}
