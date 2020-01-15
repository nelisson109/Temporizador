package com.martin;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import martin.FinCuentaAtras;
import martin.Temporizador;
import javafx.scene.paint.Color;

public class Principal extends Application {
    @Override
    public void start(Stage stage) throws Exception {
       // Parent root = FXMLLoader.load(getClass().getResource("ventana.fxml"));
        VBox vBox = new VBox();
        Temporizador temporizador = new Temporizador();
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
        vBox.getChildren().add(temporizador);


       // Scene scene = new Scene(root, 800, 800);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);

        stage.show();
        temporizador.comenzar();
    }
    public static void main(String[] args){
        launch(args);
    }
}
