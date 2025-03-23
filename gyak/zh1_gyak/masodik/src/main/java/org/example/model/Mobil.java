package org.example.model;

public class Mobil {
    private String nev;
    private String gyarto;
    private Boolean esim;

    public Mobil(String nev, String gyarto, Boolean esim) {
        this.nev = nev;
        this.gyarto = gyarto;
        this.esim = esim;
    }

    public Mobil() {
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getGyarto() {
        return gyarto;
    }

    public void setGyarto(String gyarto) {
        this.gyarto = gyarto;
    }

    public Boolean getEsim() {
        return esim;
    }

    public void setEsim(Boolean esim) {
        this.esim = esim;
    }
}
