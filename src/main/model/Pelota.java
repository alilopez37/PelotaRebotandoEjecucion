package main.model;


import java.util.Observable;

public class Pelota extends Observable implements Runnable{
    PVector posicion;
    PVector velocidad;
    public boolean estado;

    public Pelota(PVector posicion){
        this.posicion = posicion;
        velocidad = new PVector(10,20);
        estado = true;
    }

    @Override
    public void run() {
        while (estado){
            posicion.add(velocidad);
            if (posicion.x > 585 || posicion.x < 15)
                velocidad.x *= -1;
            if (posicion.y > 385 || posicion.y < 15)
                velocidad.y *= -1;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setChanged();
            this.notifyObservers(posicion);
        }
    }

    public void setEstado(boolean estado){
        this.estado = estado;
    }

}
