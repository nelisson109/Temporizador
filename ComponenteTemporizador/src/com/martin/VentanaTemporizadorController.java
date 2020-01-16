package com.martin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import martin.FinCuentaAtras;
import martin.Temporizador;

import java.net.URL;
import java.util.ResourceBundle;

public class VentanaTemporizadorController implements Initializable {

    @FXML
    Temporizador temporizador;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //temporizador = new Temporizador();
        temporizador.setSegundos(5);
        temporizador.setColorEncendido(Color.GREEN);
        temporizador.setColorFin(Color.RED);
        temporizador.setMensajeFinal("Finito");
        temporizador.addFinCuentaAtras(new FinCuentaAtras() {
            @Override
            public void ejecuta() {
                System.out.println("La cuenta atras ha terminado");
            }
        });
    }

    @FXML
    public void iniciar(){
        temporizador.comenzar();
    }
}
