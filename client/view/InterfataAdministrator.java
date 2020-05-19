package view;

import prelucrare.PersistentaElementFisier;

import javax.swing.*;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class InterfataAdministrator extends JFrame implements Observer {

    private JMenu menu,submenu;
    private JMenuItem rom,eng,fr;
    private int optiuneLimba = 0;
    private String[] columnNames;
    private PersistentaElementFisier persistentaElementFisier = new PersistentaElementFisier();


    private JTable angajati;
    private JButton adauga;
    private JButton inapoi;
    private JButton vizualizare;

    private JMenuItem delete;
    private JMenuItem edit;

    private TableModel tableModel;
    private Object[][] data;

    public InterfataAdministrator(/*int optiuneLimba*/){
        //this.optiuneLimba = optiuneLimba;
        this.setTitle(persistentaElementFisier.vizualizare("admin",optiuneLimba));
        this.setSize(600,400);
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

        columnNames = new String[]{persistentaElementFisier.vizualizare("id", optiuneLimba), persistentaElementFisier.vizualizare("nume", optiuneLimba), persistentaElementFisier.vizualizare("prenume", optiuneLimba), persistentaElementFisier.vizualizare("varsta", optiuneLimba), persistentaElementFisier.vizualizare("datangj", optiuneLimba)};
        data = new Object[][]{{}};
        tableModel = new DefaultTableModel(data, columnNames);
        angajati = new JTable(tableModel);
        adauga = new JButton(persistentaElementFisier.vizualizare("add",optiuneLimba));
        inapoi = new JButton(persistentaElementFisier.vizualizare("inapoi",optiuneLimba));
        vizualizare = new JButton(persistentaElementFisier.vizualizare("viz",optiuneLimba));
        delete = new JMenuItem(persistentaElementFisier.vizualizare("sterg",optiuneLimba));
        edit = new JMenuItem(persistentaElementFisier.vizualizare("edit",optiuneLimba));

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(delete);
        popupMenu.add(edit);

        angajati.setComponentPopupMenu(popupMenu);
        angajati.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(angajati);

        JPanel panelVizualizare = new JPanel();
        panelVizualizare.add(vizualizare);

        JPanel panelInapoi = new JPanel();
        panelInapoi.add(inapoi);

        JPanel panelAdaugare = new JPanel();
        panelAdaugare.add(adauga);

        JPanel panelButoane = new JPanel();
        panelButoane.add(panelInapoi);
        panelButoane.add(panelVizualizare);
        panelButoane.add(panelAdaugare);
        panelButoane.setLayout(new BoxLayout(panelButoane, BoxLayout.X_AXIS));

        JPanel panelTabel = new JPanel();
        panelTabel.add(sp);

        JPanel panelTotal = new JPanel();
        panelTotal.add(panelTabel);
        panelTotal.add(panelButoane);
        panelTotal.setLayout(new BoxLayout(panelTotal, BoxLayout.Y_AXIS));

        this.add(panelTotal);
    }

    public void setInapoiListener (ActionListener a){
        inapoi.addActionListener(a);
    }

    public void setAdaugaListener (ActionListener a){
        adauga.addActionListener(a);
    }

    public void addDelteListener (ActionListener a){
        delete.addActionListener(a);
    }

    public void addEditListener (ActionListener a){
        edit.addActionListener(a);
    }

    public void addVizListener (ActionListener a){
        vizualizare.addActionListener(a);
    }

    public JTable getTable (){
        return angajati;
    }

    public void tableFiller(String[][] rows,int n){
        DefaultTableModel modelTable = new DefaultTableModel();
        modelTable.setColumnCount(5);
        modelTable.setColumnIdentifiers(columnNames);
        int i = 0;
        while(i != n ){
            modelTable.addRow(rows[i]);
            i++;
        }
        angajati.setModel(modelTable);
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
        this.setTitle(persistentaElementFisier.vizualizare("admin",optiuneLimba));
        columnNames = new String[]{persistentaElementFisier.vizualizare("id", optiuneLimba), persistentaElementFisier.vizualizare("nume", optiuneLimba), persistentaElementFisier.vizualizare("prenume", optiuneLimba), persistentaElementFisier.vizualizare("varsta", optiuneLimba), persistentaElementFisier.vizualizare("datangj", optiuneLimba)};
        adauga.setText(persistentaElementFisier.vizualizare("add",optiuneLimba));
        inapoi.setText(persistentaElementFisier.vizualizare("inapoi",optiuneLimba));
        vizualizare.setText(persistentaElementFisier.vizualizare("viz",optiuneLimba));
        delete.setText(persistentaElementFisier.vizualizare("sterg",optiuneLimba));
        edit.setText(persistentaElementFisier.vizualizare("edit",optiuneLimba));
        DefaultTableModel modelTable = new DefaultTableModel();
        modelTable.setColumnIdentifiers(columnNames);
        angajati.setModel(modelTable);
        menu.setText(persistentaElementFisier.vizualizare("setari",optiuneLimba));
        submenu.setText(persistentaElementFisier.vizualizare("schimbareLimba",optiuneLimba));
        rom.setText(persistentaElementFisier.vizualizare("rom",optiuneLimba));
        eng.setText(persistentaElementFisier.vizualizare("eng",optiuneLimba));
        fr.setText(persistentaElementFisier.vizualizare("fr",optiuneLimba));
        System.out.println(arg);
        this.setTitle(persistentaElementFisier.vizualizare("admin",optiuneLimba));
        this.revalidate();
        this.repaint();
    }
}
