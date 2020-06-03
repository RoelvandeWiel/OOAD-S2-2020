package ooad.DAO;

import ooad.DTO.GebruikerDTO;
import ooad.DTO.GebruikersVragenlijstDTO;
import ooad.DTO.ThemaDTO;
import ooad.DTO.VragenlijstDTO;
import ooad.Database.Database;

import java.util.List;
import java.util.stream.Collectors;

public class VragenLijstDAO {

    public List<VragenlijstDTO> getVragenlijsten(GebruikerDTO gebruiker, ThemaDTO thema) {
        //todo: filter op lijsten die de gebruiker nog niet heeft

        var vragenlijsten = gebruiker.vragenlijsten;

        return Database.vragenlijsten.stream().filter((item) -> item.thema.equals(thema)).collect(Collectors.toList());
    }

    public List<VragenlijstDTO> getVragenlijstenShop(GebruikerDTO gebruiker, ThemaDTO thema, GebruikersVragenlijstDTO gebruikersVragenlijstDTO) {
        var collection = Database.vragenlijsten.stream().filter((item) -> item.thema.equals(thema)).collect(Collectors.toList());

        collection.stream().filter(i -> i.naam.equals(gebruikersVragenlijstDTO.naam)).collect(Collectors.toList());

        return collection;
    }


    public void koopVragenLijst(VragenlijstDTO vragenlijst, GebruikerDTO gebruiker) {
        var gebruikers = Database.gebruikers;

        //todo: Kunnen deze twee functie samen ??
        gebruikers.stream().filter((item) -> item.gebruikersnaam.equals(gebruiker.gebruikersnaam)).forEach(g -> g.vragenlijsten.add(new GebruikersVragenlijstDTO(vragenlijst.naam, vragenlijst.vragen, vragenlijst.thema)));
        gebruikers.stream().filter((item) -> item.gebruikersnaam.equals(gebruiker.gebruikersnaam)).forEach(g -> g.saldo -= vragenlijst.prijs);

        //todo: Zie casus -> een vragenlijst heeft een geldigheidsduur van 1 jaar. kijken wat hier nog mee moet
    }

}
