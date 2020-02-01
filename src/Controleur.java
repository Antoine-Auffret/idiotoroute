import idiotoroute.*;

public class Controleur {

    public static void main(String[] args) throws InterruptedException {
        Controleur controleur = new Controleur();
        Vehicule A = controleur.ajouterVehiculeA();
        System.out.println(A);

        while (true) {
            tourSuivant(A);
            System.out.println(A);
            Thread.sleep(1000);
        }
    }

    public static void tourSuivant(Vehicule A) {
        try {
            A.rouler();
        }
        catch (VehiculeException e) {
            System.out.println(e.getMessage());
        }
    }

    public Vehicule ajouterVehiculeA() {
        Vehicule vehicule;

        vehicule = new VehiculeA();

        return vehicule;
    }
}
