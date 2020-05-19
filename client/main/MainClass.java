package main;

import controller.*;
import prelucrare.ElementFisier;
import prelucrare.PersistentaElementFisier;
import subiect.Subiect;
import transfer.Client;
import view.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[] args) throws IOException {
        Client c = new Client("127.0.0.1",5000);

        InterfataAutentificare interfataAutentificare = new InterfataAutentificare();
        InterfataAdministrator interfataAdministrator = new InterfataAdministrator();
        InterfataAdaugareAngajat interfataAdaugareAngajat = new InterfataAdaugareAngajat();
        InterfataEditareAngajat interfataEditareAngajat = new InterfataEditareAngajat();
        InterfataAngajat interfataAngajat = new InterfataAngajat();
        InterfataPrajituri interfataPrajituri = new InterfataPrajituri();
        InterfataAdaugarePrajitura interfataAdaugarePrajitura = new InterfataAdaugarePrajitura();
        InterfataEditarePrajitura interfataEditarePrajitura = new InterfataEditarePrajitura();
        Subiect subiect = new Subiect();
        ControlInterfataAdaugareAngajat controlInterfataAdaugareAngajat = new ControlInterfataAdaugareAngajat(c,subiect,interfataAutentificare,interfataAdaugareAngajat,interfataAdministrator);
        ControlInterfataAutentificare controlInterfataAutentificare = new ControlInterfataAutentificare(c,subiect,interfataAdaugareAngajat,interfataEditareAngajat,interfataEditarePrajitura,interfataAdaugarePrajitura,interfataPrajituri,interfataAutentificare,interfataAdministrator,interfataAngajat);
        ControlInterfataAdministrator controlInterfataAdministrator = new ControlInterfataAdministrator(c,subiect,interfataAngajat,interfataAdministrator,interfataAutentificare,interfataAdaugareAngajat,interfataEditareAngajat);
        ControlInterfataEditareAngajat controlInterfataEditareAngajat = new ControlInterfataEditareAngajat(c,subiect,interfataAutentificare,interfataAdministrator,interfataEditareAngajat);
        ControlInterfataAngajat controlInterfataAngajat = new ControlInterfataAngajat(c,subiect,interfataAdministrator,interfataEditarePrajitura,interfataAdaugarePrajitura,interfataAngajat,interfataAutentificare,interfataPrajituri);
        ControlInterfataPrajituri controlInterfataPrajituri = new ControlInterfataPrajituri(c,subiect,interfataAutentificare,interfataPrajituri,interfataAngajat,interfataAdaugarePrajitura,interfataEditarePrajitura,controlInterfataAngajat);
        ControlInterfataAdaugarePrajitura controlInterfataAdaugarePrajitura = new ControlInterfataAdaugarePrajitura(c,subiect,interfataAngajat,interfataAdaugarePrajitura,interfataPrajituri,controlInterfataAngajat);
        ControlInterfataEditarePrajitura controlInterfataEditarePrajitura = new ControlInterfataEditarePrajitura(c,subiect,interfataAngajat,interfataEditarePrajitura,interfataPrajituri,controlInterfataPrajituri,controlInterfataAngajat);

    }

}
