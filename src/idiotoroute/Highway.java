package idiotoroute;

import java.util.ArrayList;

public class Highway {

    private static int nbRoad = 6;

    Highway(){
        setRoad();
    }

    void setRoad() {
        ArrayList<Road> roads = new ArrayList<Road>();



        for(int i=0; i<nbRoad; i++){
            roads.add(new Road());
        }
    }
}
