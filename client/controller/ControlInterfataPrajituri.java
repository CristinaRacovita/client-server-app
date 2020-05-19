package controller;

import prelucrare.PersistentaElementFisier;
import subiect.Subiect;
import transfer.Client;
import view.*;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControlInterfataPrajituri {
    private InterfataPrajituri interfataPrajituri;
    private InterfataAngajat interfataAngajat;
    private InterfataAdaugarePrajitura interfataAdaugarePrajitura;
    private InterfataEditarePrajitura interfataEditarePrajitura;
    private ControlInterfataAngajat controlInterfataAngajat;
    private InterfataAutentificare interfataAutentificare;
    private int optiuneLimba;
    private Client client;
    private Subiect subiect;
    private int id;

    public ControlInterfataPrajituri(Client client,Subiect subiect,InterfataAutentificare interfataAutentificare,InterfataPrajituri interfataPrajituri,InterfataAngajat interfataAngajat,InterfataAdaugarePrajitura interfataAdaugarePrajitura,InterfataEditarePrajitura interfataEditarePrajitura,ControlInterfataAngajat controlInterfataAngajat){
        this.interfataPrajituri = interfataPrajituri;
        this.interfataAngajat = interfataAngajat;
        this.subiect = subiect;
        this.interfataAutentificare = interfataAutentificare;
        this.interfataAdaugarePrajitura = interfataAdaugarePrajitura;
        this.interfataEditarePrajitura = interfataEditarePrajitura;
        this.controlInterfataAngajat = controlInterfataAngajat;
        this.client = client;

        interfataPrajituri.addComboListener(new ActionComboListener());
        interfataPrajituri.getMinim().addMouseListener(new MinimMouseListener());
        interfataPrajituri.getMaxim().addMouseListener(new MaximMouseListener());
        interfataPrajituri.getData().addMouseListener(new DataMouseListener());
        interfataPrajituri.setInapoiButon(new InapoiListener());
        interfataPrajituri.getCautareText().addMouseListener(new NumeMouseListener());
        interfataPrajituri.setAdaugareButon(new AdaugaListener());
        interfataPrajituri.addDeleteListener(new DeleteListener());
        interfataPrajituri.addEditListener(new EditListener());
        interfataPrajituri.addListenerRomana(new RomanaListener());
        interfataPrajituri.addListenerEngleza(new EnglezaListener());
        interfataPrajituri.addListenerFranceza(new FrancezaListener());
        interfataPrajituri.addListenerSettings(new SettingsListener());

    }

    public class SettingsListener implements MenuListener {
        @Override
        public void menuSelected(MenuEvent e) {
            /*subiect.deleteAll();
            subiect.add(interfataPrajituri);
            subiect.add(interfataAngajat);
            subiect.add(interfataAdaugarePrajitura);
            subiect.add(interfataEditarePrajitura);*/
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

    public class ActionComboListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectare = interfataPrajituri.getCombo();
            System.out.println(selectare);
            PersistentaElementFisier persistentaElementFisier = new PersistentaElementFisier();
            String viz1 = persistentaElementFisier.vizualizare("vizprj", 0);
            String pret1 = persistentaElementFisier.vizualizare("pret", 0);
            String nume1 = persistentaElementFisier.vizualizare("nume", 0);
            String disp1 = persistentaElementFisier.vizualizare("disp", 0);
            String valab1 = persistentaElementFisier.vizualizare("valab", 0);

            String viz2 = persistentaElementFisier.vizualizare("vizprj", 1);
            String pret2 = persistentaElementFisier.vizualizare("pret", 1);
            String nume2 = persistentaElementFisier.vizualizare("nume", 1);
            String disp2 = persistentaElementFisier.vizualizare("disp", 1);
            String valab2 = persistentaElementFisier.vizualizare("valab", 1);

            String viz3 = persistentaElementFisier.vizualizare("vizprj", 2);
            String pret3 = persistentaElementFisier.vizualizare("pret", 2);
            String nume3 = persistentaElementFisier.vizualizare("nume", 2);
            String disp3 = persistentaElementFisier.vizualizare("disp", 2);
            String valab3 = persistentaElementFisier.vizualizare("valab", 2);


            if(selectare.equals(pret1)||selectare.equals(pret2)||selectare.equals(pret3)) {
                interfataPrajituri.deletePanel();
                JTextField minim = interfataPrajituri.getMinim();
                JTextField maxim = interfataPrajituri.getMaxim();

                JPanel panelMinim = new JPanel();
                panelMinim.add(minim);

                JPanel panelCriteriiText = new JPanel();

                JPanel panelMaxim = new JPanel();
                panelMaxim.add(maxim);

                panelCriteriiText.add(panelMinim);
                panelCriteriiText.add(panelMaxim);
                panelCriteriiText.setLayout(new BoxLayout(panelCriteriiText, BoxLayout.X_AXIS));
                interfataPrajituri.addPanel(panelCriteriiText);
                String[] pretMin = new String[1];
                String[] pretMax = new String[1];
                minim.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pretMin[0] = minim.getText();
                        pretMax[0] = maxim.getText();
                        if (pretMax[0] != null || pretMin[0] != null) {
                            List<model.Prajitura> prajituraLista = null;
                            if (!controlInterfataAngajat.getCofetarie().equals("")) {
                                prajituraLista = (List<model.Prajitura>) client.transfer("prajiturimm" + controlInterfataAngajat.getCofetarie() + "," + minim.getText() + "," + maxim.getText());
                            }
                            String[][] rows1 = formRows(prajituraLista);
                            interfataPrajituri.tableFiller(rows1, prajituraLista.size());
                        }
                    }
                });
                maxim.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pretMin[0] = minim.getText();
                        pretMax[0] = maxim.getText();
                        if (pretMax[0] != null || pretMin[0] != null) {
                            List<model.Prajitura> prajituraLista = null;
                            if (!controlInterfataAngajat.getCofetarie().equals("")) {
                                prajituraLista = (List<model.Prajitura>) client.transfer("prajiturimm" + controlInterfataAngajat.getCofetarie() + "," + minim.getText() + "," + maxim.getText());
                            }
                            String[][] rows1 = formRows(prajituraLista);
                            interfataPrajituri.tableFiller(rows1, prajituraLista.size());
                        }
                    }
                });
            }
              else if (selectare.equals(valab1)||selectare.equals(valab2)||selectare.equals(valab3)) {
                interfataPrajituri.deletePanel();
                JTextField data = interfataPrajituri.getData();
                JPanel panelData = new JPanel();
                panelData.add(data);
                interfataPrajituri.addPanel(panelData);
                final String[] dataText = new String[1];
                data.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dataText[0] = data.getText();
                        List<model.Prajitura> prajituraLista = null;
                        if (!controlInterfataAngajat.getCofetarie().equals("")) {
                            prajituraLista = (List<model.Prajitura>) client.transfer("prajiturif" + controlInterfataAngajat.getCofetarie() + ",valabilitate" + "," + data.getText());
                        }
                        String[][] rows1 = formRows(prajituraLista);
                        interfataPrajituri.tableFiller(rows1, prajituraLista.size());
                    }
                });
            }
                else if(selectare.equals(nume2)||selectare.equals(nume1)||selectare.equals(nume3)) {
                interfataPrajituri.deletePanel();
                JTextField nume = interfataPrajituri.getCautareText();
                JPanel panelNume = new JPanel();
                panelNume.add(nume);
                interfataPrajituri.addPanel(panelNume);
                final String[] numeText = new String[1];
                nume.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        numeText[0] = nume.getText();
                        List<model.Prajitura> prajituraLista = null;
                        if (!controlInterfataAngajat.getCofetarie().equals("")) {
                            prajituraLista = (List<model.Prajitura>) client.transfer("prajiturif" + controlInterfataAngajat.getCofetarie() + ",nume" + "," + nume.getText());
                        }
                        String[][] rows1 = formRows(prajituraLista);
                        interfataPrajituri.tableFiller(rows1, prajituraLista.size());
                    }
                });

            }
                else if(selectare.equals(viz1)||selectare.equals(viz2)||selectare.equals(viz3)) {
                    System.out.println("DAAA");
                interfataPrajituri.deletePanel();
                List<model.Prajitura> prajituraLista = null;
                if (!controlInterfataAngajat.getCofetarie().equals("")) {
                    prajituraLista = (List<model.Prajitura>) client.transfer("prajituri" + controlInterfataAngajat.getCofetarie());
                }
                String[][] rows = formRows(prajituraLista);
                interfataPrajituri.tableFiller(rows, prajituraLista.size());
            }
               else if(selectare.equals(disp1)||selectare.equals(disp2)||selectare.equals(disp3)) {
                interfataPrajituri.deletePanel();
                List<model.Prajitura> prajituraLista1 = null;
                if (!controlInterfataAngajat.getCofetarie().equals("")) {
                    prajituraLista1 = (List<model.Prajitura>) client.transfer("prajituridisp" + controlInterfataAngajat.getCofetarie());
                }
                String[][] rows2 = formRows(prajituraLista1);
                interfataPrajituri.tableFiller(rows2, prajituraLista1.size());
            }
                    //interfataPrajituri.deletePanel();
                    interfataPrajituri.getContentPane().validate();
                    interfataPrajituri.getContentPane().repaint();
            }
        }
    public class MinimMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            interfataPrajituri.setTextMinim("");
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
    public class MaximMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            interfataPrajituri.setTextMaxim("");
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
    public class DataMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            interfataPrajituri.setTextData("");
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
    public class NumeMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            interfataPrajituri.setCautareText("");
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
    public class InapoiListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String[][] row ={{}};
            interfataPrajituri.setCombo();
            interfataPrajituri.tableFiller(row,0);
            interfataPrajituri.setVisible(false);
            subiect.deleteAll();
            subiect.add(interfataAngajat);
            subiect.add(interfataPrajituri);
            subiect.add(interfataAutentificare);
            interfataAngajat.setVisible(true);
        }
    }
    public class AdaugaListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(controlInterfataAngajat.getCofetarie().equals("Toate")){
                JOptionPane.showMessageDialog(interfataPrajituri,"In acest mod de vizualizare nu se pot adauga produse!\n                    Va rugam selectati altul!","Eroare",JOptionPane.ERROR_MESSAGE);
            }else {
                interfataPrajituri.setVisible(false);
                subiect.deleteAll();
                subiect.add(interfataAdaugarePrajitura);
                subiect.add(interfataPrajituri);
                interfataAdaugarePrajitura.setVisible(true);
            }
        }
    }
    public class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultTableModel prajituri = (DefaultTableModel) interfataPrajituri.getTable().getModel();
            int index = interfataPrajituri.getTable().getSelectedRow();
            if(index != -1){
                int modelIndex = interfataPrajituri.getTable().convertRowIndexToModel(index);
                client.transfer("stergpraj"+prajituri.getValueAt(modelIndex,0));
                prajituri.removeRow(modelIndex);
            }
            else{
                System.out.println("Nu ati selectat nimic!");
            }
        }
    }
    private String[][] formRows(List<model.Prajitura> prajituraLista){
        String[][] rows = new String[100][100];
        List<String> lista = new ArrayList<String>();
        for(int i = 0; i < prajituraLista.size(); i+=1){
            lista.add(String.valueOf(prajituraLista.get(i).getId_prajitura()));
            lista.add(prajituraLista.get(i).getNumeCofetarie());
            lista.add(prajituraLista.get(i).getNumePrajitura());
            lista.add(prajituraLista.get(i).getPret()+"");
            if(prajituraLista.get(i).isDisponibilitateProdus()) {
                lista.add("pe stoc");
            }
            else{
                lista.add("stoc epuizat");
            }
            lista.add(prajituraLista.get(i).getValabilitate());
        }
        int i = 0;
        for(int k = 0; k < lista.size()/6; k++) {
            for (int j = 0; j < 6; j++) {
                rows[k][j] = lista.get(i);
                i++;
            }
        }
        return rows;
    }
    public class EditListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultTableModel prajituri = (DefaultTableModel) interfataPrajituri.getTable().getModel();
            int index = interfataPrajituri.getTable().getSelectedRow();
            if(controlInterfataAngajat.getCofetarie().equals("Toate")){
                JOptionPane.showMessageDialog(interfataPrajituri,"In acest mod de vizualizare nu se pot face editari!\n                    Va rugam selectati altul!","Eroare",JOptionPane.ERROR_MESSAGE);
            }else {
                if (index != -1) {
                    int modelIndex = interfataPrajituri.getTable().convertRowIndexToModel(index);
                    id = Integer.parseInt((String) prajituri.getValueAt(modelIndex,0));
                    interfataEditarePrajitura.setNume((String) prajituri.getValueAt(modelIndex, 2));
                    interfataEditarePrajitura.setPret((String) prajituri.getValueAt(modelIndex, 3));
                    interfataEditarePrajitura.setDisponibilitate((String) prajituri.getValueAt(modelIndex, 4));
                    interfataEditarePrajitura.setValabilitate((String) prajituri.getValueAt(modelIndex, 5));
                    interfataPrajituri.setVisible(false);
                    prajituri.removeRow(modelIndex);
                    subiect.deleteAll();
                    subiect.add(interfataEditarePrajitura);
                    subiect.add(interfataPrajituri);
                    interfataEditarePrajitura.setVisible(true);
                }
            }

        }
    }
    public int getId(){
        return id;
    }
}

