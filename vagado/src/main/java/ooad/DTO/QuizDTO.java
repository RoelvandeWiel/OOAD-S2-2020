package ooad.DTO;

import java.util.List;

public class QuizDTO {
    public int quizId;
    public GebruikerDTO gebruiker;
    public GebruikersVragenlijstDTO vragenlijst;
    public List<QuizRondeDTO> rondes;
    public int punten;
    public long tijd;

    public QuizDTO(int quizId, GebruikerDTO gebruiker, GebruikersVragenlijstDTO vragenlijst, List<QuizRondeDTO> rondes) {
        this.quizId = quizId;
        this.gebruiker = gebruiker;
        this.vragenlijst = vragenlijst;
        this.rondes = rondes;
    }
}
