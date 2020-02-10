package idiotoroute;

import java.util.Enumeration;
import java.util.Vector;
import static java.lang.Math.abs;

public class Highway {

    private static int nbRoad = 5;
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
        vehicule.setSpeed(vehicule.getOriginalSpeed()*roads.get(newRoad).getSpeed());
    }

    public void tourSuivant() {
        for (Road road: roads) {
            for (Vehicule vehicule: road.vehicule) {
                if (vehicule.getPos() > road.getLon()) {
                    vehicule.setPos(vehicule.getPos()-road.getLon());
                }
            }
        }
        for (Vehicule vehicule: vehiculeQuiRoule) {
            try {
                vehicule.rouler();
            } catch (VehiculeException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void checkCollision() {
        boolean changeRoadBool;
        int oldRoad;
        int newRoad;
        double posMin;
        double posMax;
        double distanceSecurite;
        for (Road road: roads) {
            Vector<Vehicule> vehiculeOnRoad = (Vector<Vehicule>) road.vehicule.clone();
            System.out.println(road);
            for (Vehicule vehicule1: vehiculeOnRoad) {
                changeRoadBool = false;
                System.out.println("\tVehicule:"+vehicule1);
                for (Exit exit: road.exit) {
                    posMin = exit.getPos()-vehicule1.getSpeed();
                    posMax = exit.getPos()+vehicule1.getSpeed();
                    if (vehicule1.getPos() <= posMax && vehicule1.getPos() >= posMin && road.getId() != (nbRoad-1)) {
                        oldRoad = road.getId();
                        newRoad = (road.getId()+1);
                        System.out.println("\t\t"+vehicule1.getType()+vehicule1.getNoSerie()+" change from road "+oldRoad+" to road "+newRoad+" by exit "+exit.getId());
                        changeRoad(vehicule1, oldRoad, newRoad);
                        break;
                    }
                }
                for(Vehicule vehicule2: vehiculeOnRoad){
                    if(vehicule1 != vehicule2){
                        changeRoadBool = false;
                        distanceSecurite = abs(vehicule1.getSpeed()-vehicule2.getSpeed());
                        if(abs(vehicule1.getPos() - vehicule2.getPos()) <= distanceSecurite){
                            if (vehicule1.getPos() <= vehicule2.getPos()) {
                                for (Exit exit: road.exit) {
                                    posMin = exit.getPos()-vehicule1.getSpeed();
                                    posMax = exit.getPos()+vehicule1.getSpeed();
                                    if (vehicule1.getPos() <= posMax && vehicule1.getPos() >= posMin && road.getId() != (nbRoad-1)) {
                                        oldRoad = road.getId();
                                        newRoad = (road.getId()+1);
                                        System.out.println("\tExit:"+exit.getId()+", VPOS:"+vehicule1.getPos()+", POSMIN:"+posMin+", POSMAX:"+posMax);
                                        System.out.println("\t\t"+vehicule1.getType()+vehicule1.getNoSerie()+" change (avoid collision) from road "+oldRoad+" to road "+newRoad+" by exit "+exit.getId());
                                        changeRoad(vehicule1, oldRoad, newRoad);
                                        changeRoadBool = true;
                                    }
                                }
                                if (!changeRoadBool) {
                                    System.out.println("Collision between "+vehicule1+" and "+vehicule2);
                                    vehicule1.setSpeed(0);
                                    vehicule2.setSpeed(0);
                                    road.vehicule.remove(vehicule1);
                                    road.vehicule.remove(vehicule2);
                                    try {
                                        vehicule1.crash(vehicule1, vehicule2);
                                    } catch (VehiculeCollisionException e) {
                                        System.out.println(e.getMessage());
                                        e.printStackTrace();
                                    }
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
            else if (i==4) {
                roads.add(new Road(i, 2, 60, 1.5));
            }
        }
    }
}
