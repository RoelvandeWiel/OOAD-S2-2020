package ooad.Services;

import ooad.DAO.GebruikerDAO;
import ooad.DTO.GebruikerDTO;

public class UserService {
    private GebruikerDAO gebruikerDAO;

    public UserService(GebruikerDAO gebruikerDAO){
        this.gebruikerDAO = gebruikerDAO;
    }

    public void registreerGebruiker(String gebruikersnaam, String wachtwoord) {
        gebruikerDAO.registreerGebruiker(gebruikersnaam, wachtwoord);
    }

    public GebruikerDTO loginGebruiker(String gebruikersnaam, String wachtwoord) {
        //todo: Fix de exception
        var user = gebruikerDAO.loginGebruiker(gebruikersnaam, wachtwoord);

        if (user == null) {
           // throw new Exception();
        } else {
            return user;
        }

        return user;
    }
}
