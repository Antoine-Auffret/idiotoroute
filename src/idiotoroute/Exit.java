package idiotoroute;

public class Exit {
    private int pos;

    public int getPos() {
        return pos;
    }

    public Exit(int pos){
        this.pos = pos;
    }

    @Override
    public String toString(){
        return "Exit:" + pos;
    }
}
