package view;

import prelucrare.PersistentaElementFisier;

import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class InterfataAdaugareAngajat extends JFrame implements Observer {

    private int optiuneLimba;
    private JMenu menu,submenu;
    private JMenuItem rom,eng,fr;
    private JTextField nume = new JTextField(10);
    private JTextField prenume = new JTextField(10);
    private JTextField varsta = new JTextField(10);
    private JTextField data = new JTextField(10);
    private JButton finish = new JButton("OK");
    private  JLabel numeAngajat;
    private JLabel prenumeAngajat;
    private JLabel varstaAngajat;
    private JLabel dataAngajarii;
    private PersistentaElementFisier persistentaElementFisier = new PersistentaElementFisier();

    public InterfataAdaugareAngajat(){

        this.setTitle(persistentaElementFisier.vizualizare("add",optiuneLimba));
        this.setSize(300,300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu(persistentaElementFisier.vizualizare("setari",optiuneLimba));
        submenu = new JMenu(persistentaElementFisier.vizualizare("schimbareLimba",optiuneLimba));
        rom = new JMenuItem(persistentaElementFisier.vizualizare("rom",optiuneLimba));
        eng = new JMenuItem(persistentaElementFisier.vizualizare("eng",optiuneLimba));
        fr = new JMenuItem(persistentaElementFisier.vizualizare("fr",optiuneLimba));

        submenu.add(rom);
        submenu.add(eng);
        submenu.add(fr);
        menu.add(submenu);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);


        JPanel panelNumeText = new JPanel();
        panelNumeText.add(nume);

        JPanel panelNumeLabel = new JPanel();
        numeAngajat = new JLabel(persistentaElementFisier.vizualizare("nume",optiuneLimba)+":");
        panelNumeLabel.add(numeAngajat);

        JPanel panelNume = new JPanel();
        panelNume.add(panelNumeLabel);
        panelNume.add(panelNumeText);
        panelNume.setLayout(new BoxLayout(panelNume, BoxLayout.X_AXIS));

        JPanel panelPrenumeText = new JPanel();
        panelPrenumeText.add(prenume);

        JPanel panelPrenumeLabel = new JPanel();
        prenumeAngajat = new JLabel(persistentaElementFisier.vizualizare("prenume",optiuneLimba)+":");
        panelPrenumeLabel.add(prenumeAngajat);

        JPanel panelPret = new JPanel();
        panelPret.add(panelPrenumeLabel);
        panelPret.add(panelPrenumeText);
        panelPret.setLayout(new BoxLayout(panelPret, BoxLayout.X_AXIS));

        JPanel panelVarstaText = new JPanel();
        panelVarstaText.add(varsta);

        JPanel panelVarstaLabel = new JPanel();
        varstaAngajat = new JLabel(persistentaElementFisier.vizualizare("varsta",optiuneLimba)+":");
        panelVarstaLabel.add(varstaAngajat);

        JPanel panelDisponibilitate = new JPanel();
        panelDisponibilitate.add(panelVarstaLabel);
        panelDisponibilitate.add(panelVarstaText);
        panelDisponibilitate.setLayout(new BoxLayout(panelDisponibilitate, BoxLayout.X_AXIS));

        JPanel panelAngajareText = new JPanel();
        panelAngajareText.add(data);

        JPanel panelAngajareLabel = new JPanel();
        dataAngajarii = new JLabel(persistentaElementFisier.vizualizare("datangj",optiuneLimba)+":");
        panelAngajareLabel.add(dataAngajarii);

        JPanel panelValabilitate = new JPanel();
        panelValabilitate.add(panelAngajareLabel);
        panelValabilitate.add(panelAngajareText);
        panelValabilitate.setLayout(new BoxLayout(panelValabilitate, BoxLayout.X_AXIS));

        JPanel panelButon = new JPanel();
        panelButon.add(finish);

        JPanel panelTotal = new JPanel();
        panelTotal.add(panelNume);
        panelTotal.add(panelPret);
        panelTotal.add(panelDisponibilitate);
        panelTotal.add(panelValabilitate);
        panelTotal.add(panelButon);
        panelTotal.setLayout(new BoxLayout(panelTotal, BoxLayout.Y_AXIS));

        this.add(panelTotal);
    }

    public void addInpoiListener(ActionListener a){
        finish.addActionListener(a);
    }
    public String getNumeText(){ return nume.getText(); }
    public String getPrenumeText() { return prenume.getText(); }
    public String getVarstaText() { return varsta.getText(); }
    public String getDataAngajarii() { return data.getText(); }
    public void setNume(String s){ nume.setText(s); }
    public void setPreume(String s){ prenume.setText(s); }
    public void setVarsta(String s){ varsta.setText(s); }
    public void setDataAngajarii(String s){ data.setText(s); }
    public void addListenerRomana(ActionListener a){
        rom.addActionListener(a);
    }

    public void addListenerEngleza(ActionListener a){
        eng.addActionListener(a);
    }

    public void addListenerFranceza(ActionListener a){
        fr.addActionListener(a);
    }

    public void addListenerSettings(MenuListener a){
        menu.addMenuListener(a);
    }

    @Override
    public void update(Observable o, Object arg) {
        optiuneLimba = (int) arg;
        this.setTitle(persistentaElementFisier.vizualizare("add",optiuneLimba));
        menu.setText(persistentaElementFisier.vizualizare("setari",optiuneLimba));
        submenu.setText(persistentaElementFisier.vizualizare("schimbareLimba",optiuneLimba));
        rom.setText(persistentaElementFisier.vizualizare("rom",optiuneLimba));
        eng.setText(persistentaElementFisier.vizualizare("eng",optiuneLimba));
        fr.setText(persistentaElementFisier.vizualizare("fr",optiuneLimba));
        dataAngajarii.setText(persistentaElementFisier.vizualizare("datangj",optiuneLimba)+":");
        varstaAngajat.setText(persistentaElementFisier.vizualizare("varsta",optiuneLimba)+":");
        prenumeAngajat.setText(persistentaElementFisier.vizualizare("prenume",optiuneLimba)+":");
        numeAngajat.setText(persistentaElementFisier.vizualizare("nume",optiuneLimba)+":");

        this.revalidate();
        this.repaint();
    }
}
