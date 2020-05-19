package controller;

import prelucrare.PersistentaElementFisier;
import subiect.Subiect;
import transfer.Client;
import view.InterfataAngajat;
import view.InterfataEditarePrajitura;
import view.InterfataPrajituri;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControlInterfataEditarePrajitura {
    private InterfataEditarePrajitura interfataEditarePrajitura;
    private InterfataPrajituri interfataPrajituri;
    private ControlInterfataAngajat controlInterfataAngajat;
    private ControlInterfataPrajituri controlInterfataPrajituri;
    private InterfataAngajat interfataAngajat;
    private Client client;
    private int optiuneLimba;
    private Subiect subiect;

    public ControlInterfataEditarePrajitura(Client client,Subiect subiect,InterfataAngajat interfataAngajat ,InterfataEditarePrajitura interfataEditarePrajitura, InterfataPrajituri interfataPrajituri, ControlInterfataPrajituri controlInterfataPrajituri, ControlInterfataAngajat controlInterfataAngajat){
        this.controlInterfataPrajituri = controlInterfataPrajituri;
        this.interfataEditarePrajitura = interfataEditarePrajitura;
        this.interfataPrajituri = interfataPrajituri;
        this.controlInterfataAngajat=controlInterfataAngajat;
        this.interfataAngajat = interfataAngajat;
        this.client = client;
        this.subiect = subiect;

        interfataEditarePrajitura.addInpoiListener(new FinishListener());
        interfataEditarePrajitura.addListenerRomana(new RomanaListener());
        interfataEditarePrajitura.addListenerEngleza(new EnglezaListener());
        interfataEditarePrajitura.addListenerFranceza(new FrancezaListener());
        interfataEditarePrajitura.addListenerSettings(new SettingsListener());
    }
    public class SettingsListener implements MenuListener {
        @Override
        public void menuSelected(MenuEvent e) {
            subiect.deleteAll();
            subiect.add(interfataEditarePrajitura);
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
            String denumire = interfataEditarePrajitura.getDenumireText();
            String pret = interfataEditarePrajitura.getPretText();
            String disponibilitate = interfataEditarePrajitura.getDisponibilitateText();
            String valabilitate = interfataEditarePrajitura.getValabilitateText();
            String numeCofetarie = controlInterfataAngajat.getCofetarie();


            String[] row = {String.valueOf(controlInterfataPrajituri.getId()),numeCofetarie,denumire,pret,disponibilitate,valabilitate};

            if(denumire.compareTo("") != 0 && pret.compareTo("") != 0 && disponibilitate.compareTo("") != 0 && valabilitate.compareTo("") != 0) {
                DefaultTableModel model = (DefaultTableModel) interfataPrajituri.getTable().getModel();
                model.addRow(row);

                client.transfer("editprj"+numeCofetarie+","+controlInterfataPrajituri.getId()+","+denumire+","+pret+","+disponibilitate+","+valabilitate);

                interfataEditarePrajitura.setVisible(false);

                interfataEditarePrajitura.setNume("");
                interfataEditarePrajitura.setPret("");
                interfataEditarePrajitura.setDisponibilitate("");
                interfataEditarePrajitura.setValabilitate("");

                subiect.deleteAll();
                subiect.deleteAll();
                subiect.add(interfataPrajituri);
                subiect.add(interfataAngajat);

                interfataPrajituri.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(interfataEditarePrajitura,persistentaElementFisier.vizualizare("campuri",optiuneLimba),persistentaElementFisier.vizualizare("eroare",optiuneLimba),JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
