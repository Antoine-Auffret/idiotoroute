package idiotoroute;

public class Vehicule {

    private int noSerie;
    private int niveauEssence = 10;
    private String type;
    private Boolean isCrashed = false;

    private static int noSerieCompteur = 0;

    Vehicule(int essence, String type) {
        setEssence(essence);
        this.type = type;
        noSerie = noSerieCompteur++;
    }

    public void rouler() throws VehiculeException {
        if (verifierEssence())
            niveauEssence--;
        else if(!isCrashed) {
            isCrashed = true;
            throw new VehiculeException(this);
        }
    }

    private void setEssence(int essence) {
        niveauEssence = essence;
        if (niveauEssence < 0)
            niveauEssence = 0;
    }

    private boolean verifierEssence() {
        return niveauEssence > 0;
    }

    @Override
    public String toString(){
        return "(NS:" + noSerie + ", ESS:" + niveauEssence + ") " + type;
    }
}
