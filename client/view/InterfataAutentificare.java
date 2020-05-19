package view;

import prelucrare.PersistentaElementFisier;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

public class InterfataAutentificare extends JFrame implements Observer {

    private int optiuneLimbaStraina = 0; //initial este romana

    private JTextField numeUtilizatorText = new JTextField(15);
    private JPasswordField parolaText = new JPasswordField(15);

    private JButton nextButton;

    private BufferedImage img_fr = ImageIO.read(new File("franceza.jpg"));
    private BufferedImage img_uk = ImageIO.read(new File("engleza.jpg"));
    private BufferedImage img_ro = ImageIO.read(new File("romania.jpg"));

    private ImageIcon icon_fr = new ImageIcon(img_fr);
    private ImageIcon icon_uk = new ImageIcon(img_uk);
    private ImageIcon icon_ro = new ImageIcon(img_ro);

    private JLabel label_fr = new JLabel();
    private JLabel label_uk = new JLabel();
    private JLabel label_ro = new JLabel();

    private JLabel aux = new JLabel("___________________________________________");

    private JPanel imagine_fr = new JPanel();
    private JPanel imagine_ro = new JPanel();
    private JPanel imagine_uk = new JPanel();
    private JLabel numeUtilizator;
    private JLabel parola;

    public InterfataAutentificare(/*int op*/) throws IOException {
        //this.optiuneLimbaStraina = op;
        this.setTitle(new PersistentaElementFisier().vizualizare("autentificare",optiuneLimbaStraina));
        this.setSize(360,250);

        nextButton = new JButton(new PersistentaElementFisier().vizualizare("ok",optiuneLimbaStraina));
        numeUtilizator = new JLabel(new PersistentaElementFisier().vizualizare("numeUtilizator",optiuneLimbaStraina));
        parola = new JLabel(new PersistentaElementFisier().vizualizare("pass",optiuneLimbaStraina));

        //romana.setIcon(new ImageIcon("romana.png"));
        label_fr.setIcon(icon_fr);
        label_ro.setIcon(icon_ro);
        label_uk.setIcon(icon_uk);

        //JPanel imagine_fr = new JPanel();
        imagine_fr.add(label_fr);

        //JPanel imagine_ro = new JPanel();
        imagine_ro.add(label_ro);

        //JPanel imagine_uk = new JPanel();
        imagine_uk.add(label_uk);

        JPanel bara = new JPanel();
        bara.add(aux);

        JPanel imagini = new JPanel();
        imagini.add(imagine_ro);
        imagini.add(imagine_uk);
        imagini.add(imagine_fr);
        imagini.setLayout(new BoxLayout(imagini, BoxLayout.X_AXIS));

        JPanel panelButon = new JPanel();
        panelButon.add(nextButton);
        JPanel panelNumeText = new JPanel();
        panelNumeText.add(numeUtilizatorText);

        JPanel panelParolaText = new JPanel();
        panelParolaText.add(parolaText);

        JPanel panelNumeLabel = new JPanel();
        panelNumeLabel.add(numeUtilizator);

        JPanel panelParolaLabel = new JPanel();
        panelParolaLabel.add(parola);

        JPanel panelNumeLabelText = new JPanel();
        panelNumeLabelText.add(panelNumeLabel);
        panelNumeLabelText.add(panelNumeText);
        panelNumeLabelText.setLayout(new BoxLayout(panelNumeLabelText, BoxLayout.X_AXIS));

        JPanel panelParolaLabelText = new JPanel();
        panelParolaLabelText.add(panelParolaLabel);
        panelParolaLabelText.add(panelParolaText);
        panelParolaLabelText.setLayout(new BoxLayout(panelParolaLabelText, BoxLayout.X_AXIS));

        JPanel panelTotal = new JPanel();
        panelTotal.add(panelNumeLabelText);
        panelTotal.add(panelParolaLabelText);
        panelTotal.add(panelButon);
        panelTotal.add(bara);
        panelTotal.add(imagini);
        panelTotal.setLayout(new BoxLayout(panelTotal, BoxLayout.Y_AXIS));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        this.add(panelTotal);
        this.setVisible(true);

    }

    public void addNextButtonListener(ActionListener addNextButonListener) {
        nextButton.addActionListener(addNextButonListener);
    }

    public String getNumeUtilizator (){
        return numeUtilizatorText.getText();
    }
    public char[] getNumeParola (){
        return parolaText.getPassword();
    }
    public void setNumeUtilizator (String s){
         numeUtilizatorText.setText(s);
    }
    public void setParola (String c){
        parolaText.setText(c);
    }
    public void addFrListener(MouseListener a){
        imagine_fr.addMouseListener(a);
    }
    public void addRoListener(MouseListener a){
        imagine_ro.addMouseListener(a);
    }
    public void addUkListener(MouseListener a){
        imagine_uk.addMouseListener(a);
    }

    @Override
    public void update(Observable o, Object arg) {
        optiuneLimbaStraina = (int) arg;
        this.setTitle(new PersistentaElementFisier().vizualizare("autentificare",optiuneLimbaStraina));
        this.nextButton.setText(new PersistentaElementFisier().vizualizare("ok",optiuneLimbaStraina));
        this.numeUtilizator.setText(new PersistentaElementFisier().vizualizare("numeUtilizator",optiuneLimbaStraina));
        this.parola.setText(new PersistentaElementFisier().vizualizare("pass",optiuneLimbaStraina));

        //System.out.print((int)arg);
        this.revalidate();
        this.repaint();

    }
}
