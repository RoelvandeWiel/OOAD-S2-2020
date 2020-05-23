package ooad.Database;

import java.util.List;

class VraagDTO {
    private String vraag;
    private byte type;
    private List<AntwoordDTO> antwoord;
    private List<OptieDTO> opties;

    VraagDTO(String vraag, List<AntwoordDTO> antwoorden) {
        this.vraag = vraag;
        this.type = 0;
        this.antwoord = antwoorden;
    }

    VraagDTO(String vraag, List<AntwoordDTO> antwoorden, List<OptieDTO> opties) {
        this.vraag = vraag;
        this.type = 1;
        this.antwoord = antwoorden;
        this.opties = opties;
    }
}
