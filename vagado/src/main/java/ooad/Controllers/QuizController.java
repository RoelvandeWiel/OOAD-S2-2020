package ooad.Controllers;

import ooad.DTO.*;
import ooad.Services.QuizService;


public class QuizController {
    private QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    public QuizDTO startQuiz(GebruikerDTO gebruiker, VragenlijstDTO vragenlijst) {
        QuizDTO quiz = quizService.speelQuiz(gebruiker, vragenlijst);
        return quiz;
    }

    public void geefAntwoord(int quizId, int ronde, String antwoord){
        GegevenAntwoordDTO gegevenAntwoord = new GegevenAntwoordDTO(antwoord);
        quizService.geefAntwoord(quizId, ronde, gegevenAntwoord);
    }

    public void berekenPunten() {

    }
}
