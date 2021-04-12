package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Verwaltung.getInstance().setPrimaryStage(primaryStage);
        Verwaltung.getInstance().zeigeFortschritt();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        launch(args);
    }
}
