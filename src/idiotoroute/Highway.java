package idiotoroute;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

public class Highway {

    private static int nbRoad = 5;
    private static int distanceCollision = 5;
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

    public void changeRoad(Vehicule vehicule, int oldRoad, int newRoad) {
        if (roads.get(oldRoad).vehicule.contains(vehicule))
            roads.get(oldRoad).removeVehiculeOnRoad(vehicule);
        if (!roads.get(newRoad).vehicule.contains(vehicule))
            roads.get(newRoad).addVehiculeOnRoad(vehicule);
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

    public void checkCollision() {
    }

    void setRoad() {
        for(int i=1; i<=nbRoad; i++){
            if (i==1) {
                roads.add(new Road(i, 4));
            }
            else if (i==2) {
                roads.add(new Road(i, 3));
            }
            else if (i==3) {
                roads.add(new Road(i, 3));
            }
            else if (i==4) {
                roads.add(new Road(i, 4));
            }
            else if (i==5) {
                roads.add(new Road(i, 2));
            }
        }
    }
}
