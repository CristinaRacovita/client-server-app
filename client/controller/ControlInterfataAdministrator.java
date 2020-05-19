package controller;


import subiect.Subiect;
import transfer.Client;
import view.*;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControlInterfataAdministrator {
    private InterfataAdministrator interfataAdministrator;
    private InterfataAutentificare interfataAutentificare;
    private InterfataAdaugareAngajat interfataAdaugareAngajat;
    private InterfataEditareAngajat interfataEditareAngajat;
    private InterfataAngajat interfataAngajat;
    private Client client;
    private Subiect subiect;

    public ControlInterfataAdministrator(Client client, Subiect subiect, InterfataAngajat interfataAngajat,InterfataAdministrator interfataAdministrator, InterfataAutentificare interfataAutentificare, InterfataAdaugareAngajat interfataAdaugareAngajat, InterfataEditareAngajat interfataEditareAngajat) {
        this.client = client;
        this.subiect = subiect;
        this.interfataAngajat = interfataAngajat;
        this.interfataAdministrator = interfataAdministrator;
        this.interfataAutentificare = interfataAutentificare;
        this.interfataAdaugareAngajat = interfataAdaugareAngajat;
        this.interfataEditareAngajat = interfataEditareAngajat;

        //subiect.add(interfataAdministrator);

        interfataAdministrator.setInapoiListener(new InapoiListener());
        interfataAdministrator.setAdaugaListener(new AdaugaListener());
        interfataAdministrator.addDelteListener(new DeleteListener());
        interfataAdministrator.addVizListener(new VizListener());
        interfataAdministrator.addEditListener(new EditListener());
        interfataAdministrator.addListenerRomana(new RomanaListener());
        interfataAdministrator.addListenerEngleza(new EnglezaListener());
        interfataAdministrator.addListenerFranceza(new FrancezaListener());
        interfataAdministrator.addListenerSettings(new SettingsListener());
    }

    public class SettingsListener implements MenuListener {
        @Override
        public void menuSelected(MenuEvent e) {
            subiect.deleteAll();
            subiect.add(interfataAdaugareAngajat);
            subiect.add(interfataEditareAngajat);
            subiect.add(interfataAdministrator);
            subiect.add(interfataAutentificare);
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
            subiect.setChangedS();
            subiect.notifyS(0);
        }
    }

    public class EnglezaListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            subiect.setChangedS();
            subiect.notifyS(1);
        }
    }

    public class FrancezaListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            subiect.setChangedS();
            subiect.notifyS(2);
        }
    }

    public class EditListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultTableModel angajati = (DefaultTableModel) interfataAdministrator.getTable().getModel();
            int index = interfataAdministrator.getTable().getSelectedRow();
            int modelIndex = interfataAdministrator.getTable().convertRowIndexToModel(index);

            String nume = (String) angajati.getValueAt(modelIndex,1);
            String prenume = (String) angajati.getValueAt(modelIndex,2);
            String varsta = (String) angajati.getValueAt(modelIndex,3);
            String dataAngajare = (String) angajati.getValueAt(modelIndex,4);

            System.out.println("Id = "+Integer.parseInt((String) angajati.getValueAt(modelIndex,0)));
            interfataEditareAngajat.setId(Integer.parseInt((String) angajati.getValueAt(modelIndex,0)));

            if(modelIndex!= -1){
                angajati.removeRow(modelIndex);
            }

            interfataEditareAngajat.setNume(nume);
            interfataEditareAngajat.setPreume(prenume);
            interfataEditareAngajat.setVarsta(varsta);
            interfataEditareAngajat.setDataAngajarii(dataAngajare);
            subiect.deleteAll();
            subiect.add(interfataEditareAngajat);
            subiect.add(interfataAdministrator);
            interfataAdministrator.setVisible(false);
            interfataEditareAngajat.setVisible(true);
        }
    }

    public class InapoiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            interfataAutentificare.setNumeUtilizator("");
            interfataAutentificare.setParola("");
            interfataAdministrator.setVisible(false);
            subiect.deleteAll();
            subiect.add(interfataAutentificare);
            subiect.add(interfataAdministrator);
            subiect.add(interfataAngajat);
            interfataAutentificare.setVisible(true);

        }
    }

    public class AdaugaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            interfataAdministrator.setVisible(false);
            subiect.deleteAll();
            subiect.add(interfataAdaugareAngajat);
            subiect.add(interfataAdministrator);
            interfataAdaugareAngajat.setVisible(true);
        }
    }

    public class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultTableModel angajati = (DefaultTableModel) interfataAdministrator.getTable().getModel();
            int index = interfataAdministrator.getTable().getSelectedRow();
            if(index != -1){
                int modelIndex = interfataAdministrator.getTable().convertRowIndexToModel(index);
                client.transfer("stergere"+(String) angajati.getValueAt(modelIndex,0));
                angajati.removeRow(modelIndex);
            }
            else{
                System.out.println("NIMIC\n");
            }
        }
    }

    public class VizListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            List<model.User> userLista = (List<model.User>) client.transfer("angajati");
            String[][] rows = formRows(userLista);
            interfataAdministrator.tableFiller(rows,userLista.size());
        }
    }

    private String[][] formRows(List<model.User> userLista){
        String[][] rows = new String[100][100];
        List<String> lista = new ArrayList<String>();
        for(int i = 0; i < userLista.size(); i+=1){
            lista.add(String.valueOf(userLista.get(i).getId_salariat()));
            lista.add(userLista.get(i).getNume());
            lista.add(userLista.get(i).getPrenume());
            lista.add(String.valueOf(userLista.get(i).getVarsta()));
            lista.add(userLista.get(i).getDataAgajarii());
        }
        int i = 0;
        for(int k = 0; k < lista.size()/5; k++) {
            for (int j = 0; j < 5; j++) {
                rows[k][j] = lista.get(i);
                i++;
            }
        }
        return rows;
    }
}
