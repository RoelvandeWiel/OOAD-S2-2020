package ooad.Database;

import java.util.List;

public class VraagDTO {
    public String vraag;
    public byte type;
    public List<AntwoordDTO> antwoord;
    public List<OptieDTO> opties;

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
