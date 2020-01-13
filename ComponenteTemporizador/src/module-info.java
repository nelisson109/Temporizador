module ComponenteTemporizador {
    exports com.martin;
    requires javafx.web;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires Temporizador;
    requires jdk.internal.le;
    requires javafx.fxml;


    opens com.martin to javafx.fxml;
}