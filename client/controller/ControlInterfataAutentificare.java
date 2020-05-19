package controller;


import prelucrare.PersistentaElementFisier;
import subiect.Subiect;
import transfer.Client;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ControlInterfataAutentificare {
    private InterfataAutentificare interfataAutentificare;
    private InterfataAngajat interfataAngajat;
    private InterfataAdministrator interfataAdministrator;
    private InterfataPrajituri interfataPrajituri;
    private InterfataAdaugarePrajitura interfataAdaugarePrajitura;
    private InterfataEditarePrajitura interfataEditarePrajitura;
    private InterfataEditareAngajat interfataEditareAngajat;
    private InterfataAdaugareAngajat interfataAdaugareAngajat;

    private Client client;
    private Subiect subiect;

    private static final String angajat = "@angajat";
    private static final String admin = "@admin";
    private int op;


    public ControlInterfataAutentificare(Client client,Subiect subiect,InterfataAdaugareAngajat interfataAdaugareAngajat,InterfataEditareAngajat interfataEditareAngajat,InterfataEditarePrajitura interfataEditarePrajitura,InterfataAdaugarePrajitura interfataAdaugarePrajitura,InterfataPrajituri interfataPrajituri,InterfataAutentificare interfataAutentificare, InterfataAdministrator interfataAdministrator, InterfataAngajat interfataAngajat){
        this.client = client;
        this.subiect = subiect;
        this.interfataAutentificare = interfataAutentificare;
        this.interfataAdministrator = interfataAdministrator;
        this.interfataAngajat = interfataAngajat;
        this.interfataAdaugareAngajat = interfataAdaugareAngajat;
        this.interfataAdaugarePrajitura = interfataAdaugarePrajitura;
        this.interfataEditareAngajat = interfataEditareAngajat;
        this.interfataEditarePrajitura = interfataEditarePrajitura;
        this.interfataPrajituri = interfataPrajituri;

        subiect.add(interfataAutentificare);
        subiect.add(interfataAngajat);
        subiect.add(interfataAdministrator);
        subiect.add(interfataAdaugareAngajat);
        subiect.add(interfataAdaugarePrajitura);
        subiect.add(interfataEditareAngajat);
        subiect.add(interfataEditarePrajitura);
        subiect.add(interfataPrajituri);

        interfataAutentificare.addNextButtonListener(new AddNextButtonListener());
        interfataAutentificare.addFrListener(new FrancezaListenter());
        interfataAutentificare.addRoListener(new RomanaListener());
        interfataAutentificare.addUkListener(new EnglezaListener());
    }

    public class RomanaListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            //System.out.println("ROMANA");
            op = 0;
            subiect.setChangedS();
            subiect.notifyS(op);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    public class EnglezaListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            //System.out.println("ENGLEZA");
            op = 1;
            subiect.setChangedS();
            subiect.notifyS(op);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    public class FrancezaListenter implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            //System.out.println("FRANCEZA");
            op = 2;
            subiect.setChangedS();
            subiect.notifyS(op);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public class AddNextButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String numeUtilizator = interfataAutentificare.getNumeUtilizator();
            char[] parola = interfataAutentificare.getNumeParola();
            String pass = String.valueOf(parola);
            model.ContUser contUser= new model.ContUser(numeUtilizator,pass);
            boolean angajatFunctie = numeUtilizator.contains(angajat);
            boolean adminFunctie = numeUtilizator.contains(admin);
            List<model.ContUser> contUserList = (List<model.ContUser>) client.transfer("conturi");
            boolean ok = false;
            for(model.ContUser c :contUserList){
                if(c.getNumeUtilizator().equals(contUser.getNumeUtilizator())){
                    if(c.getParola().equals(c.getParola())){
                        ok = true;
                    }
                }
            }
            if(ok) {
                if (angajatFunctie) {
                    interfataAngajat.setVisible(true);
                    interfataAutentificare.setVisible(false);
                }
                if (adminFunctie) {
                    interfataAdministrator.setVisible(true);
                    interfataAutentificare.setVisible(false);
                }
            }
            else{
                JOptionPane.showMessageDialog(interfataAutentificare,new PersistentaElementFisier().vizualizare("incorecte",op),new PersistentaElementFisier().vizualizare("incercati",op),JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
