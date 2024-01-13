package com.example.cm1601_cw_2236750;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("racing.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 500);
        stage.setTitle("World rally cross championship management system");
        stage.setScene(scene);
        String css = Objects.requireNonNull(this.getClass().getResource("Style.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}