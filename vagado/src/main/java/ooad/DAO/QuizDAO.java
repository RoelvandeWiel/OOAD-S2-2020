package ooad.DAO;

import ooad.DTO.*;
import ooad.Database.Database;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuizDAO {

    public QuizDTO speelQuiz(GebruikerDTO gebruiker, VragenlijstDTO vragenlijst){
        //todo fix quizId
        var quizId = 1;
        var rondes = genereerRondes(quizId, vragenlijst);

       return new QuizDTO(quizId, gebruiker, vragenlijst, rondes);
    }

    private List<QuizRondeDTO> genereerRondes(int quizId, VragenlijstDTO vragenlijst){
        //todo Randomize vragen
        var rondes = new ArrayList<QuizRondeDTO>();
        var vragen = vragenlijst.vragen;

        for(int i = 0; i < 10; i++){
            rondes.add(new QuizRondeDTO(quizId, i, vragen.get(i)));
        }

        return rondes;
    }

    public void geefAntoord(int quizId, int rondeNummer, GegevenAntwoordDTO antwoord) {
        var quiz = Database.quizen.stream().filter((item) -> item.quizId == quizId).collect(Collectors.toList());

        quiz.forEach((quizDTO -> quizDTO.rondes.stream().filter(ronde-> ronde.rondeNummer == rondeNummer).forEach(ronde -> ronde.gegevenAntwoordDTO = antwoord)));
    }
}
