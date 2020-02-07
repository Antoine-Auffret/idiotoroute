package idiotoroute;

import java.util.Enumeration;
import java.util.Vector;
import java.lang.*;

public class Highway {

    private static int nbRoad = 5;
    private static int distanceCollision = 5;
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
        boolean changeRoadBool;
        boolean skip = false;
        int oldRoad;
        int newRoad;
        for (Road road: roads) {
            changeRoadBool = false;
            Vector<Vehicule> vehiculeOnRoad = (Vector<Vehicule>) road.vehicule.clone();
            System.out.println(road);
            for (Vehicule vehicule1: vehiculeOnRoad) {
                for(Vehicule vehicule2: vehiculeOnRoad){
                    if(vehicule1 != vehicule2 && !skip){
                        skip = true;
                        if(Math.abs(vehicule1.getPos() - vehicule2.getPos()) <= distanceCollision){
                            if (vehicule1.getPos() < vehicule2.getPos()) {
                                for (Exit exit: road.exit) {
                                    System.out.println(exit.getPos()-2);
                                    System.out.println(exit.getPos()+2);
                                    System.out.println(vehicule1.getPos());
                                    if (exit.getPos()+2 >= vehicule1.getPos() && exit.getPos()-2 <= vehicule1.getPos()){
                                        oldRoad = road.getId();
                                        newRoad = (road.getId()+1);
                                        System.out.println("vehicule1 change road "+oldRoad+" to road "+newRoad);
                                        changeRoad(vehicule1, oldRoad, newRoad);
                                        changeRoadBool = true;
                                    }
                                }
                                if (!changeRoadBool) {
                                    System.out.println("Collision");
                                    vehicule1.setSpeed(0);
                                    vehicule2.setSpeed(0);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void setRoad() {
        for(int i=0; i<nbRoad; i++){
            roads.add(new Road(i, 4,100));
        }
    }
    /*void setRoad() {
        for(int i=1; i<=nbRoad; i++){
            if (i==1) {
                roads.add(new Road(i, 4, 100));
            }
            else if (i==2) {
                roads.add(new Road(i, 3, 80));
            }
            else if (i==3) {
                roads.add(new Road(i, 3, 60));
            }
            else if (i==4) {
                roads.add(new Road(i, 4, 40));
            }
            else if (i==5) {
                roads.add(new Road(i, 2, 20));
            }
        }
    }*/
}
