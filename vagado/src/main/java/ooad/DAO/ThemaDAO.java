package ooad.DAO;

import ooad.DTO.ThemaDTO;
import ooad.Database.Database;

import java.util.List;

public class ThemaDAO {
    public List<ThemaDTO> getThemas() {
        return Database.themas;
    }
}
