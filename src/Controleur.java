import idiotoroute.*;

import java.util.Random;

public class Controleur {

    public static void main(String[] args) throws InterruptedException {
        int nbTour = 1;
        Controleur controleur = new Controleur();
        Highway highway = new Highway();

        while (true) {
            Vehicule v1 = null;
            if (nbTour == 1) {
                v1 = controleur.ajouterVehicule();
                highway.rouler(v1);
            } else if (nbTour == 10) {
                Vehicule v2 = controleur.ajouterVehicule();
                highway.rouler(v2);
            } else if (nbTour == 20) {
                Vehicule v3 = controleur.ajouterVehicule();
                highway.rouler(v3);
                highway.changeRoad(v1, 0, 1);
            }
            System.out.println("Tour:" + nbTour);
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
