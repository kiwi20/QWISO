package sample;

import java.util.ArrayList;

public class Aufgabe {
    private String pfad;
    private boolean bearbeitet;
    private int richtigeVersuche;
    private String aufgabentext;
    private ArrayList<String> antwortmoeglichkeiten;
    private String antwort;

    public Aufgabe(String pfad, boolean bearbeitet, int richtigeVersuche, String aufgabentext,
                   ArrayList<String> antwortmoeglichkeiten, String antwort){
        this.pfad = pfad;
        this.bearbeitet = bearbeitet;
        this.richtigeVersuche = richtigeVersuche;
        this.aufgabentext = aufgabentext;
        this.antwortmoeglichkeiten = antwortmoeglichkeiten;
        this.antwort = antwort;
    }

    public String getPfad(){
        return this.pfad;
    }
    public boolean getBearbeitet(){
        return this.bearbeitet;
    }
    public int getRichtigeVersuche(){
        return this.richtigeVersuche;
    }
    public String getAufgabentext(){
        return this.aufgabentext;
    }
    public ArrayList<String> getAntwortmoeglichkeiten(){
        return this.antwortmoeglichkeiten;
    }
    public String getAntwortMoeglichkeitenAlsString(){
        StringBuilder ret = new StringBuilder("");
        for(String m : this.antwortmoeglichkeiten){
            ret.append(m);
            if(!m.equals(this.antwortmoeglichkeiten.get(this.antwortmoeglichkeiten.size()-1))){
                ret.append("\n");
            }
        }
        return ret.toString();
    }
    public String getAntwort(){
        return this.antwort;
    }

    public boolean checkAntwort(String antwort){
        return (this.antwort.equalsIgnoreCase(antwort));
    }
}
