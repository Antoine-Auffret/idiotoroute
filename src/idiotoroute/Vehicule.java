package idiotoroute;

public class Vehicule {

    private int noSerie;
    private int niveauEssence;
    private double pos = 0.0;
    private double speed;
    private String type;
    private Boolean isCrashed = false;

    private static int noSerieCompteur = 0;

    Vehicule(int essence, String type, double speed) {
        setEssence(essence);
        this.type = type;
        this.speed = speed;
        noSerie = noSerieCompteur++;
    }

    public double getPos() {
        return pos;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void rouler() throws VehiculeException {
        if (verifierEssence()) {
            niveauEssence--;
            this.pos = pos + speed;
        }
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
        return "(NS:" + noSerie + ", ESS:" + niveauEssence + ", POS:" + pos + ", SPD:" + speed + ") " + type;
    }
}
