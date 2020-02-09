package idiotoroute;

public class VehiculeCollisionException extends Exception {

    Vehicule collision1;
    Vehicule collision2;

    VehiculeCollisionException(Vehicule vehicule1, Vehicule vehicule2) {
        collision1 = vehicule1;
        collision2 = vehicule2;
    }

    @Override
    public String getMessage() {
        return "Le vehicule " + collision1 + " est rentré dans le véhicule " + collision2;
    }
}
