package idiotoroute;

public class VehiculeEssenceException extends Exception {

    Vehicule panne;

    VehiculeEssenceException(Vehicule vehicule) { panne = vehicule; }

    @Override
    public String getMessage() {
        return "Le vehicule " + panne + " est tombé en panne!";
    }
}
