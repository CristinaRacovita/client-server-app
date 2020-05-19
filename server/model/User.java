package model;

import java.io.Serializable;

public class User extends model.Persoana implements Serializable {
    private int id_salariat;
    private String functie;

    @Override
    public String toString() {
        return id_salariat + functie + " " + nume + " " + prenume + " " + varsta + " " + dataAgajarii;
    }

    public User(int id_salariat,model.Persoana persoana, String functie) {
        super(persoana.nume,persoana.prenume,persoana.varsta,persoana.dataAgajarii);
        this.id_salariat = id_salariat;
        this.functie = functie;
    }

    public User(model.Persoana persoana, String functie) {
        super(persoana.nume,persoana.prenume,persoana.varsta,persoana.dataAgajarii);
        this.functie = functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public String getFunctie() {
        return functie;
    }

    public int getId_salariat() {
        return id_salariat;
    }

    public void setId_salariat(int id_salariat) {
        this.id_salariat = id_salariat;
    }
}
