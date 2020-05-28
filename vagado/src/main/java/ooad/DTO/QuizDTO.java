package ooad.DTO;

import java.util.List;

public class QuizDTO {
    public GebruikerDTO gebruiker;
    public VragenlijstDTO vragenlijst;
    public List<QuizRondeDTO> rondes;
    public int punten;
    public int tijd;
}
