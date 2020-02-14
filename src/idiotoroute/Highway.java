package idiotoroute;

import java.util.Vector;
import static java.lang.Math.abs;

public class Highway {

    private static int nbRoad = 5;
    private Vector<Vehicule> vehiculeQuiRoule;
    private Vector<Road> roads;

    public Highway(){
        roads = new Vector<Road>();
        vehiculeQuiRoule = new Vector<Vehicule>();
        createRoad();
    }

    public void ajouterVehicule(Vehicule vehicule) {
        // Ajout d'un véhicule sur la première route
        vehiculeQuiRoule.add(vehicule);
        roads.get(0).addVehiculeOnRoad(vehicule);
        // Initialisation de la vitesse du véhicule en fonction de la route et du véhicule
        vehicule.setSpeed(roads.get(0).getSpeed()*vehicule.getSpeed());
    }

    public void changeRoad(Vehicule vehicule, int oldRoad, int newRoad) {
        // Supprime de la liste roads de l'ancienne route le véhicule
        if (roads.get(oldRoad).vehicule.contains(vehicule))
            roads.get(oldRoad).removeVehiculeOnRoad(vehicule);
        // Ajoute dans la liste roads de la nouvelle route le véhicule
        if (!roads.get(newRoad).vehicule.contains(vehicule))
            roads.get(newRoad).addVehiculeOnRoad(vehicule);
        // Réinitialisation de la vitesse du véhicule en fonction de la nouvelle route et du véhicule
        vehicule.setSpeed(vehicule.getOriginalSpeed()*roads.get(newRoad).getSpeed());
    }

    public void tourSuivant() {
        // Réinitialise la position de chaque voiture si elles ont fait un tour complet
        for (Road road: roads) {
            for (Vehicule vehicule: road.vehicule) {
                if (vehicule.getPos() > road.getLon()) {
                    vehicule.setPos(vehicule.getPos()-road.getLon());
                }
            }
        }
        // Fait rouler toutes les voitures
        for (Vehicule vehicule: vehiculeQuiRoule) {
            try {
                vehicule.rouler();
            } catch (VehiculeEssenceException e) {
                // Supprime le véhicule de la liste lors d'une panne d'essence
                for (Road road: roads){
                    road.vehicule.remove(vehicule);
                }
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void checkCollision() {
        boolean changeRoadBool = false;
        int oldRoad;
        int newRoad;
        double posMin;
        double posMax;
        double distanceSecurite;
        for (Road road: roads) {
            // Liste des véhicules qui sont sur la route
            Vector<Vehicule> vehiculeOnRoad = (Vector<Vehicule>) road.vehicule.clone();
            System.out.println(road);
            for (Vehicule vehicule1: vehiculeOnRoad) {
                System.out.println("\tVehicule:"+vehicule1);
                for (Exit exit: road.exit) {
                    // Définition des seuils pour savoir si le véhicule est proche d'une sortie de route
                    posMin = exit.getPos()-vehicule1.getSpeed();
                    posMax = exit.getPos()+vehicule1.getSpeed();
                    // Si un véhicule est proche d'une sortie, celui-ci change de route
                    if (vehicule1.getPos() < posMax && vehicule1.getPos() > posMin && road.getId() != (nbRoad-1) && !changeRoadBool) {
                        // Définition de la nouvelle route
                        oldRoad = road.getId();
                        newRoad = (road.getId()+1);
                        System.out.println("\t\t"+vehicule1.getType()+vehicule1.getNoSerie()+" change from road "+oldRoad+" to road "+newRoad+" by exit "+exit.getId());
                        // Changement de route du véhicule vers une route plus au centre si possible
                        changeRoad(vehicule1, oldRoad, newRoad);
                        changeRoadBool = true;
                        break;
                    }
                }
                // Comparaison avec les autres véhicules
                for(Vehicule vehicule2: vehiculeOnRoad){
                    if(vehicule1 != vehicule2){
                        changeRoadBool = false;
                        // Définition de la distance minimale entre deux véhicules
                        distanceSecurite = abs(vehicule1.getSpeed()-vehicule2.getSpeed());
                        // Si la distance entre les deux véhicules est inférieure à la distance minimale
                        if(abs(vehicule1.getPos() - vehicule2.getPos()) <= distanceSecurite){
                            if (vehicule1.getPos() <= vehicule2.getPos()) {
                                for (Exit exit: road.exit) {
                                    // Définition des seuils pour savoir si le véhicule est proche d'une sortie de route
                                    posMin = exit.getPos()-vehicule1.getSpeed();
                                    posMax = exit.getPos()+vehicule1.getSpeed();
                                    // Si un véhicule est proche d'une sortie, celui-ci change de route afin d'éviter la collision
                                    if (vehicule1.getPos() < posMax && vehicule1.getPos() > posMin && road.getId() != (nbRoad-1)) {
                                        // Définition de la nouvelle route
                                        oldRoad = road.getId();
                                        newRoad = (road.getId()+1);
                                        System.out.println("\tExit:"+exit.getId()+", VPOS:"+vehicule1.getPos()+", POSMIN:"+posMin+", POSMAX:"+posMax);
                                        System.out.println("\t\t"+vehicule1.getType()+vehicule1.getNoSerie()+" change (avoid collision) from road "+oldRoad+" to road "+newRoad+" by exit "+exit.getId());
                                        // Changement de route du véhicule vers une route plus au centre si possible
                                        changeRoad(vehicule1, oldRoad, newRoad);
                                        changeRoadBool = true;
                                    }
                                }
                                // Si la collision est innévitable
                                if (!changeRoadBool) {
                                    // Arrêt des véhicules si collision
                                    vehicule1.setSpeed(0);
                                    vehicule2.setSpeed(0);
                                    // Suppression des véhicules de la route
                                    road.vehicule.remove(vehicule1);
                                    road.vehicule.remove(vehicule2);
                                    try {
                                        // Met à jour de l'état des deux voitures
                                        vehicule1.setCrashed(true);
                                        vehicule2.setCrashed(true);
                                        // Renvoie une exception pour les deux véhicules en collision
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

    void createRoad() {
        // Ajoute les routes à la liste roads
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
