package ooad.DAO;

import com.google.gson.Gson;
import ooad.DTO.*;
import ooad.Database.Database;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuizDAO implements  Cloneable{

    public QuizDTO speelQuiz(GebruikerDTO gebruiker, GebruikersVragenlijstDTO vragenlijst) {
        var quizId = Database.quizen.size() + 1;
        var rondes = genereerRondes(quizId, vragenlijst);

        var quiz = new QuizDTO(quizId, gebruiker, vragenlijst, rondes);
        Database.quizen.add(quiz);

        return quiz;
    }

    private List<QuizRondeDTO> genereerRondes(int quizId, GebruikersVragenlijstDTO vragenlijst) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(vragenlijst);
        GebruikersVragenlijstDTO clone = gson.fromJson(jsonString, GebruikersVragenlijstDTO.class);

        var rondes = new ArrayList<QuizRondeDTO>();
        var vragenClone = clone.vragen;
        int quizSize = 10;

        for (int i = 0; i < quizSize; i++) {
            int rand = (int)(vragenClone.size() * Math.random());
            rondes.add(new QuizRondeDTO(quizId, i, vragenClone.get(rand)));
            vragenClone.remove(rand);
        }

        return rondes;
    }

    public void geefAntoord(int quizId, int rondeNummer, GegevenAntwoordDTO antwoord) {
        var quiz = Database.quizen.stream().filter((item) -> item.quizId == quizId).collect(Collectors.toList());

        var rondes = quiz.get(0).rondes;

        var ronde = rondes.stream().filter((item) -> item.rondeNummer == rondeNummer).collect(Collectors.toList()).get(0);

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

    public int aantalVragenGoed(int quizId) {
        var quiz = Database.quizen.stream().filter((item) -> item.quizId == quizId).collect(Collectors.toList());

        var rondes = quiz.get(0).rondes;

        int aantalGoed = 0;

        for (QuizRondeDTO ronde : rondes) {
            if (ronde.punten == 1) {
                aantalGoed++;
            }
        }

        if(aantalGoed == 10){
            quiz.get(0).gebruiker.saldo += 2;
        }

        return aantalGoed;
    }

    public void saveRondeTijd(int quizId, int rondeNummer, long tijd) {
        var quiz = Database.quizen.stream().filter((item) -> item.quizId == quizId).collect(Collectors.toList());

        var rondes = quiz.get(0).rondes;

        var ronde = rondes.stream().filter((item) -> item.rondeNummer == rondeNummer).collect(Collectors.toList()).get(0);

        //rondeTijd
        ronde.tijd = tijd;

        //Totale speeltijd
        quiz.get(0).tijd += tijd;
    }

    public long berekenTijd(int quizId) {
        var quiz = Database.quizen.stream().filter((item) -> item.quizId == quizId).collect(Collectors.toList());

        return quiz.get(0).tijd;
    }

    public void savePunten(int quizId, int punten) {
        var quiz = Database.quizen.stream().filter((item) -> item.quizId == quizId).collect(Collectors.toList());

        quiz.get(0).punten = punten;

        if(quiz.get(0).vragenlijst.lifeTimeBest < punten){
            quiz.get(0).vragenlijst.lifeTimeBest = punten;
        }
    }
}
