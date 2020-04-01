package ooad.Database;

import java.util.List;

public class Database {
    public List<GebruikerDTO> gebruikers;

    public void SetUser(String gebruikersnaam, String wachtwoord) {
        int saldo = 100;

        gebruikers.add(new GebruikerDTO(gebruikersnaam, wachtwoord, saldo));
    }
}
