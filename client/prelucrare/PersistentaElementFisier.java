package prelucrare;

import javax.swing.text.Element;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

//subiect
public class PersistentaElementFisier {
    private final String NUME_FISIER = "limbiStraine.bin";


    public void incarcare(List<ElementFisier> elemente){
        try {
            FileOutputStream file = new FileOutputStream(NUME_FISIER);
            ObjectOutputStream elementeFisier = new ObjectOutputStream(file);
            elementeFisier.writeObject(elemente);
            elementeFisier.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ElementFisier> vizualizare(){
        try{
            FileInputStream file = new FileInputStream(NUME_FISIER);
            ObjectInputStream in = new ObjectInputStream(file);
            List<ElementFisier> elemente = (ArrayList<ElementFisier>)in.readObject();
            in.close();
            return elemente;
        }
        catch (IOException | ClassNotFoundException ex){
            System.out.println("IOException is caught");
        }
        return null;
    }

    public String vizualizare(String tag,int limba){
        try{
            FileInputStream file = new FileInputStream(NUME_FISIER);
            ObjectInputStream in = new ObjectInputStream(file);
            List<ElementFisier> elementFisiere = (ArrayList<ElementFisier>)in.readObject();
            String valoare = "";
            for(ElementFisier elem: elementFisiere){
                if(elem.getTag().equals(tag)) {
                    switch (limba) {
                        case 0:
                            valoare = elem.getValoareTagRomana();
                            break;
                        case 1:
                            valoare = elem.getValoareTagEngleza();
                            break;
                        case 2:
                            valoare = elem.getValoareTagFranceza();
                            break;
                    }
                }
            }
            in.close();
            return valoare;
        }
        catch (IOException | ClassNotFoundException ex){
            System.out.println("IOException is caught");
        }
        return null;
    }
}
