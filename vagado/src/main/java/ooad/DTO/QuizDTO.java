package ooad.DTO;

import java.util.List;

public class QuizDTO {
    public int quizId;
    public SpelerDTO gebruiker;
    public SpelerVragenlijstDTO vragenlijst;
    public List<QuizRondeDTO> rondes;
    public int punten;
    public long tijd;

    public QuizDTO(int quizId, SpelerDTO gebruiker, SpelerVragenlijstDTO vragenlijst, List<QuizRondeDTO> rondes) {
        this.quizId = quizId;
        this.gebruiker = gebruiker;
        this.vragenlijst = vragenlijst;
        this.rondes = rondes;
    }
}
