package ooad.Database;

import java.util.ArrayList;
import java.util.List;

public class ThemaDTO {
    public static String thema;
    public static List themas = new ArrayList();

    ThemaDTO(String thema){
        this.thema = thema;
        themas.add(thema);
    }

    public static String getThema(String thema) {
        return thema;
    }

    public static List getThemas(){
        return themas;
    }
}
