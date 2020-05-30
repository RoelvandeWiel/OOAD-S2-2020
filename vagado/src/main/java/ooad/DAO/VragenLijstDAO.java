package ooad.DAO;

import ooad.DTO.GebruikerDTO;
import ooad.DTO.VragenlijstDTO;
import ooad.Database.Database;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VragenLijstDAO {

    public List<VragenlijstDTO> getVragenlijsten(GebruikerDTO gebruiker){
        return Database.vragenlijsten;
    }

    public void koopVragenLijst(VragenlijstDTO vragenlijst, GebruikerDTO gebruiker){
        var gebruikers = Database.gebruikers;

        gebruikers.stream().filter((item) -> item.gebruikersnaam.equals(gebruiker.gebruikersnaam)).forEach(g -> g.vragenlijsten.add(vragenlijst));
    }
}
