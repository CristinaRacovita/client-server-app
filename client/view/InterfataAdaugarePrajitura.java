package view;

import prelucrare.PersistentaElementFisier;

import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class InterfataAdaugarePrajitura extends JFrame implements Observer {
    private int optiuneLimba;
    private PersistentaElementFisier persistentaElementFisier = new PersistentaElementFisier();
    private JLabel numePrajitura;
    private JTextField nume = new JTextField(10);

    private JMenu menu,submenu;
    private JMenuItem rom,eng,fr;

    private JLabel pretPrajitura;
    private JTextField pret = new JTextField(10);

    private JLabel disponibilitatePrajitura;
    private JTextField disponibilitate = new JTextField(10);

    private JLabel valabilitatePrajitura;
    private JTextField valabilitate = new JTextField(10);

    private JButton finish = new JButton("OK");

    public InterfataAdaugarePrajitura(){
        this.setTitle(persistentaElementFisier.vizualizare("adaugaprj",optiuneLimba));
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

        numePrajitura = new JLabel(persistentaElementFisier.vizualizare("numeprj",optiuneLimba)+": ");
        pretPrajitura = new JLabel(persistentaElementFisier.vizualizare("pret",optiuneLimba)+": ");
        disponibilitatePrajitura = new JLabel(persistentaElementFisier.vizualizare("disp",optiuneLimba)+": ");
        valabilitatePrajitura = new JLabel(persistentaElementFisier.vizualizare("valab",optiuneLimba)+": ");

        JPanel panelNumeText = new JPanel();
        panelNumeText.add(nume);

        JPanel panelNumeLabel = new JPanel();
        panelNumeLabel.add(numePrajitura);

        JPanel panelNume = new JPanel();
        panelNume.add(panelNumeLabel);
        panelNume.add(panelNumeText);
        panelNume.setLayout(new BoxLayout(panelNume, BoxLayout.X_AXIS));


        JPanel panelPretText = new JPanel();
        panelPretText.add(pret);

        JPanel panelPretLabel = new JPanel();
        panelPretLabel.add(pretPrajitura);

        JPanel panelPret = new JPanel();
        panelPret.add(panelPretLabel);
        panelPret.add(panelPretText);
        panelPret.setLayout(new BoxLayout(panelPret, BoxLayout.X_AXIS));

        JPanel panelDisponibilitateText = new JPanel();
        panelDisponibilitateText.add(disponibilitate);

        JPanel panelDisponibilitateLabel = new JPanel();
        panelDisponibilitateLabel.add(disponibilitatePrajitura);

        JPanel panelDisponibilitate = new JPanel();
        panelDisponibilitate.add(panelDisponibilitateLabel);
        panelDisponibilitate.add(panelDisponibilitateText);
        panelDisponibilitate.setLayout(new BoxLayout(panelDisponibilitate, BoxLayout.X_AXIS));

        JPanel panelValabilitateText = new JPanel();
        panelValabilitateText.add(valabilitate);

        JPanel panelValabilitateLabel = new JPanel();
        panelValabilitateLabel.add(valabilitatePrajitura);

        JPanel panelValabilitate = new JPanel();
        panelValabilitate.add(panelValabilitateLabel);
        panelValabilitate.add(panelValabilitateText);
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
    public String getDenumireText(){ return nume.getText(); }
    public String getPretText() { return pret.getText(); }
    public String getDisponibilitateText() { return disponibilitate.getText(); }
    public String getValabilitateText() { return valabilitate.getText(); }

    public void setNume(String n) {
        nume.setText(n);
    }

    public void setPret(String p) {
        pret.setText(p);
    }

    public void setDisponibilitate(String d) {
        disponibilitate.setText(d);
    }

    public void setValabilitate(String v) {
        valabilitate.setText(v);
    }

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
        this.setTitle(persistentaElementFisier.vizualizare("adaugaprj",optiuneLimba));
        numePrajitura.setText(persistentaElementFisier.vizualizare("numeprj",optiuneLimba)+": ");
        pretPrajitura.setText(persistentaElementFisier.vizualizare("pret",optiuneLimba)+": ");
        disponibilitatePrajitura.setText(persistentaElementFisier.vizualizare("disp",optiuneLimba)+": ");
        valabilitatePrajitura.setText(persistentaElementFisier.vizualizare("valab",optiuneLimba)+": ");

        menu.setText(persistentaElementFisier.vizualizare("setari",optiuneLimba));
        submenu.setText(persistentaElementFisier.vizualizare("schimbareLimba",optiuneLimba));
        rom.setText(persistentaElementFisier.vizualizare("rom",optiuneLimba));
        eng.setText(persistentaElementFisier.vizualizare("eng",optiuneLimba));
        fr.setText(persistentaElementFisier.vizualizare("fr",optiuneLimba));

        this.revalidate();
        this.repaint();
    }
}
