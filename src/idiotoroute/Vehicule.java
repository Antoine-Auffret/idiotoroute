package idiotoroute;

public class Vehicule {

    private int noSerie;
    private int niveauEssence;
    private double pos = 10.0;
    private double speed;
    private double originalSpeed;
    private String type;
    private Boolean isCrashed = false;

    private static int noSerieCompteur = 0;

    Vehicule(int essence, String type, double speed) {
        setEssence(essence);
        this.type = type;
        this.speed = speed;
        this.originalSpeed = speed;
        noSerie = noSerieCompteur++;
    }

    public double getPos() {
        return pos;
    }

    public void setPos(double pos) {
        this.pos = pos;
    }

    public double getSpeed() {
        return speed;
    }

    public double getOriginalSpeed() {
        return originalSpeed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void rouler() throws VehiculeException {
        if (verifierEssence()) {
            niveauEssence--;
            this.pos += speed;
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
