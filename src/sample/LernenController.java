package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;
import java.util.Random;

import static java.nio.file.StandardCopyOption.*;

public class LernenController {
    public boolean aufgabenvorhanden;
    private Aufgabe aktuelleAufgabe;
    public LernenController() throws IOException, InterruptedException {
        aufgabenvorhanden = true;
        Random zufall = new Random();
        int zufallsZahl;
        if(Objects.requireNonNull((new File("fragen/unbearbeitet")).list()).length > 0){
            zufallsZahl = zufall.nextInt(Objects.requireNonNull((new File("fragen/unbearbeitet")).list()).length-1);
            this.aktuelleAufgabe = (new AufgabenLeser()).leseAufgabe(
                    "fragen/unbearbeitet/" +
                    Objects.requireNonNull((new File("fragen/unbearbeitet")).list())[zufallsZahl]);
        }else if(Objects.requireNonNull((new File("fragen/richtig_0")).list()).length > 0){
            zufallsZahl = zufall.nextInt(Objects.requireNonNull((new File("fragen/richtig_0")).list()).length-1);
            this.aktuelleAufgabe = (new AufgabenLeser()).leseAufgabe(
                    "fragen/richtig_0/" +
                            Objects.requireNonNull((new File("fragen/richtig_0")).list())[zufallsZahl]);
        }else if(Objects.requireNonNull((new File("fragen/richtig_1")).list()).length > 0){
            zufallsZahl = zufall.nextInt(Objects.requireNonNull((new File("fragen/richtig_1")).list()).length-1);
            this.aktuelleAufgabe = (new AufgabenLeser()).leseAufgabe(
                    "fragen/richtig_1/" +
                            Objects.requireNonNull((new File("fragen/richtig_1")).list())[zufallsZahl]);
        }else if(Objects.requireNonNull((new File("fragen/richtig_2")).list()).length > 0){
            zufallsZahl = zufall.nextInt(Objects.requireNonNull((new File("fragen/richtig_2")).list()).length-1);
            this.aktuelleAufgabe = (new AufgabenLeser()).leseAufgabe(
                    "fragen/richtig_2/" +
                            Objects.requireNonNull((new File("fragen/richtig_2")).list())[zufallsZahl]);
        }else{
            System.out.println("Keine Aufgaben zu bearbeiten! :)");
            aufgabenvorhanden = false;
        }
    }

    @FXML
    private TextArea aufgabe_text;

    @FXML
    private Button ergebnis_button;

    @FXML
    private TextField antwort_text;

    @FXML
    private TextField loesung_text;

    @FXML
    private void stelleAktuelleAufgabeDar(){
        naechste_button.setVisible(false);
        this.aufgabe_text.setText(aktuelleAufgabe.getAufgabentext()+"\n"+aktuelleAufgabe.getAntwortMoeglichkeitenAlsString());
    }


    @FXML
    private Button home_button;

    @FXML
    public void home_oeffnen() throws IOException, InterruptedException {
        Verwaltung.getInstance().zeigeFortschritt();
    }

    @FXML
    private Rectangle ergebnis_signal;

    @FXML
    private Button naechste_button;

    @FXML
    public void ermittleErgebnis(){
        boolean erg = false;
        if(!antwort_text.getText().equals("")){
            erg = aktuelleAufgabe.checkAntwort(antwort_text.getText());
        }
        if(erg){
            ergebnis_signal.setFill(Paint.valueOf("GREEN"));
        }else{
            ergebnis_signal.setFill(Paint.valueOf("RED"));
        }
        this.loesung_text.setText(aktuelleAufgabe.getAntwort());
        naechste_button.setVisible(true);
    }

    @FXML
    public void naechsteAufgabe() throws IOException, InterruptedException {
        if(naechste_button.isVisible()){
            CopyOption h = REPLACE_EXISTING;
            Path source = FileSystems.getDefault().getPath(aktuelleAufgabe.getPfad());
            String ziel = aktuelleAufgabe.getPfad();
            Path target = FileSystems.getDefault().getPath(ziel);
            if(ergebnis_signal.getFill().equals(Paint.valueOf("GREEN"))){
                switch (this.aktuelleAufgabe.getRichtigeVersuche()){
                    case 0:
                        ziel = ziel.replace("unbearbeitet","richtig_1");
                        ziel = ziel.replace("richtig_0","richtig_1");
                        target = FileSystems.getDefault().getPath(ziel);
                        break;
                    case 1:
                        ziel = ziel.replace("richtig_1","richtig_2");
                        target = FileSystems.getDefault().getPath(ziel);
                        break;
                    case 2:
                        ziel = ziel.replace("richtig_2","richtig_3");
                        target = FileSystems.getDefault().getPath(ziel);
                        break;
                    default:
                        break;
                }
                try {
                    Files.move(source,target, h);
                } catch (Exception e){
                    System.out.println("Aufgabe konnte nicht eingeordnet werden.");
                }
            }else if(ergebnis_signal.getFill().equals(Paint.valueOf("RED"))){
                switch (this.aktuelleAufgabe.getRichtigeVersuche()){
                    case 0:
                        ziel = ziel.replace("unbearbeitet","richtig_0");
                        target = FileSystems.getDefault().getPath(ziel);
                        break;
                    case 1:
                        ziel = ziel.replace("richtig_1","richtig_0");
                        target = FileSystems.getDefault().getPath(ziel);
                        break;
                    case 2:
                        ziel = ziel.replace("richtig_2","richtig_1");
                        target = FileSystems.getDefault().getPath(ziel);
                        break;
                    default:
                        break;
                }
                try {
                    Files.move(source,target, h);
                } catch (Exception e){
                    System.out.println("Aufgabe konnte nicht eingeordnet werden.");
                }
            }

            //neue Aufgabe aussuchen
            boolean exists = true;
            Random zufall = new Random();
            int zufallsZahl;
            if(Objects.requireNonNull((new File("fragen/unbearbeitet")).list()).length > 0){
                zufallsZahl = zufall.nextInt(Objects.requireNonNull((new File("fragen/unbearbeitet")).list()).length-1);
                this.aktuelleAufgabe = (new AufgabenLeser()).leseAufgabe(
                        "fragen/unbearbeitet/" +
                                Objects.requireNonNull((new File("fragen/unbearbeitet")).list())[zufallsZahl]);
            }else if(Objects.requireNonNull((new File("fragen/richtig_0")).list()).length > 0){
                zufallsZahl = zufall.nextInt(Objects.requireNonNull((new File("fragen/richtig_0")).list()).length-1);
                this.aktuelleAufgabe = (new AufgabenLeser()).leseAufgabe(
                        "fragen/richtig_0/" +
                                Objects.requireNonNull((new File("fragen/richtig_0")).list())[zufallsZahl]);
            }else if(Objects.requireNonNull((new File("fragen/richtig_1")).list()).length > 0){
                zufallsZahl = zufall.nextInt(Objects.requireNonNull((new File("fragen/richtig_1")).list()).length-1);
                this.aktuelleAufgabe = (new AufgabenLeser()).leseAufgabe(
                        "fragen/richtig_1/" +
                                Objects.requireNonNull((new File("fragen/richtig_1")).list())[zufallsZahl]);
            }else if(Objects.requireNonNull((new File("fragen/richtig_2")).list()).length > 0){
                zufallsZahl = zufall.nextInt(Objects.requireNonNull((new File("fragen/richtig_2")).list()).length-1);
                this.aktuelleAufgabe = (new AufgabenLeser()).leseAufgabe(
                        "fragen/richtig_2/" +
                                Objects.requireNonNull((new File("fragen/richtig_2")).list())[zufallsZahl]);
            }else{
                System.out.println("Keine Aufgaben zu bearbeiten! :)");
                exists = false;
                aufgabenvorhanden = false;
            }

            ergebnis_signal.setFill(Paint.valueOf("#bfbfbf"));
            naechste_button.setVisible(false);
            antwort_text.setText("");
            loesung_text.setText("");

            if(exists){
                stelleAktuelleAufgabeDar();
            }else{
                Verwaltung.getInstance().zeigeFortschritt();
            }
        }
    }

}