package idiotoroute;

import java.util.Vector;

public class Road {

    private int id;
    private int nbExit;
    private int lon;
    private int speed;
    public Vector<Vehicule> vehicule;
    public Vector<Exit> exit;

    public Road(int id, int nbExit, int lon, int speed) {
        if (nbExit>= 2 && nbExit<=6) {
            this.id = id;
            this.nbExit = nbExit;
            this.lon = lon;
            this.speed = speed;
            vehicule = new Vector<Vehicule>();
            exit = new Vector<Exit>();
            initExit();
        }
        else
            throw new IllegalArgumentException("nbExit must be between 2 and 6");
    }

    public int getId() {
        return id;
    }

    public int getSpeed() {
        return speed;
    }

    public void initExit() {
        exit.add(new Exit(0));
        exit.add(new Exit(33));
        exit.add(new Exit(66));
        exit.add(new Exit(100));
        System.out.println(exit);
    }

    /*public void initExit(){
        int offset = 0;
        for(int i=0; i<nbExit; i++){
            System.out.println((((Math.round( (float) i/(nbExit-1)))*lon)+offset)%lon);
            exit.add(new Exit((((Math.round( (float) i/(nbExit-1)))*lon)+offset)%lon));
        }
    }*/

    public void addVehiculeOnRoad(Vehicule vehiculeOnRoad) { vehicule.add(vehiculeOnRoad); }

    public void removeVehiculeOnRoad(Vehicule vehiculeOnRoad) { vehicule.remove(vehiculeOnRoad); }

    @Override
    public String toString(){
        return "Road:" + id + ", nbExit:" + nbExit + ", Vehicule" + vehicule;
    }
}
