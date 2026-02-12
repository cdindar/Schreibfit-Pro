package model;

public class Wort {
    public String loesung, optionen, beschreibung, kategorie, stufe, modus;
    public String letzteEingabe = "";
    public boolean userKorrekt = true;

    public Wort(String loesung, String optionen, String beschreibung, String kategorie, String stufe, String modus) {
        this.loesung = loesung;
        this.optionen = optionen;
        this.beschreibung = beschreibung;
        this.kategorie = kategorie;
        this.stufe = stufe;
        this.modus = modus;
    }
}