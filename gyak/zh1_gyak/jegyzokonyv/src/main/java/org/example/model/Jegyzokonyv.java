package org.example.model;

public class Jegyzokonyv {
    private String cim;
    private String leiras;
    private String datum;
    private String jegyzo;

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getJegyzo() {
        return jegyzo;
    }

    public void setJegyzo(String jegyzo) {
        this.jegyzo = jegyzo;
    }

    public Jegyzokonyv() {
    }

    public Jegyzokonyv(String cim, String leiras, String datum, String jegyzo) {
        this.cim = cim;
        this.leiras = leiras;
        this.datum = datum;
        this.jegyzo = jegyzo;
    }
}
