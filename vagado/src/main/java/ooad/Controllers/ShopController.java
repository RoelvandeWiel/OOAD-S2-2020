package ooad.Controllers;

import ooad.DTO.SpelerDTO;
import ooad.DTO.ThemaDTO;
import ooad.DTO.VragenlijstDTO;
import ooad.Services.ShopService;

import java.util.List;

public class ShopController {
    private ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    public List<ThemaDTO> getThemas() {
        return shopService.getThemas();
    }

    public List<VragenlijstDTO> getVragenLijsten(SpelerDTO gebruiker, ThemaDTO thema) {
        return shopService.getVragenLijsten(gebruiker, thema);
    }

    public void koopVragenLijst(VragenlijstDTO vragenlijst, SpelerDTO gebruiker) {
        shopService.koopVragenLijst(vragenlijst, gebruiker);
    }

}
