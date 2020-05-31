package ooad.DTO;

public class QuizRondeDTO {
    public int quizId;
    public int rondeNummer;
    public VraagDTO vraag;
    public GegevenAntwoordDTO gegevenAntwoordDTO;
    public int punten;
    public int tijd;

    public QuizRondeDTO(int quizId, int rondeNummer, VraagDTO vraag){
        this.quizId = quizId;
        this.rondeNummer = rondeNummer;
        this.vraag = vraag;
    }
}
