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

    public List<VragenlijstDTO> getVragenlijsten(SpelerDTO speler, ThemaDTO thema) {
        var vragenlijsten = speler.vragenlijsten;

        var namen = new ArrayList<String>();

        for (SpelerVragenlijstDTO vragenlijst : vragenlijsten) {
            namen.add(vragenlijst.naam);
        }

        return Database.vragenlijsten.stream().filter((item) -> item.thema.equals(thema)).filter((item) -> !namen.contains(item.naam)).collect(Collectors.toList());
    }

    public void koopVragenLijst(VragenlijstDTO vragenlijst, SpelerDTO speler) {
        var spelers = Database.spelers;

        spelers.stream().filter((item) -> item.gebruikersnaam.equals(speler.gebruikersnaam)).forEach(g -> g.vragenlijsten.add(new SpelerVragenlijstDTO(vragenlijst.naam, vragenlijst.vragen, vragenlijst.thema)));
        spelers.stream().filter((item) -> item.gebruikersnaam.equals(speler.gebruikersnaam)).forEach(g -> g.saldo -= vragenlijst.prijs);
    }

}
