package ooad.Services;

import ooad.DAO.QuizDAO;
import ooad.DAO.VragenLijstDAO;
import ooad.DTO.*;

import java.util.List;

public class QuizService {

    private VragenLijstDAO vragenlijstDAO;
    private QuizDAO quizDAO;

    public QuizService(){

    }

    public List<VragenlijstDTO> getVragenlijsten(GebruikerDTO gebruiker){
        return vragenlijstDAO.getVragenlijstenFromGebruiker(gebruiker);
    }

    public QuizDTO speelQuiz(GebruikerDTO gebruiker, VragenlijstDTO vragenlijst){
        return quizDAO.speelQuiz(gebruiker, vragenlijst);
    }

    public QuizRondeDTO getRonde(QuizDTO quiz){

        return new QuizRondeDTO();
    }
}

