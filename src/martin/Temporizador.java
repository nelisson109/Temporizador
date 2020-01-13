package martin;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Temporizador extends Label {

    private ArrayList<FinCuentaAtras> listaFinCuentaAtras = new ArrayList<>();

    private int segundos;

    public Temporizador(){

    }

    public Temporizador(int segundos) {
        this.segundos = segundos;
    }

    public Temporizador(String s, int segundos) {
        super(s);
        this.segundos = segundos;
    }

    public Temporizador(String s, Node node, int segundos) {
        super(s, node);
        this.segundos = segundos;
    }

    public void setSegundos(int segundos){
        this.segundos = segundos;
        setText(Integer.toString(this.segundos));
    }

    public int getSegundos(){
        return segundos;
    }

    public void addFinCuentaAtras(FinCuentaAtras finCuentaAtras){//añadir a la lista
        listaFinCuentaAtras.add(finCuentaAtras);
    }

    public void comenzar(){
        setStyle("-fx-text-fill:green");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(segundos>0){
                    //setStyle("-fx-background-color:green");

                    segundos--;

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            setText(Integer.toString(segundos));
                            if(segundos == 0) {
                                //setStyle("-fx-background-color:red");
                                setStyle("-fx-text-fill:red");
                                //finCuentaAtras.ejecuta();//for para llamar a todos los ejecutas
                                for(int i=0; i<listaFinCuentaAtras.size(); i++){

                                    listaFinCuentaAtras.get(i).ejecuta();
                                }
                            }
                        }
                    });

                }else {
                    cancel();
                }

            }
        }, 0 , 1000);

   /*     while(this.segundos>0){
            setSegundos(segundos-1);
            try {
                Thread.sleep(1000);//esto sirve para parar un hilo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }

}
