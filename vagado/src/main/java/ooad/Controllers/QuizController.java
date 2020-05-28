package ooad.Controllers;

import ooad.DTO.QuizDTO;
import ooad.DTO.VragenlijstDTO;

import java.util.ArrayList;
import java.util.List;

public class QuizController {

    public QuizDTO speelQuiz(){
        return new QuizDTO();
    }

    public List<VragenlijstDTO> getVragenlijsten(){
        return new ArrayList<VragenlijstDTO>();
    }
}
