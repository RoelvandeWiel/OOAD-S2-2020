package ooad.Services;

import ooad.DAO.ThemaDAO;
import ooad.DAO.VragenLijstDAO;
import ooad.DTO.GebruikerDTO;
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

    public List<VragenlijstDTO> getVragenLijsten(GebruikerDTO gebruiker, ThemaDTO thema) {
        return vragenlijstDAO.getVragenlijsten(gebruiker, thema);
    }

    public void koopVragenLijst(VragenlijstDTO vragenlijst, GebruikerDTO gebruiker) {
        vragenlijstDAO.koopVragenLijst(vragenlijst, gebruiker);
    }

    public List<ThemaDTO> getThemas() {
        return themaDAO.getThemas();
    }
}
