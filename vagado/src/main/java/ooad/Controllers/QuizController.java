package ooad.Controllers;

import ooad.DTO.GebruikerDTO;
import ooad.DTO.QuizDTO;
import ooad.DTO.VragenlijstDTO;
import ooad.Services.PuntenA;
import ooad.Services.PuntenService;
import ooad.Services.QuizService;


import java.util.List;

public class QuizController {
    private QuizService quizService;

    public QuizController(){

    }

    public QuizDTO speelQuiz(GebruikerDTO gebruiker, VragenlijstDTO vragenlijst){
        QuizDTO quiz = quizService.speelQuiz(gebruiker, vragenlijst);
        PuntenService puntenService = new PuntenA();
        return quiz;
    }

    public List<VragenlijstDTO> getVragenlijsten(GebruikerDTO gebruiker){
        List<VragenlijstDTO> vragenlijsten = quizService.getVragenlijsten(gebruiker);
        return vragenlijsten;
    }
}
