package ooad.DTO;

import java.util.Date;
import java.util.List;

public class GebruikersVragenlijstDTO {
    public String naam;
    public List<VraagDTO> vragen;
    public ThemaDTO thema;
    public int lifeTimeBest;
    public Date aanschafDatum;

    public GebruikersVragenlijstDTO(String naam, List<VraagDTO> vragen, ThemaDTO thema) {
        this.naam = naam;
        this.vragen = vragen;
        this.thema = thema;
        this.aanschafDatum = new Date();
    }

}
