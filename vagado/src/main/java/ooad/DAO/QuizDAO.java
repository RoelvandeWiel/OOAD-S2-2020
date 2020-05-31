package ooad.DAO;

import ooad.DTO.GebruikerDTO;
import ooad.DTO.QuizDTO;
import ooad.DTO.QuizRondeDTO;
import ooad.DTO.VragenlijstDTO;

import java.util.ArrayList;
import java.util.List;

public class QuizDAO {

    public QuizDTO speelQuiz(GebruikerDTO gebruiker, VragenlijstDTO vragenlijst){
        //todo implement method

        var rondes = genereerRondes(vragenlijst);

       return new QuizDTO(gebruiker, vragenlijst, rondes);
    }

    private List<QuizRondeDTO> genereerRondes(VragenlijstDTO vragenlijst){
        //todo Randomize vragen
        var rondes = new ArrayList<QuizRondeDTO>();
        var vragen = vragenlijst.vragen;

        for(int i = 0; i < 10; i++){
            rondes.add(new QuizRondeDTO(i, vragen.get(i)));
        }

        return rondes;
    }

}
