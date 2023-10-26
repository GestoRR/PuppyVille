package Main;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class UI {
    Manager man;
    JFrame okno;
    public JPanel panel_tlo[] = new JPanel[10];
    public JLabel label_tlo[] = new JLabel[10];

    public UI(Manager man){
        this.man = man;
        TworzenieOkna();
        okno.setVisible(true);
        salon_tlo();
    }

    public void TworzenieOkna(){
        okno = new JFrame();
        okno.setSize(1024,768);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.getContentPane().setBackground(Color.black);
        okno.setLayout(null);
        okno.setTitle("PuppyVille: Psia Ferajna");
        okno.setLocationRelativeTo(null);
    }
    public void salon_tlo(){
        //Panel z tlem na cala strone. 0 = salon
        panel_tlo[0] = new JPanel();
        panel_tlo[0].setBounds(0,0,1024,768);
        panel_tlo[0].setBackground(Color.red);
        panel_tlo[0].setLayout(null);
        okno.add(panel_tlo[0]);

        label_tlo[0] = new JLabel();
        label_tlo[0].setBounds(0,0,1024,768);

        ImageIcon salon_zdj = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("salon.jpg")));
        label_tlo[0].setIcon(salon_zdj);
        panel_tlo[0].add(label_tlo[0]);



    }
}