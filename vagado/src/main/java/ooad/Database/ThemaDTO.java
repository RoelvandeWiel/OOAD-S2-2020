package ooad.Database;

import java.util.ArrayList;
import java.util.List;

public class ThemaDTO {
    private static String thema;
    private static List themas = new ArrayList();

    ThemaDTO(String thema){
        ThemaDTO.thema = thema;
        themas.add(thema);
    }

    public static String getThema() {
        return thema;
    }

    public static List getThemas(){
        return themas;
    }
}
