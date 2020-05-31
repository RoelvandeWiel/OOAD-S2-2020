package ooad.DTO;

public class QuizRondeDTO {
    public int nummer;
    public VraagDTO vraag;
    public GegevenAntwoordDTO gegevenAntwoordDTO;
    public int punten;
    public int tijd;

    public QuizRondeDTO(int nummer, VraagDTO vraag){
        this.nummer = nummer;
        this.vraag = vraag;
    }
}
