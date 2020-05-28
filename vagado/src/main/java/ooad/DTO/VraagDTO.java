package ooad.DTO;

import java.util.List;

public class VraagDTO {
    private String vraag;
    private byte type;
    private List<AntwoordDTO> antwoord;
    private List<OptieDTO> opties;

    public VraagDTO(String vraag, List<AntwoordDTO> antwoorden) {
        this.vraag = vraag;
        this.type = 0;
        this.antwoord = antwoorden;
    }

    public VraagDTO(String vraag, List<AntwoordDTO> antwoorden, List<OptieDTO> opties) {
        this.vraag = vraag;
        this.type = 1;
        this.antwoord = antwoorden;
        this.opties = opties;
    }
}
