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

        //System.out.print(quiz.forEach((quizDTO -> quizDTO.rondes.stream().filter(ronde-> ronde.rondeNummer == rondeNummer).collect()
        // .forEach(ronde -> ronde.vraag.antwoord.forEach((antwoordDTO -> antwoordDTO.equals(antwoord)))))));



        quiz.forEach((quizDTO -> quizDTO.rondes.stream().filter(ronde-> ronde.rondeNummer == rondeNummer).forEach(ronde -> ronde.gegevenAntwoordDTO = antwoord)));

        //todo: check of antwoord goed is
        quiz.forEach((quizDTO -> quizDTO.rondes.stream().filter(ronde-> ronde.rondeNummer == rondeNummer).forEach(ronde -> ronde.punten = 10)));


        quiz.forEach((quizDTO -> quizDTO.rondes.stream().filter(ronde-> ronde.rondeNummer == rondeNummer).forEach(ronde -> ronde.punten = 10)));

        var goedantwoord = new ArrayList<>();


    }
    
    private boolean checkAntwoord(){
        //todo: implement

        return true;
    }
}
