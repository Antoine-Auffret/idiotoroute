package idiotoroute;

public class Road {

    private int id;

    Road(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "Road:" + id;
    }
}
