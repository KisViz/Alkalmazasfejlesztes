package org.example.model;

public class Telefon {
    private String nev;
    private String gyarto;
    private Boolean esim;

    public Telefon(String nev, String gyarto, boolean esim) {
        this.nev = nev;
        this.gyarto = gyarto;
        this.esim = esim;
    }

    public Telefon() {
    }

    public String getNev() {
        return nev;
    }

    public String getGyarto() {
        return gyarto;
    }

    public Boolean isEsim() {
        return esim;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public void setGyarto(String gyarto) {
        this.gyarto = gyarto;
    }

    public void setEsim(Boolean esim) {
        this.esim = esim;
    }
}
