package ooad.DTO;

import java.util.ArrayList;
import java.util.List;

public class SpelerDTO {
    public String gebruikersnaam;
    public String wachtwoord;
    public int saldo;
    public List<SpelerVragenlijstDTO> vragenlijsten;

    public SpelerDTO(String gebruikersnaam, String wachtwoord, int saldo) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.saldo = saldo;
        this.vragenlijsten = new ArrayList<>();
    }
}
