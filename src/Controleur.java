import idiotoroute.*;

import java.util.Random;

public class Controleur {

    public static void main(String[] args) throws InterruptedException {
        Controleur controleur = new Controleur();
        Vehicule A = controleur.ajouterVehicule();
        System.out.println(A);

        while (true) {
            tourSuivant(A);
            System.out.println(A);
            Thread.sleep(1000);
        }
    }

    public static void tourSuivant(Vehicule x) {
        try {
            x.rouler();
        }
        catch (VehiculeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
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
