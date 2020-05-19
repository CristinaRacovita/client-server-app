package controller;

import prelucrare.PersistentaElementFisier;
import subiect.Subiect;
import transfer.Client;
import view.InterfataAdministrator;
import view.InterfataAutentificare;
import view.InterfataEditareAngajat;
import view.InterfataEditarePrajitura;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class ControlInterfataEditareAngajat {
    private InterfataAdministrator interfataAdministrator;
    private InterfataEditareAngajat interfataEditareAngajat;
    private InterfataAutentificare interfataAutentificare;
    private Client client;
    private int optiuneLimba;
    Subiect subiect;

    public ControlInterfataEditareAngajat(Client client,Subiect subiect,InterfataAutentificare interfataAutentificare,InterfataAdministrator interfataAdministrator, InterfataEditareAngajat interfataEditareAngajat){
        this.subiect = subiect;
        this.interfataAdministrator = interfataAdministrator;
        this.interfataEditareAngajat = interfataEditareAngajat;
        this.client = client;
        this.interfataAutentificare = interfataAutentificare;

        interfataEditareAngajat.addInpoiListener(new InapoiListener());
        interfataEditareAngajat.addListenerRomana(new RomanaListener());
        interfataEditareAngajat.addListenerEngleza(new EnglezaListener());
        interfataEditareAngajat.addListenerFranceza(new FrancezaListener());
        interfataEditareAngajat.addListenerSettings(new SettingsListener());
    }

    public class SettingsListener implements MenuListener {
        @Override
        public void menuSelected(MenuEvent e) {
            subiect.deleteAll();
            subiect.add(interfataEditareAngajat);
            subiect.add(interfataAdministrator);
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


    public class InapoiListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String nume = interfataEditareAngajat.getNumeText();
            String prenume = interfataEditareAngajat.getPrenumeText();
            String varsta = interfataEditareAngajat.getVarstaText();
            String dataAngajare = interfataEditareAngajat.getDataAngajarii();
            PersistentaElementFisier persistentaElementFisier = new PersistentaElementFisier();
            if(nume.compareTo("") != 0 && prenume.compareTo("") != 0 && varsta.compareTo("") != 0 && dataAngajare.compareTo("") != 0) {
                model.Persoana persoana;
                persoana = new model.Persoana(nume,prenume,Integer.parseInt(varsta),dataAngajare);
                model.User user = new model.User(persoana,"angajat");
                DefaultTableModel model = (DefaultTableModel) interfataAdministrator.getTable().getModel();
                String[] row = {String.valueOf(interfataEditareAngajat.getId()),nume,prenume,varsta,dataAngajare};
                model.addRow(row);
                java.util.List<model.ContUser> contUsers = (List<model.ContUser>) client.transfer("editeaza"+interfataEditareAngajat.getId()+","+user.getNume()+","+user.getPrenume()+","+user.getVarsta()+","+user.getDataAgajarii());
                contUsers.get(0).setId_cont(interfataEditareAngajat.getId());
                model.ContUser contUser = contUsers.get(0);
                String[] option = {"OK"};
                int result = JOptionPane.showOptionDialog(null,persistentaElementFisier.vizualizare("numeUtilizator",optiuneLimba)+": "+ contUser.getNumeUtilizator()+ "\n"+persistentaElementFisier.vizualizare("pass",optiuneLimba)+": "+ contUser.getParola(),persistentaElementFisier.vizualizare("editaresucc",optiuneLimba),JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,option,option[0]);
                if(result == JOptionPane.OK_OPTION) {
                    interfataEditareAngajat.setVisible(false);
                    interfataEditareAngajat.setNume("");
                    interfataEditareAngajat.setPreume("");
                    interfataEditareAngajat.setVarsta("");
                    interfataEditareAngajat.setDataAngajarii("");
                    interfataAdministrator.setVisible(true);
                }
            }
            else{
                JOptionPane.showMessageDialog(interfataEditareAngajat,persistentaElementFisier.vizualizare("campuri",optiuneLimba),persistentaElementFisier.vizualizare("eroare",optiuneLimba),JOptionPane.ERROR_MESSAGE);
            }
            subiect.deleteAll();
            subiect.add(interfataAdministrator);
            subiect.add(interfataAutentificare);
            subiect.add(interfataEditareAngajat);
        }
    }
    private String indexMaxim(){
        int maxim = -1;
        DefaultTableModel model = (DefaultTableModel) interfataAdministrator.getTable().getModel();
        for(int i = 0; i < model.getRowCount(); i++){
            if(Integer.parseInt((String) model.getValueAt(i,0)) > maxim){
                maxim = Integer.parseInt((String) model.getValueAt(i,0));
            }
        }
        return String.valueOf(maxim+1);
    }
}
