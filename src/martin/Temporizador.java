package martin;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Temporizador extends Label {

    private StringProperty mensajeFinal = new SimpleStringProperty("0");

    private ObjectProperty<Paint> colorEncendido = new SimpleObjectProperty<>(Color.GREEN);
    private ObjectProperty<Paint> colorFin = new SimpleObjectProperty<>(Color.RED);

    private ArrayList<FinCuentaAtras> listaFinCuentaAtras = new ArrayList<>();

    private IntegerProperty segundos = new SimpleIntegerProperty(0);

    public Temporizador(){

    }

 /*   public Temporizador(int segundos) {
        this.segundos = segundos;
    }

    public Temporizador(String s, int segundos) {
        super(s);
        this.segundos = segundos;
    }

    public Temporizador(String s, Node node, int segundos) {
        super(s, node);
        this.segundos = segundos;
    }*/

    public int getSegundos() {
        return segundos.get();
    }

    public IntegerProperty segundosProperty() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos.set(segundos);
        setText(Integer.toString(segundos));
    }



    public String getMensajeFinal() {
        return mensajeFinal.get();
    }

    public StringProperty mensajeFinalProperty() {
        return mensajeFinal;
    }

    public void setMensajeFinal(String mensajeFinal) {
        this.mensajeFinal.set(mensajeFinal);
    }

    public Paint getColorEncendido() {
        return colorEncendido.get();
    }

    public ObjectProperty<Paint> colorEncendidoProperty() {
        return colorEncendido;
    }

    public void setColorEncendido(Paint colorEncendido) {
        this.colorEncendido.set(colorEncendido);
    }

    public Paint getColorFin() {
        return colorFin.get();
    }

    public ObjectProperty<Paint> colorFinProperty() {
        return colorFin;
    }

    public void setColorFin(Paint colorFin) {
        this.colorFin.set(colorFin);
    }

    public void addFinCuentaAtras(FinCuentaAtras finCuentaAtras){//añadir a la lista
        listaFinCuentaAtras.add(finCuentaAtras);
    }

    private String colorToString(Paint color){
        return ((Color)color).toString().substring(2);
    }

    public void comenzar(){
        //colorEncendido.get().
        //setStyle("-fx-text-fill:#"+String.format("%02x%02x%02x", (int)(colorEncendido.get().getRed()*255), (int)(colorEncendido.get().getGreen()*255), (int)(colorEncendido.get().getBlue()*255)));
        setStyle("-fx-text-fill:#" + colorToString(colorEncendido.get()));
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {//en este run no podemos meter nada que modifique o afecte a la interfaz de nuestro programa, en dicho caso tenemos que meterlo en platform.runlater
                if(segundos.get()>0){
                    //setStyle("-fx-background-color:green");
                    segundos.set(segundos.get()-1);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            setText(Integer.toString(segundos.get()));
                            if(segundos.get() == 0) {
                                //setStyle("-fx-background-color:red");
                                setStyle("-fx-text-fill:#" + colorToString(colorFin.get()));
                                if(listaFinCuentaAtras!=null) {
                                    for (int i = 0; i < listaFinCuentaAtras.size(); i++) {

                                        listaFinCuentaAtras.get(i).ejecuta();
                                    }
                                }
                                //setText("Fin cuenta atras");
                                setText(getMensajeFinal());
                            }
                        }
                    });

                }else {
                    timer.cancel();
                    timer.purge();
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
