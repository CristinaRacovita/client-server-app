package controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import prelucrare.BuilderStatisticaPrajitura;
import prelucrare.PersistentaElementFisier;
import prelucrare.SalvareRapoarte;
import prelucrare.StatisticaPrajituri;
import subiect.Subiect;
import transfer.Client;
import view.*;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ControlInterfataAngajat {
    private InterfataAngajat interfataAngajat;
    private InterfataAutentificare interfataAutentificare;
    private InterfataPrajituri interfataPrajituri;
    private InterfataAdaugarePrajitura interfataAdaugarePrajitura;
    private InterfataEditarePrajitura interfataEditarePrajitura;
    private InterfataAdministrator interfataAdministrator;
    private int optiuneLimba;
    private String cofetarie;
    private Client client;
    private Subiect subiect;

    public ControlInterfataAngajat(Client client,Subiect subiect,InterfataAdministrator interfataAdministrator,InterfataEditarePrajitura interfataEditarePrajitura,InterfataAdaugarePrajitura interfataAdaugarePrajitura,InterfataAngajat interfataAngajat,InterfataAutentificare interfataAutentificare,InterfataPrajituri interfataPrajituri){
        this.client = client;
        this.subiect = subiect;
        this.interfataAngajat = interfataAngajat;
        this.interfataAdministrator = interfataAdministrator;
        this.interfataAutentificare = interfataAutentificare;
        this.interfataPrajituri = interfataPrajituri;
        this.interfataAdaugarePrajitura = interfataAdaugarePrajitura;
        this.interfataEditarePrajitura = interfataEditarePrajitura;

        interfataAngajat.addInapoiListener(new ActionInapoiListener());
        interfataAngajat.addVizualizareListener(new ActionVizualizareListener());
        interfataAngajat.addSaveListener(new SaveListener());
        interfataAngajat.addListenetStatistici(new StatisticiListener());
        interfataAngajat.addListenerRomana(new RomanaListener());
        interfataAngajat.addListenerEngleza(new EnglezaListener());
        interfataAngajat.addListenerFranceza(new FrancezaListener());
        interfataAngajat.addListenerSettings(new SettingsListener());

    }


    public class SettingsListener implements MenuListener {
        @Override
        public void menuSelected(MenuEvent e) {
//            subiect.deleteAll();
//            subiect.add(interfataPrajituri);
//            subiect.add(interfataAngajat);
//            subiect.add(interfataAutentificare);
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

    public class ActionInapoiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           interfataAngajat.setVisible(false);
           interfataAutentificare.setNumeUtilizator("");
           interfataAutentificare.setParola("");
           subiect.deleteAll();
           subiect.add(interfataAutentificare);
           subiect.add(interfataAdministrator);
           subiect.add(interfataAngajat);
           interfataAutentificare.setVisible(true);
        }
    }
    public class ActionVizualizareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cofetarie = interfataAngajat.getVizualizare();
            interfataAngajat.setVisible(false);
            subiect.deleteAll();
            subiect.add(interfataPrajituri);
            subiect.add(interfataAdaugarePrajitura);
            subiect.add(interfataEditarePrajitura);
            interfataPrajituri.setVisible(true);
        }
    }
    public class SaveListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String path = System.getProperty("user.dir");
            File userDirectory = new File(path);
            String format = interfataAngajat.getFormatText();
            cofetarie = interfataAngajat.getVizualizareRaport();
            PersistentaElementFisier persistentaElementFisier = new PersistentaElementFisier();

            List<model.Prajitura> prajituraLista = (List<model.Prajitura>) client.transfer("prajituri"+cofetarie);

            try {
                switch (format){
                    case "csv":
                        String fileCSV = "raport.csv";
                        FileWriter writer = new FileWriter(fileCSV);
                        SalvareRapoarte.CSVFile(writer,prajituraLista);
                        Desktop.getDesktop().open(userDirectory);
                        break;
                    case "xml":
                        String fileXML = "raport.xml";
                        SalvareRapoarte.XMLFile(cofetarie,fileXML, new String[]{persistentaElementFisier.vizualizare("cofetarie",optiuneLimba), persistentaElementFisier.vizualizare("numeprj",optiuneLimba), persistentaElementFisier.vizualizare("pret",optiuneLimba), persistentaElementFisier.vizualizare("disp",optiuneLimba), persistentaElementFisier.vizualizare("valab",optiuneLimba)},prajituraLista);
                        Desktop.getDesktop().open(userDirectory);
                        break;
                    case "json":
                        String fileJSON = "raport.json";
                        FileWriter w = new FileWriter(fileJSON);
                        SalvareRapoarte.jsonFile(w, new String[]{persistentaElementFisier.vizualizare("cofetarie",optiuneLimba), persistentaElementFisier.vizualizare("numeprj",optiuneLimba), persistentaElementFisier.vizualizare("pret",optiuneLimba), persistentaElementFisier.vizualizare("disp",optiuneLimba), persistentaElementFisier.vizualizare("valab",optiuneLimba)},prajituraLista);
                        Desktop.getDesktop().open(userDirectory);
                        break;
                    default:
                        JOptionPane.showMessageDialog(interfataAngajat,"Va rugam introduceti alt format!","Format necunoscut",JOptionPane.ERROR_MESSAGE);
                        break;
                }
            } catch (IOException | ParserConfigurationException | TransformerException ex) {
                ex.printStackTrace();
            }
        }
    }

    public class StatisticiListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String cofetarie = interfataAngajat.getVizualizareStatistica();

            BuilderStatisticaPrajitura builderStatisticaPrajitura = new BuilderStatisticaPrajitura();
            StatisticaPrajituri statisticaPrajituri = builderStatisticaPrajitura.creareStatisticaPrajituri((List<model.Prajitura>) client.transfer("prajituri"+cofetarie));
            boolean tot = false;
            if(cofetarie.equals("TOATE")||cofetarie.equals("All")||cofetarie.equals("Tous")||cofetarie.equals("Toate")){
                tot = true;
            }

            try {
                statisticaPrajituri.generareLineChart("Statistica Prajituri "+cofetarie,tot);
                statisticaPrajituri.generarePieChart("Statistica Prajituri "+cofetarie,tot);
                statisticaPrajituri.generareBarChart("Statistica Prajituri "+cofetarie,tot);
                File userDirectory = new File(System.getProperty("user.dir"));
                Desktop.getDesktop().open(userDirectory);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public String getCofetarie() {
        return cofetarie;
    }

}
