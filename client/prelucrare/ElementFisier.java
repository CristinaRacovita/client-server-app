package prelucrare;

import java.io.Serializable;

public class ElementFisier implements Serializable {
    private String tag;
    private String valoareTagRomana;
    private String valoareTagEngleza;
    private String valoareTagFranceza;

    public ElementFisier(String tag, String valoareTagRomana, String valoareTagEngleza, String valoareTagFranceza) {
        this.tag = tag;
        this.valoareTagRomana = valoareTagRomana;
        this.valoareTagEngleza = valoareTagEngleza;
        this.valoareTagFranceza = valoareTagFranceza;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getValoareTagRomana() {
        return valoareTagRomana;
    }

    public void setValoareTagRomana(String valoareTagRomana) {
        this.valoareTagRomana = valoareTagRomana;
    }

    public String getValoareTagEngleza() {
        return valoareTagEngleza;
    }

    public void setValoareTagEngleza(String valoareTagEngleza) {
        this.valoareTagEngleza = valoareTagEngleza;
    }

    public String getValoareTagFranceza() {
        return valoareTagFranceza;
    }

    public void setValoareTagFranceza(String valoareTagFranceza) {
        this.valoareTagFranceza = valoareTagFranceza;
    }

    @Override
    public String toString() {
        return tag + ' ' + valoareTagRomana + ' ' + valoareTagEngleza + ' ' + valoareTagFranceza;
    }
}
