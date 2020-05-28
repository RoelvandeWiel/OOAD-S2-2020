package ooad.Services;

public class PuntenB implements PuntenService{
    int aantalJuisteAntwoorden = 8;

    @Override
    public int berekenPunten() {
        return aantalJuisteAntwoorden;
    }
}
