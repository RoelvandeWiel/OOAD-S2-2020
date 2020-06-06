package ooad.Services;

import ooad.DAO.ThemaDAO;
import ooad.DAO.VragenLijstDAO;
import ooad.DTO.SpelerDTO;
import ooad.DTO.ThemaDTO;
import ooad.DTO.VragenlijstDTO;

import java.util.List;

public class ShopService {
    private VragenLijstDAO vragenlijstDAO;
    private ThemaDAO themaDAO;

    public ShopService(VragenLijstDAO vragenlijstDAO, ThemaDAO themaDAO) {
        this.vragenlijstDAO = vragenlijstDAO;
        this.themaDAO = themaDAO;
    }

    public List<VragenlijstDTO> getVragenLijsten(SpelerDTO speler, ThemaDTO thema) {
        return vragenlijstDAO.getVragenlijsten(speler, thema);
    }

    public void koopVragenLijst(VragenlijstDTO vragenlijst, SpelerDTO speler) {
        vragenlijstDAO.koopVragenLijst(vragenlijst, speler);
    }

    public List<ThemaDTO> getThemas() {
        return themaDAO.getThemas();
    }
}
