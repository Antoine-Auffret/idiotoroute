package idiotoroute;

import java.util.Enumeration;
import java.util.Vector;
import java.lang.*;

public class Highway {

    private static int nbRoad = 5;
    private static int distanceCollision = 50;
    private Vector<Vehicule> vehiculeQuiRoule;
    private Vector<Road> roads;

    public Highway(){
        roads = new Vector<Road>();
        vehiculeQuiRoule = new Vector<Vehicule>();
        setRoad();
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

        for (Road road: roads) {
            Vector<Vehicule> vehiculeOnRoad = road.vehicule;
            System.out.println(vehiculeOnRoad);
            for (Vehicule vehicule: vehiculeOnRoad) {
                //System.out.println(vehicule);
                for(Vehicule vehicule2: vehiculeOnRoad){
                    //System.out.println(vehicule2);
                    if(vehicule != vehicule2){
                        System.out.println(Math.abs(vehicule.getPos() - vehicule2.getPos()));
                        if(Math.abs(vehicule.getPos() - vehicule2.getPos()) <= distanceCollision){
                            System.out.println("Collision");
                            vehicule.setSpeed(0);
                            vehicule2.setSpeed(0);
                        }
                    }
                }
            }
        }
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
