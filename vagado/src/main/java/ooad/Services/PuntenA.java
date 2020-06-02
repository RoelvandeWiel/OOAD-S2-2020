package ooad.Services;

public class PuntenA implements  PuntenService{

    @Override
    public int berekenPunten(int aantalGoed, long tijd) {
        int punten = 0;

        punten += (aantalGoed*2);

        if(tijd < 60000){
            punten += 2;
        }

        if(tijd < 30000){
            punten += 5;
        }

        if(tijd < 10000){
            punten += 10;
        }

        if(aantalGoed == 10){
            punten += 5;
        }

        return punten;
    }
}
