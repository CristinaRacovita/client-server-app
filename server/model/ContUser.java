package model;

import java.io.Serializable;

public class ContUser implements Serializable {
    private int id_cont;
    private String numeUtilizator;
    private String parola;

    public ContUser(String nume, String pass){
        this.numeUtilizator = nume;
        this.parola = pass;
    }
    public ContUser(int id,String nume, String pass){
        this.id_cont = id;
        this.numeUtilizator = nume;
        this.parola = pass;
    }

    public void setNumeUtilizator(String nume){
        this.numeUtilizator = nume;
    }

    public void setParola(String pass){
        this.parola = pass;
    }

    public String getNumeUtilizator(){
        return this.numeUtilizator;
    }

    public String getParola(){
        return this.parola;
    }

    public int getId_cont() {
        return id_cont;
    }

    public void setId_cont(int id_cont) {
        this.id_cont = id_cont;
    }

    @Override
    public String toString() {
        return id_cont+" "+numeUtilizator + " " + parola;
    }
}
