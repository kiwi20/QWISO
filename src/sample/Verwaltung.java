package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Verwaltung {
    private static Verwaltung verwaltung;
    private Stage primaryStage;
    private LernfortschrittController lernfortschrittController;
    private LernenController lernenController;

    private Verwaltung() throws IOException, InterruptedException {
        this.lernenController = new LernenController();
        this.lernfortschrittController = new LernfortschrittController();
    }

    public static Verwaltung getInstance() throws IOException, InterruptedException {
        if(verwaltung == null){
            verwaltung = new Verwaltung();
        }
        return verwaltung;
    }
    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void zeigeFortschritt() throws IOException, InterruptedException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        this.primaryStage.setTitle("QWISO");
        this.primaryStage.setScene(new Scene(root, 600, 400));
        this.primaryStage.show();
    }

    public void zeigeLernen() throws IOException {
        if(lernenController.aufgabenvorhanden){
            Parent root = FXMLLoader.load(getClass().getResource("lernen.fxml"));
            this.primaryStage.setScene(new Scene(root, 600, 400));
            this.primaryStage.show();
        }
    }
}
