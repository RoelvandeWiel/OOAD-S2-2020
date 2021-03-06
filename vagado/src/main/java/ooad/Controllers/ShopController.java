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

    public List<VragenlijstDTO> getVragenLijsten(SpelerDTO speler, ThemaDTO thema) {
        return shopService.getVragenLijsten(speler, thema);
    }

    public void koopVragenLijst(VragenlijstDTO vragenlijst, SpelerDTO speler) {
        shopService.koopVragenLijst(vragenlijst, speler);
    }

}
