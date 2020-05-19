package controller;

import prelucrare.PersistentaElementFisier;
import subiect.Subiect;
import transfer.Client;
import view.InterfataAdaugarePrajitura;
import view.InterfataAngajat;
import view.InterfataPrajituri;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlInterfataAdaugarePrajitura {
    private InterfataAdaugarePrajitura interfataAdaugarePrajitura;
    private InterfataPrajituri interfataPrajituri;
    private ControlInterfataAngajat controlInterfataAngajat;
    private InterfataAngajat interfataAngajat;
    private Client client;
    private Subiect subiect;
    private int optiuneLimba;

    public ControlInterfataAdaugarePrajitura(Client client,Subiect subiect,InterfataAngajat interfataAngajat,InterfataAdaugarePrajitura interfataAdaugarePrajitura, InterfataPrajituri interfataPrajituri,ControlInterfataAngajat controlInterfataAngajat){
        this.client = client;
        this.subiect = subiect;
        this.interfataAngajat = interfataAngajat;
        this.interfataAdaugarePrajitura = interfataAdaugarePrajitura;
        this.interfataPrajituri = interfataPrajituri;
        this.controlInterfataAngajat = controlInterfataAngajat;

        interfataAdaugarePrajitura.addInpoiListener(new FinishListener());
        interfataAdaugarePrajitura.addListenerRomana(new RomanaListener());
        interfataAdaugarePrajitura.addListenerEngleza(new EnglezaListener());
        interfataAdaugarePrajitura.addListenerFranceza(new FrancezaListener());
        interfataAdaugarePrajitura.addListenerSettings(new SettingsListener());
    }

    public class SettingsListener implements MenuListener {
        @Override
        public void menuSelected(MenuEvent e) {
            subiect.deleteAll();
            subiect.add(interfataAdaugarePrajitura);
            subiect.add(interfataPrajituri);
        }

        @Override
        public void menuDeselected(MenuEvent e) {

        }

        @Override
        public void menuCanceled(MenuEvent e) {

        }
    }


    public class RomanaListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            optiuneLimba = 0;
            subiect.setChangedS();
            subiect.notifyS(0);
        }
    }

    public class EnglezaListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            optiuneLimba = 1;
            subiect.setChangedS();
            subiect.notifyS(1);
        }
    }

    public class FrancezaListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            optiuneLimba = 2;
            subiect.setChangedS();
            subiect.notifyS(2);
        }
    }

    public class FinishListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            PersistentaElementFisier persistentaElementFisier = new PersistentaElementFisier();
            String denumire = interfataAdaugarePrajitura.getDenumireText();
            String pret = interfataAdaugarePrajitura.getPretText();
            String disponibilitate = interfataAdaugarePrajitura.getDisponibilitateText();
            String valabilitate = interfataAdaugarePrajitura.getValabilitateText();


            String nume = controlInterfataAngajat.getCofetarie();

            String[] row = {indexMaxim(),nume,denumire,pret,disponibilitate,valabilitate};


            if(denumire.compareTo("") != 0 && pret.compareTo("") != 0 && disponibilitate.compareTo("") != 0 && valabilitate.compareTo("") != 0) {
                DefaultTableModel model = (DefaultTableModel) interfataPrajituri.getTable().getModel();
                model.addRow(row);

                client.transfer("salvprj"+nume+","+denumire+","+pret+","+disponibilitate+","+valabilitate);

                interfataAdaugarePrajitura.setVisible(false);

                interfataAdaugarePrajitura.setNume("");
                interfataAdaugarePrajitura.setPret("");
                interfataAdaugarePrajitura.setDisponibilitate("");
                interfataAdaugarePrajitura.setValabilitate("");

                subiect.deleteAll();
                subiect.add(interfataPrajituri);
                subiect.add(interfataAngajat);

                interfataPrajituri.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(interfataAdaugarePrajitura,persistentaElementFisier.vizualizare("campuri",optiuneLimba),persistentaElementFisier.vizualizare("eroare",optiuneLimba),JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private String indexMaxim(){
        int maxim = -1;
        DefaultTableModel model = (DefaultTableModel) interfataPrajituri.getTable().getModel();
        for(int i = 0; i < model.getRowCount(); i++){
            if(Integer.parseInt((String) model.getValueAt(i,0)) > maxim){
                maxim = Integer.parseInt((String) model.getValueAt(i,0));
            }
        }
        return String.valueOf(maxim+1);
    }
}
