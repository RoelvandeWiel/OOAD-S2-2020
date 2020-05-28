package ooad.Services;

import ooad.DTO.QuizDTO;
import ooad.DTO.QuizRondeDTO;
import ooad.DTO.VragenlijstDTO;

import java.util.ArrayList;
import java.util.List;

public class QuizService {

    public List<VragenlijstDTO> getVragenlijsten(){
        return new ArrayList<VragenlijstDTO>();
    }

    public QuizDTO speelQuiz(){
        return new QuizDTO();
    }
    
    public QuizRondeDTO getRonde(){
        return new QuizRondeDTO();
    }
}

