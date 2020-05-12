package com.soundhive;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import kong.unirest.Unirest;



/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(final Stage stage) {
        Unirest.config().defaultBaseUrl(Globals.API__URL_BASE);

        final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainView.fxml"));
        final Parent view;
        try {
            view = loader.load();
            stage.setScene(new Scene(view));
        } catch (Exception e) {
            e.printStackTrace();
        }


        stage.setTitle("SoundHive");
        Image icon = new Image(getClass().getResourceAsStream("/drawable/logo-icon.png"));
        stage.getIcons().add(icon);
        stage.show();

    }



    public static void main(final String[] args) {
        launch(args);
    }


}