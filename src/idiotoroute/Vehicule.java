package idiotoroute;

public class Vehicule {

    private int noSerie;
    private int niveauEssence;
    private double pos = 0.0;
    private double speed;
    private static double originalSpeed;
    private String type;
    private Boolean isCrashed = false;
    private static int noSerieCompteur = 0;

    Vehicule(int essence, String type, double speed) {
        // Initialisation des attributs d'un véhicule avec les attributs des classes VehiculeX héritées
        setEssence(essence);
        this.type = type;
        this.speed = speed;
        this.originalSpeed = speed;
        // Incrémentation du numéro de série pour chaque instanciation d'un nouveau véhicule
        this.noSerie = noSerieCompteur++;
    }

    public int getNoSerie() { return noSerie; }

    public double getPos() { return pos; }

    public void setPos(double pos) { this.pos = pos; }

    public String getType() { return type; }

    public double getSpeed() { return speed; }

    public double getOriginalSpeed() { return originalSpeed; }

    public void setSpeed(double speed) { this.speed = speed; }

    public void setCrashed(Boolean crashed) { isCrashed = crashed; }

    public void rouler() throws VehiculeEssenceException {
        // Décrémentation de l'essence et incrémentation de la postion si le véhicule peut encore rouler
        if (verifierEssence() && !isCrashed) {
            niveauEssence--;
            this.pos += speed;
        }
        // Si le véhicule n'a plus d'essence, renvoyer une seule exception
        else if (!isCrashed){
            isCrashed = true;
            throw new VehiculeEssenceException(this);
        }
    }

    // Renvoie une exception si deux véhicules se rentre dedans
    public void crash(Vehicule vehicule1, Vehicule vehicule2) throws VehiculeCollisionException {
        throw new VehiculeCollisionException(vehicule1, vehicule2);
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
