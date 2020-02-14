package idiotoroute;

import java.util.Vector;

public class Road {

    private int id;
    private int nbExit;
    private int taille;
    private double speed;
    public Vector<Vehicule> vehicule;
    public Vector<Exit> exit;

    public Road(int id, int nbExit, int taille, double speed) {
        // Vérification que le nombre de sorties est compris entre 2 et 6
        if (nbExit>= 2 && nbExit<=6) {
            this.id = id;
            this.nbExit = nbExit;
            this.taille = taille;
            this.speed = speed;
            // Chaque route possède une liste de véhicules
            vehicule = new Vector<Vehicule>();
            // Chaque route possède une liste de sorties
            exit = new Vector<Exit>();
            // Création des sorties
            initExit(nbExit, taille);
        }
        else
            throw new IllegalArgumentException("nbExit must be between 2 and 6");
    }

    public int getId() { return id; }

    public double getSpeed() { return speed; }

    public int getLon() { return taille; }

    public void initExit(int nbExit, int lon) {
        // Calcule la distance entre deux sorties
        int distance = lon/nbExit;
        int longueur = 0;
        // Ajoute à la liste des sorties le nombre de sorties nécessaire
        for(int i=0; i<nbExit; i++){
            exit.add(new Exit(i, longueur));
            longueur+=distance;
        }
    }

    // Ajoute à la liste des véhicules le véhicule
    public void addVehiculeOnRoad(Vehicule vehiculeOnRoad) {
        vehicule.add(vehiculeOnRoad);
    }

    // Supprime de la liste des véhicules le véhicule
    public void removeVehiculeOnRoad(Vehicule vehiculeOnRoad) {
        vehicule.remove(vehiculeOnRoad);
    }

    @Override
    public String toString(){
        return "Road:" + id + ", nbExit:" + nbExit + ", Lon:" + taille + ", Spd:" + speed + ", Vehicule" + vehicule;
    }
}
