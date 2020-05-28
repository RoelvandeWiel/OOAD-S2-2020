package ooad.Services;

import ooad.DAO.QuizDAO;
import ooad.DAO.VragenLijstDAO;
import ooad.DTO.GebruikerDTO;
import ooad.DTO.QuizDTO;
import ooad.DTO.QuizRondeDTO;
import ooad.DTO.VragenlijstDTO;

import java.util.List;

public class QuizService {

    private VragenLijstDAO vragenlijstDAO;
    private QuizDAO quizDAO;

    public QuizService(){

    }

    public List<VragenlijstDTO> getVragenlijsten(GebruikerDTO gebruiker){
        return vragenlijstDAO.getVragenlijsten(gebruiker);
    }

    public QuizDTO speelQuiz(GebruikerDTO gebruiker, VragenlijstDTO vragenlijst){
        return quizDAO.speelQuiz(gebruiker, vragenlijst);
    }

    public QuizRondeDTO getRonde(QuizDTO quiz){

        return new QuizRondeDTO();
    }
}

