package idiotoroute;

public class VehiculeException extends Exception {

    Vehicule panne;

    VehiculeException(Vehicule vehicule) { panne = vehicule; }

    @Override
    public String getMessage() {
        return "Le vehicule " + panne + " est tombé en panne!";
    }
}
