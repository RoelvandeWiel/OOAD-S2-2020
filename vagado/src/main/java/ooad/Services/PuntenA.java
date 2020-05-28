package ooad.Services;

public class PuntenA implements  PuntenService{
    int aantalJuisteAntwoorden = 8;
    @Override
    public int berekenPunten() {
        return aantalJuisteAntwoorden * 2;
    }
}
