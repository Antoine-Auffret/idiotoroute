package idiotoroute;

import java.util.Vector;

public class Road {

    private int id;
    private int nbExit;
    public Vector<Vehicule> vehicule;

    Road(int id, int nbExit) {
        if (nbExit>= 2 && nbExit<=6) {
            this.id = id;
            this.nbExit = nbExit;
            vehicule = new Vector<Vehicule>();
        }
        else
            throw new IllegalArgumentException("nbExit must be between 2 and 6");
    }

    public void addVehiculeOnRoad(Vehicule vehiculeOnRoad) { vehicule.add(vehiculeOnRoad); }

    public void removeVehiculeOnRoad(Vehicule vehiculeOnRoad) {
        vehicule.remove(vehiculeOnRoad);
    }

    @Override
    public String toString(){
        return "Road:" + id + ", nbExit:" + nbExit + ", Vehicule" + vehicule;
    }
}
