package ooad.DAO;

import ooad.DTO.*;
import ooad.Database.Database;

import javax.xml.crypto.Data;
import javax.xml.stream.events.DTD;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuizDAO {

    public QuizDTO speelQuiz(GebruikerDTO gebruiker, VragenlijstDTO vragenlijst){
        //todo fix quizId
        var quizId = 1;
        var rondes = genereerRondes(quizId, vragenlijst);

        var quiz = new QuizDTO(quizId, gebruiker, vragenlijst, rondes);
        Database.quizen.add(quiz);

       return quiz;
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

        var rondes = quiz.get(0).rondes;

        var ronde = rondes.stream().filter((item)-> item.rondeNummer == rondeNummer).collect(Collectors.toList()).get(0);

        //Antwoord in database
        ronde.gegevenAntwoordDTO = antwoord;

        var antwoorden = ronde.vraag.antwoord;

        //Check of gegeven antwoord is juist
        for (AntwoordDTO antwoordDTO : antwoorden) {
            if (antwoord.antwoord.equals(antwoordDTO.antwoord)) {
                ronde.punten = 1;
            }
        }
    }
}
