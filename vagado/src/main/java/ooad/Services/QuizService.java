package ooad.Services;

import ooad.DAO.QuizDAO;
import ooad.DTO.*;

public class QuizService {

    private QuizDAO quizDAO;

    public QuizService(QuizDAO quizDAO) {
        this.quizDAO = quizDAO;
    }

    public QuizDTO startQuiz(SpelerDTO speler, SpelerVragenlijstDTO vragenlijst) {
        return quizDAO.startQuiz(speler, vragenlijst);
    }

    public void geefAntwoord(int quizId, int rondeNummer, GegevenAntwoordDTO antwoord) {
        quizDAO.geefAntwoord(quizId, rondeNummer, antwoord);
    }

    public int aantalVragenGoed(int quizId) {
        return quizDAO.aantalVragenGoed(quizId);
    }

    public void saveRondeTijd(int quizId, int ronde, long tijd) {
        quizDAO.saveRondeTijd(quizId, ronde, tijd);
    }

    public long berekenTijd(int quizId) {
        return quizDAO.berekenTijd(quizId);
    }

    public void savePunten(int quizId, int punten) {
        quizDAO.savePunten(quizId, punten);
    }
}

