package ooad.DAO;

import ooad.DTO.SpelerDTO;
import ooad.DTO.SpelerVragenlijstDTO;
import ooad.DTO.ThemaDTO;
import ooad.DTO.VragenlijstDTO;
import ooad.Database.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VragenLijstDAO {

    public List<VragenlijstDTO> getVragenlijsten(SpelerDTO gebruiker, ThemaDTO thema) {
        var vragenlijsten = gebruiker.vragenlijsten;

        var namen = new ArrayList<String>();

        for (SpelerVragenlijstDTO vragenlijst : vragenlijsten) {
            namen.add(vragenlijst.naam);
        }

        return Database.vragenlijsten.stream().filter((item) -> item.thema.equals(thema)).filter((item) -> !namen.contains(item.naam)).collect(Collectors.toList());
    }

    public void koopVragenLijst(VragenlijstDTO vragenlijst, SpelerDTO gebruiker) {
        var gebruikers = Database.gebruikers;

        gebruikers.stream().filter((item) -> item.gebruikersnaam.equals(gebruiker.gebruikersnaam)).forEach(g -> g.vragenlijsten.add(new SpelerVragenlijstDTO(vragenlijst.naam, vragenlijst.vragen, vragenlijst.thema)));
        gebruikers.stream().filter((item) -> item.gebruikersnaam.equals(gebruiker.gebruikersnaam)).forEach(g -> g.saldo -= vragenlijst.prijs);
    }

}
