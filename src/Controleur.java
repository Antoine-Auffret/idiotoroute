import idiotoroute.*;

import java.util.Random;

public class Controleur {

    public static void main(String[] args) throws InterruptedException {
        int nbTour = 1;
        Controleur controleur = new Controleur();
        Highway highway = new Highway();
        Vehicule veh;

        while (true) {
            if (nbTour%5 == 0) {
                veh = controleur.ajouterVehicule();
                highway.ajouterVehicule(veh);
            }
            /*if (nbTour == 1) {
                veh = new VehiculeA();
                highway.ajouterVehicule(veh);
            }
            if (nbTour == 10) {
                veh = new VehiculeB();
                highway.ajouterVehicule(veh);
            }
            if (nbTour == 20) {
                veh = new VehiculeC();
                highway.ajouterVehicule(veh);
            }*/

            System.out.println("\nTour:" + nbTour);
            highway.checkCollision();
            highway.tourSuivant();
            nbTour++;
            Thread.sleep(100);
        }
    }

    public Vehicule ajouterVehicule() {
        int[] vehiculeArray = {1, 2, 3};
        int vehiculeX = new Random().nextInt(vehiculeArray.length);
        int value = vehiculeArray[vehiculeX];

        Vehicule vehicule;

        switch(value){
            case 1: vehicule = new VehiculeA();
                break;
            case 2: vehicule = new VehiculeB();
                break;
            case 3: vehicule = new VehiculeC();
                break;
            default: vehicule = new VehiculeA();
                break;
        }

        return vehicule;
    }
}
