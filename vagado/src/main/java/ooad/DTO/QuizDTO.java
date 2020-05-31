package ooad.DTO;

import java.util.List;

public class QuizDTO {
    public int quizId;
    public GebruikerDTO gebruiker;
    public VragenlijstDTO vragenlijst;
    public List<QuizRondeDTO> rondes;
    public int punten;
    public int tijd;

    public QuizDTO(int quizId, GebruikerDTO gebruiker, VragenlijstDTO vragenlijst, List<QuizRondeDTO> rondes){
        this.quizId = quizId;
        this.gebruiker = gebruiker;
        this.vragenlijst = vragenlijst;
        this.rondes = rondes;
    }
}
