package ooad.Controllers;

import ooad.DTO.GebruikerDTO;
import ooad.DTO.QuizDTO;
import ooad.DTO.ThemaDTO;
import ooad.DTO.VragenlijstDTO;
import ooad.Services.PuntenA;
import ooad.Services.PuntenService;
import ooad.Services.QuizService;


import java.util.List;

public class QuizController {
    private QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    public QuizDTO speelQuiz(GebruikerDTO gebruiker, VragenlijstDTO vragenlijst) {
        QuizDTO quiz = quizService.speelQuiz(gebruiker, vragenlijst);
        //PuntenService puntenService = new PuntenA();
        return quiz;
    }
}
