package idiotoroute;

public class Vehicule {

    private int niveauEssence = 10;
    private String type;

    Vehicule(int essence, String type) {
        setEssence(essence);
        this.type = type;
    }

    public void rouler() throws VehiculeException {
        if (verifierEssence())
            niveauEssence--;
        else
            throw new VehiculeException(this);
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
        return "Type:" + type + ", ESS:" + niveauEssence;
    }
}
