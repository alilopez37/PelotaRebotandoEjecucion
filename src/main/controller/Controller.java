package main.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import main.model.PVector;
import main.model.Pelota;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Controller implements Observer {
    private Circle bola;
    Thread hilo;
    Pelota pelota;

    @FXML
    private AnchorPane canvas;

    @FXML
    private Button btnDetener;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnIniciar;

    @FXML
    void btnIniciarOnMouseClicked(MouseEvent event) {
        Random random = new Random(System.currentTimeMillis());
        PVector posicion = new PVector(random.nextInt(400),random.nextInt(300));
        bola  = new Circle(posicion.x, posicion.y, 10, Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256)) );
        canvas.getChildren().add(bola);
        pelota = new Pelota(posicion);
        pelota.addObserver(this);
        hilo = new Thread(pelota);
        hilo.start();
        btnIniciar.setDisable(true);
        btnDetener.setDisable(false);
    }

    @FXML
    void btnSalirOnMouseClicked(MouseEvent event) {
        Platform.exit();
        System.exit(1);
    }

    @FXML
    void btnDetenerOnMouseClicked(MouseEvent event) {
        pelota.setEstado(false);
        btnIniciar.setDisable(false);
        btnDetener.setDisable(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        PVector posicion = ((PVector)arg);

        bola.setCenterX(posicion.x);
        bola.setCenterY(posicion.y);
    }

    @FXML
    public void initialize(){
        btnDetener.setDisable(true);
    }
}
