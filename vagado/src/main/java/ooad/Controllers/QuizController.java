package ooad.Controllers;

import ooad.DTO.GebruikerDTO;
import ooad.DTO.QuizDTO;
import ooad.DTO.VragenlijstDTO;
import ooad.Services.PuntenA;
import ooad.Services.PuntenService;

import java.util.ArrayList;
import java.util.List;

public class QuizController {

    public QuizDTO speelQuiz(GebruikerDTO gebruiker, VragenlijstDTO vragenlijst){

        PuntenService puntenService = new PuntenA();
        return new QuizDTO();
    }

    public List<VragenlijstDTO> getVragenlijsten(GebruikerDTO gebruiker){
        return new ArrayList<VragenlijstDTO>();
    }
}
