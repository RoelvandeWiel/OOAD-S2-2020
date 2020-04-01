package ooad.Database;

import java.util.List;

public class GebruikerDTO {
    public String gebruikersnaam;
    public String wachtwoord;
    public int saldo;
    public List<VragenlijstDTO> vragenlijsten;

    public GebruikerDTO(String gebruikersnaam, String wachtwoord, int saldo) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.saldo = saldo;
    }
}
