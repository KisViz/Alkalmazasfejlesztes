package org.example.model;

public class Kutya {
    private String nev;
    private String faj;
    private  String gazda;

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getFaj() {
        return faj;
    }

    public void setFaj(String faj) {
        this.faj = faj;
    }

    public String getGazda() {
        return gazda;
    }

    public void setGazda(String gazda) {
        this.gazda = gazda;
    }

    public Kutya() {
    }

    public Kutya(String nev, String faj, String gazda) {
        this.nev = nev;
        this.faj = faj;
        this.gazda = gazda;
    }
}
