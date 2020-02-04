package idiotoroute;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

public class Highway {

    private static int nbRoad = 6;
    private Vector<Vehicule> vehiculeQuiRoule;
    ArrayList<Road> roads = new ArrayList<Road>();

    public Highway(){
        setRoad();
        vehiculeQuiRoule = new Vector<Vehicule>();
    }

    public void rouler(Vehicule vehicule) {
        vehiculeQuiRoule.add(vehicule);
        roads.get(0).addVehiculeOnRoad(vehicule);
    }

    public void changeRoad(Vehicule vehicule, Road oldRoad, Road newRoad) {
        oldRoad.removeVehiculeOnRoad(vehicule);
        newRoad.addVehiculeOnRoad(vehicule);
    }

    public void tourSuivant() {
        System.out.println(roads);
        for (Enumeration<Vehicule> enumVehicule = vehiculeQuiRoule.elements(); enumVehicule.hasMoreElements();) {
            try {
                enumVehicule.nextElement().rouler();
            } catch (VehiculeException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    void setRoad() {
        for(int i=1; i<=nbRoad; i++){
            roads.add(new Road(i));
        }
    }
}
