package view;

import prelucrare.PersistentaElementFisier;

import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

public class InterfataAngajat extends JFrame implements Observer {

    private JComboBox vizPrj;
    private JButton vizualizarePrajituri = new JButton("OK");
    private JButton inapoi;
    private JButton vizualizareStatistici = new JButton("OK");
    private JButton salvare = new JButton("OK");
    private JComboBox cofetarieAleasaRapoarte ;
    private JComboBox cofetarieAleasaStatistici ;
    private JTextField format = new JTextField(5);
    private JLabel vizualizare;
    private JLabel statistici;
    private JLabel salvarePrajitura;
    private JLabel formatLabel;
    private JMenu menu,submenu;
    private JMenuItem rom,eng,fr;
    private PersistentaElementFisier persistentaElementFisier = new PersistentaElementFisier();
    private int optiuneLimba;

    public InterfataAngajat(){
        this.setTitle(persistentaElementFisier.vizualizare("setari",optiuneLimba));
        this.setSize(600,400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        vizPrj = new JComboBox(new String[]{"Zahar Ars","Deliciu","Viata Dulce",persistentaElementFisier.vizualizare("toate",optiuneLimba)});
        cofetarieAleasaRapoarte = new JComboBox(new String[]{"Zahar Ars","Deliciu","Viata Dulce",persistentaElementFisier.vizualizare("toate",optiuneLimba)});
        cofetarieAleasaStatistici = new JComboBox(new String[]{"Zahar Ars","Deliciu","Viata Dulce",persistentaElementFisier.vizualizare("toate",optiuneLimba)});
        inapoi = new JButton(persistentaElementFisier.vizualizare("inapoi",optiuneLimba));
        vizualizare = new JLabel(persistentaElementFisier.vizualizare("vizCofetarie",optiuneLimba)+": ");
        statistici = new JLabel(persistentaElementFisier.vizualizare("statistici",optiuneLimba)+": ");
        salvarePrajitura = new JLabel(persistentaElementFisier.vizualizare("save",optiuneLimba) +": ");
        formatLabel = new JLabel(persistentaElementFisier.vizualizare("format",optiuneLimba)+": ");

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

        JPanel vizText = new JPanel();
        vizText.add(vizPrj);

        JPanel vizLabel = new JPanel();

        vizLabel.add(vizualizare);

        JPanel vizButon = new JPanel();
        vizButon.add(vizualizarePrajituri);

        JPanel panelVizualizare = new JPanel();
        panelVizualizare.add(vizLabel);
        panelVizualizare.add(vizText);
        panelVizualizare.add(vizButon);
        panelVizualizare.setLayout(new BoxLayout(panelVizualizare, BoxLayout.X_AXIS));


        JPanel labelStatistici = new JPanel();
        labelStatistici.add(statistici);

        JPanel panelStatisticiButon = new JPanel();
        panelStatisticiButon.add(vizualizareStatistici);

        JPanel panelStatisticiCombo = new JPanel();
        panelStatisticiCombo.add(cofetarieAleasaStatistici);

        JPanel panelStatistici = new JPanel();
        panelStatistici.add(labelStatistici);
        panelStatistici.add(panelStatisticiCombo);
        panelStatistici.add(panelStatisticiButon);
        panelStatistici.setLayout(new BoxLayout(panelStatistici, BoxLayout.X_AXIS));


        JPanel panelInapoi = new JPanel();
        panelInapoi.add(inapoi);

        JPanel panelSalvare = new JPanel();

        panelSalvare.add(salvarePrajitura);

        JPanel panelCofetarieRapoarte = new JPanel();
        panelCofetarieRapoarte.add(cofetarieAleasaRapoarte);

        JPanel panelSalvareLabel = new JPanel();

        panelSalvareLabel.add(formatLabel);

        JPanel panelFormat = new JPanel();
        panelFormat.add(format);

        JPanel panelSalvareButon = new JPanel();
        panelSalvareButon.add(salvare);

        JPanel panelSalvareFormat = new JPanel();
        panelSalvareFormat.add(panelSalvare);
        panelSalvareFormat.add(panelCofetarieRapoarte);
        panelSalvareFormat.add(panelSalvareLabel);
        panelSalvareFormat.add(panelFormat);
        panelSalvareFormat.add(panelSalvareButon);
        panelSalvareFormat.setLayout(new BoxLayout(panelSalvareFormat, BoxLayout.X_AXIS));

        JPanel panelTotal = new JPanel();
        panelTotal.add(panelVizualizare);
        panelTotal.add(panelStatistici);
        panelTotal.add(panelSalvareFormat);
        panelTotal.add(panelInapoi);
        panelTotal.setLayout(new BoxLayout(panelTotal, BoxLayout.Y_AXIS));

        this.add(panelTotal);
    }

    public void addInapoiListener(ActionListener addInapoiListener) {
        inapoi.addActionListener(addInapoiListener);
    }

    public void addVizualizareListener(ActionListener addVizualizareListener) {
        vizualizarePrajituri.addActionListener(addVizualizareListener);
    }

    public void addSaveListener (ActionListener a) { salvare.addActionListener(a); }

    public String getFormatText() { return format.getText(); }

    public void addListenetStatistici(ActionListener a){
        vizualizareStatistici.addActionListener(a);
    }
    public String getVizualizare(){
        return vizPrj.getSelectedItem().toString();
    }
    public String getVizualizareRaport(){
        return cofetarieAleasaRapoarte.getSelectedItem().toString();
    }
    public String getVizualizareStatistica(){
        return cofetarieAleasaStatistici.getSelectedItem().toString();
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
        menu.setText(persistentaElementFisier.vizualizare("setari",optiuneLimba));
        submenu.setText(persistentaElementFisier.vizualizare("schimbareLimba",optiuneLimba));
        rom.setText(persistentaElementFisier.vizualizare("rom",optiuneLimba));
        eng.setText(persistentaElementFisier.vizualizare("eng",optiuneLimba));
        fr.setText(persistentaElementFisier.vizualizare("fr",optiuneLimba));
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        DefaultComboBoxModel model2 = new DefaultComboBoxModel();
        DefaultComboBoxModel model1 = new DefaultComboBoxModel();
        model.addAll(Arrays.asList(new String[]{"Zahar Ars", "Deliciu", "Viata Dulce", persistentaElementFisier.vizualizare("toate", optiuneLimba)}));
        model1.addAll(Arrays.asList(new String[]{"Zahar Ars", "Deliciu", "Viata Dulce", persistentaElementFisier.vizualizare("toate", optiuneLimba)}));
        model2.addAll(Arrays.asList(new String[]{"Zahar Ars", "Deliciu", "Viata Dulce", persistentaElementFisier.vizualizare("toate", optiuneLimba)}));

        vizPrj.setModel(model);
        cofetarieAleasaRapoarte.setModel(model1);
        cofetarieAleasaStatistici.setModel(model2);
        inapoi.setText(persistentaElementFisier.vizualizare("inapoi",optiuneLimba));
        vizualizare.setText(persistentaElementFisier.vizualizare("vizCofetarie",optiuneLimba)+": ");
        statistici.setText(persistentaElementFisier.vizualizare("statistici",optiuneLimba)+": ");
        salvarePrajitura.setText(persistentaElementFisier.vizualizare("save",optiuneLimba) +": ");
        formatLabel.setText(persistentaElementFisier.vizualizare("format",optiuneLimba)+": ");

        this.revalidate();
        this.repaint();
    }
}
