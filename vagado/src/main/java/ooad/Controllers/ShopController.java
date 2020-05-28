package ooad.Controllers;

import ooad.DTO.GebruikerDTO;
import ooad.DTO.VragenlijstDTO;
import ooad.Services.ShopService;

import java.util.List;

public class ShopController {
    private ShopService shopService;

    public ShopController(){

    }

    public List<VragenlijstDTO> getVragenLijsten(GebruikerDTO gebruiker){
        List<VragenlijstDTO> vragenlijsten = shopService.getVragenLijsten(gebruiker);

        return vragenlijsten;
    }

    public void koopVragenLijst(VragenlijstDTO vragenlijst, GebruikerDTO gebruiker){
        shopService.koopVragenLijst(vragenlijst, gebruiker);
    }

}
