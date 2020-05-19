package subiect;

import java.util.Observable;
import java.util.Observer;

public class Subiect extends Observable {
    public void setChangedS(){
        this.setChanged();
    }
    public void notifyS(int a){
        this.notifyObservers(a);
    }
    public void add(Observer observer){
        System.out.println(observer.getClass() + "ADAUGA");
        this.addObserver(observer);
    }
    public void deleteAll(){
        System.out.println("S-au sters toate");
        this.deleteObservers();
    }

}
