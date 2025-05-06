package hu.alkfejl.model;

public class Utazas {
    private String nev;
    private String uticel;
    private Boolean felpanzio;
    private int utasok;
    private int ejszaka;
    private String leiras;

    public Utazas(String nev, String uticel, Boolean felpanzio, int utasok, int ejszaka, String leiras) {
        this.nev = nev;
        this.uticel = uticel;
        this.felpanzio = felpanzio;
        this.utasok = utasok;
        this.ejszaka = ejszaka;
        this.leiras = leiras;
    }

    public Utazas() {

    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public void setUticel(String uticel) {
        this.uticel = uticel;
    }

    public void setFelpanzio(Boolean felpanzio) {
        this.felpanzio = felpanzio;
    }

    public void setUtasok(int utasok) {
        this.utasok = utasok;
    }

    public void setEjszaka(int ejszaka) {
        this.ejszaka = ejszaka;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public String getNev() {
        return nev;
    }

    public String getUticel() {
        return uticel;
    }

    public Boolean getFelpanzio() {
        return felpanzio;
    }

    public int getUtasok() {
        return utasok;
    }

    public int getEjszaka() {
        return ejszaka;
    }

    public String getLeiras() {
        return leiras;
    }
}
