import idiotoroute.*;

import java.util.Random;

public class Controleur {

    public static void main(String[] args) throws InterruptedException {
        // Initialisation
        int nbTour = 1;
        Controleur controleur = new Controleur();
        Highway highway = new Highway();
        Vehicule vehicule;

        // Boucle infini pemettant d'ajouter un nouveau véhicule au hasard sur l'idiotoroute tous les 5 tours
        while (true) {
            if (nbTour%5 == 0) {
                vehicule = controleur.ajouterVehicule();
                highway.ajouterVehicule(vehicule);
            }

            System.out.println("\nTour:" + nbTour);

            // Fait avancer les véhicules
            highway.tourSuivant();
            // Vérification des collisions entre les véhicules à chaque tour
            highway.checkCollision();

            nbTour++;
            Thread.sleep(100);
        }
    }

    public Vehicule ajouterVehicule() {
        // Génération d'un nombre entre 1 et 3
        int[] vehiculeArray = {1, 2, 3};
        int vehiculeX = new Random().nextInt(vehiculeArray.length);
        int value = vehiculeArray[vehiculeX];

        Vehicule vehicule;

        // Instantiation d'un nouveau véhicule en fonction du nombre généré
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
