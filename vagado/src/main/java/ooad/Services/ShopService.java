package ooad.Services;

import ooad.DAO.VragenLijstDAO;
import ooad.DTO.GebruikerDTO;
import ooad.DTO.VragenlijstDTO;

import java.util.List;

public class ShopService {
    private VragenLijstDAO vragenlijstDAO;

    public ShopService(){

    }

    public List<VragenlijstDTO> getVragenLijsten(GebruikerDTO gebruiker){
        return vragenlijstDAO.getVragenlijsten(gebruiker);
    }

    public void koopVragenLijst(VragenlijstDTO vragenlijst, GebruikerDTO gebruiker){
        vragenlijstDAO.koopVragenLijst(vragenlijst, gebruiker);
    }

}
