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
        System.out.println(roads);
        vehiculeQuiRoule = new Vector<Vehicule>();
    }

    public void rouler(Vehicule vehicule) {
        vehiculeQuiRoule.add(vehicule);
    }

    public void tourSuivant() {
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
