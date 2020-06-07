package ooad.DTO;

import java.util.List;

public class QuizDTO {
    public int quizId;
    public SpelerDTO speler;
    public SpelerVragenlijstDTO vragenlijst;
    public List<QuizRondeDTO> rondes;
    public int punten;
    public long tijd;

    public QuizDTO(int quizId, SpelerDTO speler, SpelerVragenlijstDTO vragenlijst, List<QuizRondeDTO> rondes) {
        this.quizId = quizId;
        this.speler = speler;
        this.vragenlijst = vragenlijst;
        this.rondes = rondes;
    }
}
