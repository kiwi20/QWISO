package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class LernfortschrittController {
    @FXML
    private Button start_button;
    @FXML
    private Rectangle unbearbeitet_balken;
    @FXML
    private Rectangle bearbeitet_balken;
    @FXML
    private Rectangle fertig_balken;
    @FXML
    private Label unbearbeitet_anzahl;
    @FXML
    private Label bearbeitet_anzahl;
    @FXML
    private Label fertig_anzahl;

    @FXML
    public void lernen_starten() throws IOException, InterruptedException {
        Verwaltung.getInstance().zeigeLernen();
    }

    public LernfortschrittController(){
        //unbearbeitet_anzahl = new Label("000 Fragen");
    }

    @FXML
    public void refresh(){
        int countall = 0;
        File dir = new File("fragen");
        for(String s : dir.list()){
            countall += (new File("fragen/"+s)).list().length;
        }

        File directory = new File("fragen/unbearbeitet");
        String[] list = directory.list();
        int count = list.length;
        unbearbeitet_anzahl.setText(formatMitNullenLinks(count,3)+unbearbeitet_anzahl.getText().substring(3));
        unbearbeitet_balken.setHeight(((double)count/(double)countall) * 150.0);
        unbearbeitet_balken.setY(-100 + (150 - unbearbeitet_balken.getHeight()));

        count = 0;
        count += Objects.requireNonNull((new File("fragen/richtig_0")).list()).length;
        count += Objects.requireNonNull((new File("fragen/richtig_1")).list()).length;
        count += Objects.requireNonNull((new File("fragen/richtig_2")).list()).length;
        bearbeitet_anzahl.setText(formatMitNullenLinks(count,3)+bearbeitet_anzahl.getText().substring(3));
        bearbeitet_balken.setHeight(((double)count/(double)countall) * 150.0);
        bearbeitet_balken.setY(-100 + (150 - bearbeitet_balken.getHeight()));

        count = 0;
        count += Objects.requireNonNull((new File("fragen/richtig_3")).list()).length;
        fertig_anzahl.setText(formatMitNullenLinks(count,3)+fertig_anzahl.getText().substring(3));
        fertig_balken.setHeight(((double)count/(double)countall) * 150.0);
        fertig_balken.setY(-100 + (150 - fertig_balken.getHeight()));
    }

    public String formatMitNullenLinks(int value, int len) {
        String result = String.valueOf(value);
        while (result.length() < len) {
            result = "0" + result;
        }
        return result;
    }

}