package ooad.Controllers;

import ooad.DTO.*;
import ooad.Services.PuntenService;
import ooad.Services.QuizService;

public class QuizController {
    private QuizService quizService;
    private PuntenService puntenService;

    public QuizController(QuizService quizService, PuntenService puntenService) {
        this.quizService = quizService;
        this.puntenService = puntenService;
    }

    public QuizDTO startQuiz(SpelerDTO speler, SpelerVragenlijstDTO vragenlijst) {
        return quizService.startQuiz(speler, vragenlijst);
    }

    public void geefAntwoord(int quizId, int ronde, String antwoord) {
        GegevenAntwoordDTO gegevenAntwoord = new GegevenAntwoordDTO(antwoord);
        quizService.geefAntwoord(quizId, ronde, gegevenAntwoord);
    }

    public void saveRondeTijd(int quizId, int ronde, long tijd) {
        quizService.saveRondeTijd(quizId, ronde, tijd);
    }

    public void berekenPunten(int quizId) {
        int aantalVragenGoed = quizService.aantalVragenGoed(quizId);
        long tijd = quizService.berekenTijd(quizId);

        int punten = puntenService.berekenPunten(aantalVragenGoed, tijd);

        quizService.savePunten(quizId, punten);
    }
}
