package ooad.DTO;

import java.util.List;

public class VragenlijstDTO {
    public String naam;
    public List<VraagDTO> vragen;
    public ThemaDTO thema;
    public int prijs;

    VragenlijstDTO(String naam, List<VraagDTO> vragen, ThemaDTO thema,  int prijs){
        this.naam = naam;
        this.vragen = vragen;
        this.thema = thema;
        this.prijs = prijs;
    }
}
