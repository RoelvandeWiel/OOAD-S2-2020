package ooad.Services;

import ooad.DAO.QuizDAO;
import ooad.DAO.VragenLijstDAO;
import ooad.DTO.*;

public class QuizService {

    private VragenLijstDAO vragenlijstDAO;
    private QuizDAO quizDAO;

    public QuizService(QuizDAO quizDAO){
        this.quizDAO = quizDAO;
    }

    public QuizDTO speelQuiz(GebruikerDTO gebruiker, VragenlijstDTO vragenlijst){
        return quizDAO.speelQuiz(gebruiker, vragenlijst);
    }

    public void geefAntwoord(int quizId, int rondeNummer, GegevenAntwoordDTO antwoord) {
        quizDAO.geefAntoord(quizId, rondeNummer, antwoord);
    }

    //public QuizRondeDTO getRonde(QuizDTO quiz){

   //    return new QuizRondeDTO();
   //}
}

