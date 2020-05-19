package model;

import java.io.Serializable;

public class Prajitura implements Serializable {

    private int id_prajitura;
    private String numeCofetarie;
    private String numePrajitura;
    private float pret;
    private boolean disponibilitateProdus;
    private String valabilitate;

    public Prajitura(){
    }
    public Prajitura(String numeCofetarie,String numePrajitura, float pret,boolean disponibilitateProdus,String valabilitate){
        this.numeCofetarie=numeCofetarie;
        this.disponibilitateProdus = disponibilitateProdus;
        this.numePrajitura = numePrajitura;
        this.pret = pret;
        this.valabilitate = valabilitate;
    }
    public Prajitura(int id,String numeCofetarie,String numePrajitura, float pret,boolean disponibilitateProdus,String valabilitate){
        this.numeCofetarie=numeCofetarie;
        this.id_prajitura = id;
        this.disponibilitateProdus = disponibilitateProdus;
        this.numePrajitura = numePrajitura;
        this.pret = pret;
        this.valabilitate = valabilitate;
    }
    public String getNumePrajitura() {
        return numePrajitura;
    }

    public void setNumePrajitura(String numePrajitura) {
        this.numePrajitura = numePrajitura;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public boolean isDisponibilitateProdus() {
        return disponibilitateProdus;
    }

    public void setDisponibilitateProdus(boolean disponibilitateProdus) {
        this.disponibilitateProdus = disponibilitateProdus;
    }

    public String getValabilitate() {
        return valabilitate;
    }

    public void setValabilitate(String valabilitate) {
        this.valabilitate = valabilitate;
    }

    public String getNumeCofetarie() {
        return numeCofetarie;
    }

    public void setNumeCofetarie(String numeCofetarie) {
        this.numeCofetarie = numeCofetarie;
    }

    public int getId_prajitura() {
        return id_prajitura;
    }

    public void setId_prajitura(int id_prajitura) {
        this.id_prajitura = id_prajitura;
    }

    @Override
    public String toString() {
        return  id_prajitura+" "+numeCofetarie+" "+numePrajitura + " " + pret + " "+disponibilitateProdus + " "+ valabilitate + " ";
    }
}
