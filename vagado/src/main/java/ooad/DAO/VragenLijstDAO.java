package ooad.DAO;

import ooad.DTO.GebruikerDTO;
import ooad.DTO.VragenlijstDTO;
import ooad.Database.Database;

import java.util.ArrayList;
import java.util.List;

public class VragenLijstDAO {
    private Database database = new Database();

    public List<VragenlijstDTO> getVragenlijsten(GebruikerDTO gebruiker){
        return database.vragenlijsten;
    }

    public void koopVragenLijst(VragenlijstDTO vragenlijst, GebruikerDTO gebruiker){



        database.koopVragenlijst(vragenlijst, gebruiker);
    }

}
