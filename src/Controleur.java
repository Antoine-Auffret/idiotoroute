import idiotoroute.*;

import java.util.Random;

public class Controleur {

    public static void main(String[] args) throws InterruptedException {
        Controleur controleur = new Controleur();
        Highway highway = new Highway();
        Vehicule v1 = controleur.ajouterVehicule();
        Vehicule v2 = controleur.ajouterVehicule();
        Vehicule v3 = controleur.ajouterVehicule();
        highway.rouler(v1);
        highway.rouler(v2);
        highway.rouler(v3);

        while (true) {
            highway.tourSuivant();
            System.out.println(v1);
            System.out.println(v2);
            System.out.println(v3);
            Thread.sleep(1000);
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
