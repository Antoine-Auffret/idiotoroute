package idiotoroute;

public class Exit {
    private int id;
    private int pos;

    public Exit(int id, int pos){
        this.id = id;
        this.pos = pos;
    }

    public int getId() { return id; }

    public int getPos() {
        return pos;
    }

    @Override
    public String toString(){
        return "Id: " + id + ", Exit:" + pos;
    }
}
