package idiotoroute;

import java.util.Vector;

public class Road {

    private int id;
    private Vector<Vehicule> vehicule;

    Road(int id) {
        this.id = id;
        vehicule = new Vector<Vehicule>();
    }

    public void addVehiculeOnRoad(Vehicule vehiculeOnRoad) {
        vehicule.add(vehiculeOnRoad);
    }

    public void removeVehiculeOnRoad(Vehicule vehiculeOnRoad) {
        vehicule.remove(vehiculeOnRoad);
    }

    @Override
    public String toString(){
        return "Road:" + id + " Vehicule" + vehicule;
    }
}
