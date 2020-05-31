package ooad.DTO;

import java.util.List;

public class QuizDTO {
    public GebruikerDTO gebruiker;
    public VragenlijstDTO vragenlijst;
    public List<QuizRondeDTO> rondes;
    public int punten;
    public int tijd;

    public QuizDTO(GebruikerDTO gebruiker, VragenlijstDTO vragenlijst, List<QuizRondeDTO> rondes){
        this.gebruiker = gebruiker;
        this.vragenlijst = vragenlijst;
        this.rondes = rondes;
    }
}
