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

    public void ajouterVehicule(Vehicule vehicule) {
        vehiculeQuiRoule.add(vehicule);
        roads.get(0).addVehiculeOnRoad(vehicule);
        vehicule.setSpeed(roads.get(0).getSpeed()*vehicule.getSpeed());
    }

    public void changeRoad(Vehicule vehicule, int oldRoad, int newRoad) {
        if (roads.get(oldRoad).vehicule.contains(vehicule))
            roads.get(oldRoad).removeVehiculeOnRoad(vehicule);
        if (!roads.get(newRoad).vehicule.contains(vehicule))
            roads.get(newRoad).addVehiculeOnRoad(vehicule);
        System.out.println("OldRoadSPD:"+roads.get(oldRoad).getSpeed());
        System.out.println("NewRoadSPD:"+roads.get(newRoad).getSpeed());
        System.out.println("OldSPD:"+vehicule.getSpeed());
        System.out.println("OrgSPD:"+vehicule.getOriginalSpeed());
        System.out.println("NewSPD:"+vehicule.getOriginalSpeed()*roads.get(newRoad).getSpeed());
        vehicule.setSpeed(vehicule.getOriginalSpeed()*roads.get(newRoad).getSpeed());
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
            System.out.println(road.exit);
            for (Vehicule vehicule1: vehiculeOnRoad) {
                for(Vehicule vehicule2: vehiculeOnRoad){
                    if(vehicule1 != vehicule2 && !skip){
                        skip = true;
                        if(Math.abs(vehicule1.getPos() - vehicule2.getPos()) <= distanceCollision){
                            if (vehicule1.getPos() < vehicule2.getPos()) {
                                for (Exit exit: road.exit) {
                                    System.out.println(exit.getPos()-20);
                                    System.out.println(exit.getPos()+20);
                                    System.out.println(vehicule1.getPos());
                                    if (exit.getPos()+20 >= vehicule1.getPos() && exit.getPos()-20 <= vehicule1.getPos()){
                                        oldRoad = road.getId();
                                        newRoad = (road.getId()+1);
                                        System.out.println(vehicule1+" change from road "+oldRoad+" to road "+newRoad);
                                        changeRoad(vehicule1, oldRoad, newRoad);
                                        changeRoadBool = true;
                                    }
                                }
                                if (!changeRoadBool) {
                                    System.out.println("Collision");
                                    //vehicule1.setSpeed(0);
                                    //vehicule2.setSpeed(0);
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
            if (i==0) {
                roads.add(new Road(i, 4, 100, 0.5));
            }
            else if (i==1) {
                roads.add(new Road(i, 3, 90, 0.75));
            }
            else if (i==2) {
                roads.add(new Road(i, 3, 80, 1));
            }
            else if (i==3) {
                roads.add(new Road(i, 4, 70, 1.25));
            }
            else if (i==5) {
                roads.add(new Road(i, 2, 60, 1.5));
            }
        }
    }
}
