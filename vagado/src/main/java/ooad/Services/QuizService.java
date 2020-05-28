package ooad.Services;

import ooad.DTO.GebruikerDTO;
import ooad.DTO.QuizDTO;
import ooad.DTO.QuizRondeDTO;
import ooad.DTO.VragenlijstDTO;

import java.util.ArrayList;
import java.util.List;

public class QuizService {

    public List<VragenlijstDTO> getVragenlijsten(GebruikerDTO gebruiker){
        return new ArrayList<VragenlijstDTO>();
    }

    public QuizDTO speelQuiz(GebruikerDTO gebruiker, VragenlijstDTO vragenlijst){
        return new QuizDTO();
    }

    public QuizRondeDTO getRonde(QuizDTO quiz){
        return new QuizRondeDTO();
    }
}

