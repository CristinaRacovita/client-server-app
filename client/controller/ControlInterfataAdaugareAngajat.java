package controller;

import prelucrare.PersistentaElementFisier;
import subiect.Subiect;
import transfer.Client;
import view.InterfataAdaugareAngajat;
import view.InterfataAdministrator;
import view.InterfataAutentificare;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControlInterfataAdaugareAngajat {
    private InterfataAdaugareAngajat interfataAdaugareAngajat;
    private InterfataAdministrator interfataAdministrator;
    private InterfataAutentificare interfataAutentificare;
    private Client client;
    private int optiuneLimba;
    private Subiect subiect;

    public ControlInterfataAdaugareAngajat(Client client,Subiect subiect,InterfataAutentificare interfataAutentificare,InterfataAdaugareAngajat interfataAdaugareAngajat, InterfataAdministrator interfataAdministrator){
        this.subiect = subiect;
        this.interfataAdaugareAngajat = interfataAdaugareAngajat;
        this.interfataAdministrator = interfataAdministrator;
        this.interfataAutentificare = interfataAutentificare;
        this.client = client;
        interfataAdaugareAngajat.addInpoiListener(new FinishListener());
        interfataAdaugareAngajat.addListenerRomana(new RomanaListener());
        interfataAdaugareAngajat.addListenerEngleza(new EnglezaListener());
        interfataAdaugareAngajat.addListenerFranceza(new FrancezaListener());
        interfataAdaugareAngajat.addListenerSettings(new SettingsListener());
    }

    public class SettingsListener implements MenuListener {
        @Override
        public void menuSelected(MenuEvent e) {
            subiect.deleteAll();
            subiect.add(interfataAdaugareAngajat);
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

    public class FinishListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String nume = interfataAdaugareAngajat.getNumeText();
            String prenume = interfataAdaugareAngajat.getPrenumeText();
            String varsta = interfataAdaugareAngajat.getVarstaText();
            String dataAngajare = interfataAdaugareAngajat.getDataAngajarii();
            PersistentaElementFisier persistentaElementFisier = new PersistentaElementFisier();

            if(nume.compareTo("") != 0 && prenume.compareTo("") != 0 && varsta.compareTo("") != 0 && dataAngajare.compareTo("") != 0) {
                model.Persoana persoana = new model.Persoana(nume,prenume,Integer.parseInt(varsta),dataAngajare);
                model.User user = new model.User(persoana,"angajat");
                DefaultTableModel model = (DefaultTableModel) interfataAdministrator.getTable().getModel();
                List<model.ContUser> contUsers = (List<model.ContUser>) client.transfer("salvare"+user.getNume()+","+user.getPrenume()+","+user.getVarsta()+","+user.getDataAgajarii());
                model.ContUser contUser = contUsers.get(0);
                String[] row = {indexMaxim(),nume,prenume,varsta,dataAngajare};
                model.addRow(row);

                String[] option = {"OK"};
                int result = JOptionPane.showOptionDialog(null,persistentaElementFisier.vizualizare("numeUtilizator",optiuneLimba)+": "+ contUser.getNumeUtilizator()+ "\n"+persistentaElementFisier.vizualizare("pass",optiuneLimba)+": "+ contUser.getParola(),persistentaElementFisier.vizualizare("succesangj",optiuneLimba),JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,option,option[0]);
                if(result == JOptionPane.OK_OPTION) {
                    interfataAdaugareAngajat.setVisible(false);
                    interfataAdaugareAngajat.setNume("");
                    interfataAdaugareAngajat.setPreume("");
                    interfataAdaugareAngajat.setVarsta("");
                    interfataAdaugareAngajat.setDataAngajarii("");
                    interfataAdministrator.setVisible(true);
                    subiect.deleteAll();
                    subiect.add(interfataAdministrator);
                    subiect.add(interfataAdaugareAngajat);
                    subiect.add(interfataAutentificare);
                }
            }
            else{
                JOptionPane.showMessageDialog(interfataAdaugareAngajat,persistentaElementFisier.vizualizare("campuri",optiuneLimba),persistentaElementFisier.vizualizare("eroare",optiuneLimba),JOptionPane.ERROR_MESSAGE);
            }
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
