package model;

import java.io.Serializable;

public class Persoana implements Serializable {
    protected String nume;
    protected String prenume;
    protected int varsta;
    protected String dataAgajarii;

    public Persoana(){
    }

    public Persoana(String nume, String prenume, int varsta, String dataAgajarii){
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
        this.dataAgajarii = dataAgajarii;
    }

    public String getNume(){
        return this.nume;
    }

    public String getPrenume(){
        return this.prenume;
    }

    public int getVarsta(){
        return this.varsta;
    }

    public void setNume(String nume){
        this.nume = nume;
    }

    public void setPrenume(String prenume){
        this.prenume = prenume;
    }

    public void setVarsta(int varsta){
        this.varsta = varsta;
    }

    public String getDataAgajarii() {
        return dataAgajarii;
    }

    public void setDataAgajarii(String dataAgajarii) {
        this.dataAgajarii = dataAgajarii;
    }

    @Override
    public String toString() {
        return  nume + " " + prenume + " " + varsta + " " + dataAgajarii + " ";
    }
}
