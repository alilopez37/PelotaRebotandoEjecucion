package main.model;

public class PVector {
    public int x;
    public int y;

    public PVector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(PVector nodo){
        this.x += nodo.x;
        this.y += nodo.y;
    }
}
