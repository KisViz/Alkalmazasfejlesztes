package hu.alkfejl.model;

public class Latnivalo {
    private String nev;
    private int ar = -1;
    private int mettol = -1;
    private int meddig = -1;
    private String leiras;
    private int nepszeruseg = -1;

    public Latnivalo(String nev, int ar, int mettol, int meddig, String leiras, int nepszeruseg) {
        this.nev = nev;
        this.ar = ar;
        this.mettol = mettol;
        this.meddig = meddig;
        this.leiras = leiras;
        this.nepszeruseg = nepszeruseg;
    }

    public Latnivalo() {
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public int getMettol() {
        return mettol;
    }

    public void setMettol(int mettol) {
        this.mettol = mettol;
    }

    public int getMeddig() {
        return meddig;
    }

    public void setMeddig(int meddig) {
        this.meddig = meddig;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public int getNepszeruseg() {
        return nepszeruseg;
    }

    public void setNepszeruseg(int nepszeruseg) {
        this.nepszeruseg = nepszeruseg;
    }
}
