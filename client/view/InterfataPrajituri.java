package view;

import prelucrare.PersistentaElementFisier;

import javax.swing.*;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;


public class InterfataPrajituri extends JFrame implements Observer {

    private int optiuneLimba;
    private PersistentaElementFisier persistentaElementFisier = new PersistentaElementFisier();

    private String[] criterii = {" ",persistentaElementFisier.vizualizare("vizprj",optiuneLimba),persistentaElementFisier.vizualizare("pret",optiuneLimba),persistentaElementFisier.vizualizare("nume",optiuneLimba),persistentaElementFisier.vizualizare("disp",optiuneLimba),persistentaElementFisier.vizualizare("valab",optiuneLimba)};
    private JLabel filtru;
    private JComboBox criteriiFiltrare;

    private String[] columnNames;
    private Object[][] data = {
            {},
    };
    private TableModel tableModel = new DefaultTableModel(data,columnNames);
    private JTable prajituri = new JTable(tableModel);

    private JTextField cautareText;
    private JButton adaugareButon;
    private JButton inapoi;

    private JPanel criteriu = new JPanel();
    private JTextField minim;
    private JTextField maxim;
    private JTextField dataExp;

    private JMenuItem deleteItem;
    private JMenuItem editItem;

    private JMenu menu,submenu;
    private JMenuItem rom,eng,fr;

    public InterfataPrajituri(){
        //this.setTitle(persistentaElementFisier.vizualizare());
        this.setSize(800,700);
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

        criteriiFiltrare = new JComboBox(criterii);
        filtru = new JLabel(persistentaElementFisier.vizualizare("filtrare",optiuneLimba));
        columnNames = new String[]{"Id", persistentaElementFisier.vizualizare("cofetarie",optiuneLimba), persistentaElementFisier.vizualizare("numeprj",optiuneLimba), persistentaElementFisier.vizualizare("pret",optiuneLimba), persistentaElementFisier.vizualizare("disp",optiuneLimba), persistentaElementFisier.vizualizare("valab",optiuneLimba)};
        cautareText = new JTextField("denumire",10);
        adaugareButon = new JButton("Adauga prajitura");
        inapoi = new JButton(persistentaElementFisier.vizualizare("inapoi",optiuneLimba));
        minim = new JTextField("Min",5);
        maxim = new JTextField("Max",5);
        dataExp = new JTextField("limita",10);
        deleteItem = new JMenuItem(persistentaElementFisier.vizualizare("sterg",optiuneLimba));
        editItem = new JMenuItem(persistentaElementFisier.vizualizare("edit",optiuneLimba));

        JPopupMenu popupMenu = new JPopupMenu();

        popupMenu.add(deleteItem);
        popupMenu.add(editItem);

        prajituri.setBounds(10,20,700,600);
        prajituri.setComponentPopupMenu(popupMenu);
        JScrollPane sp=new JScrollPane(prajituri);

        JPanel panelFiltru = new JPanel();
        panelFiltru.add(filtru);

        JPanel panelCriterii = new JPanel();
        panelCriterii.add(criteriiFiltrare);

        JPanel panelFiltrare = new JPanel();
        panelFiltrare.add(panelFiltru);
        panelFiltrare.add(panelCriterii);
        panelFiltrare.add(criteriu);
        panelFiltrare.setLayout(new BoxLayout(panelFiltrare, BoxLayout.X_AXIS));

        JPanel panelTabel = new JPanel();
        panelTabel.add(sp);

        JPanel panelCautareButon =  new JPanel();
        panelCautareButon.add(adaugareButon);

        JPanel panelButonInapoi = new JPanel();
        panelButonInapoi.add(inapoi);

        JPanel panelButoane = new JPanel();
        panelButoane.add(panelButonInapoi);
        panelButoane.add(panelCautareButon);
        panelButoane.setLayout(new BoxLayout(panelButoane, BoxLayout.X_AXIS));

        JPanel panelTotal = new JPanel();
        panelTotal.add(panelFiltrare);
        panelTotal.add(panelTabel);
        panelTotal.add(panelButoane);
        panelTotal.setLayout(new BoxLayout(panelTotal, BoxLayout.Y_AXIS));
        this.add(panelTotal);
    }

    public void addPanel(JPanel p){
        criteriu.add(p);
    }
    public void deletePanel(){
        criteriu.removeAll();
    }
    public String getCombo(){
        return criteriiFiltrare.getSelectedItem().toString();
    }
    public void setCombo() { this.criteriiFiltrare.setSelectedIndex(0);}
    public void addComboListener (ActionListener e){
        criteriiFiltrare.addActionListener(e);
    }
    public void setTextMinim(String s){
        minim.setText(s);
    }
    public void setTextMaxim(String s){
        maxim.setText(s);
    }
    public void setTextData(String s){
        dataExp.setText(s);
    }
    public void setCautareText(String s){
        cautareText.setText(s);
    }
    public JTextField getMinim(){
        return minim;
    }
    public JTextField getMaxim(){
        return maxim;
    }
    public JTextField getData(){
        return dataExp;
    }
    public JTextField getCautareText(){
        return cautareText;
    }
    public void setInapoiButon(ActionListener a){
        inapoi.addActionListener(a);
    }
    public void setAdaugareButon(ActionListener a){ adaugareButon.addActionListener(a); }
    public void addDeleteListener(ActionListener a){ deleteItem.addActionListener(a); }
    public JTable getTable(){ return prajituri; }
    public void tableFiller(String[][] rows,int n){
        DefaultTableModel modelTable = new DefaultTableModel();
        modelTable.setColumnCount(6);
        modelTable.setColumnIdentifiers(columnNames);
        int i = 0;
        while(i != n ){
            modelTable.addRow(rows[i]);
            i++;
        }
        prajituri.setModel(modelTable);
    }
    public void addEditListener(ActionListener a){
        editItem.addActionListener(a);
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
        model.addAll(Arrays.asList(" ", persistentaElementFisier.vizualizare("vizprj", optiuneLimba), persistentaElementFisier.vizualizare("pret", optiuneLimba), persistentaElementFisier.vizualizare("nume", optiuneLimba), persistentaElementFisier.vizualizare("disp", optiuneLimba), persistentaElementFisier.vizualizare("valab", optiuneLimba)));
        criteriiFiltrare.setModel(model);

        filtru.setText(persistentaElementFisier.vizualizare("filtrare",optiuneLimba));
        columnNames = new String[]{"Id", persistentaElementFisier.vizualizare("cofetarie",optiuneLimba), persistentaElementFisier.vizualizare("numeprj",optiuneLimba), persistentaElementFisier.vizualizare("pret",optiuneLimba), persistentaElementFisier.vizualizare("disp",optiuneLimba), persistentaElementFisier.vizualizare("valab",optiuneLimba)};
        DefaultTableModel defaultComboBoxModel = new DefaultTableModel();
        defaultComboBoxModel.setColumnIdentifiers(columnNames);
        prajituri.setModel(defaultComboBoxModel);

        cautareText.setText("denumire");
        adaugareButon.setText(persistentaElementFisier.vizualizare("adaugaprj",optiuneLimba));
        inapoi.setText(persistentaElementFisier.vizualizare("inapoi",optiuneLimba));
        minim.setText("Min");
        maxim.setText("Max");
        dataExp.setText("limita");
        deleteItem.setText(persistentaElementFisier.vizualizare("sterg",optiuneLimba));
        editItem.setText(persistentaElementFisier.vizualizare("edit",optiuneLimba));

        this.revalidate();
        this.repaint();

    }
}
