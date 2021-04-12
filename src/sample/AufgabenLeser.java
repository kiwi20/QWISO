package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AufgabenLeser {
    public AufgabenLeser(){
    }

    public Aufgabe leseAufgabe(String dateipfad){
        boolean bearbeitet = dateipfad.contains("richtig");
        int richtigeVersuche = 0;
        if(bearbeitet){
            richtigeVersuche = Integer.parseInt(dateipfad.split("richtig_")[1].charAt(0)+"");
        }

        String aufgabentext = "";
        ArrayList<String> antwortmoeglichkeiten = new ArrayList<>();
        String antwort = "";

        File f = new File(dateipfad);
        Scanner scan = null;
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Frage konnte nicht gelesen werden.");
        }

        while (scan.hasNext()) {
            String line = scan.nextLine();
            if(line.startsWith("//")){
                if(!aufgabentext.equals("")){
                    aufgabentext += "\n";
                }
                aufgabentext += line.substring(2);
            } else if(line.startsWith("??")){
                antwortmoeglichkeiten.add(line.substring(2));
            } else if(line.startsWith("!!")){
                if(antwort.equals("")){
                    antwort = line.substring(2);
                }
            }
        }
        scan.close();

        return new Aufgabe(dateipfad,bearbeitet,richtigeVersuche,aufgabentext,antwortmoeglichkeiten,antwort);
    }
}
