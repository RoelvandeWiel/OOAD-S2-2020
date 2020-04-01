package ooad.Database;

import java.util.ArrayList;
import java.util.List;

public class Database {
    public List<GebruikerDTO> gebruikers = new ArrayList<>();

    public void RegistreerGebruiker(String gebruikersnaam, String wachtwoord) {
        int saldo = 100;

        gebruikers.add(new GebruikerDTO(gebruikersnaam, wachtwoord, saldo));
    }
}
