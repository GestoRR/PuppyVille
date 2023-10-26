package Main;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class UI {
    Manager man;
    JFrame okno;
    public JPanel panel_tlo[] = new JPanel[10];
    public JLabel label_tlo[] = new JLabel[10];
    public String imie;

    public UI(Manager man){
        this.man = man;
        TworzenieOkna();
        okno.setVisible(true);
        wpisz_imie();
        salon_tlo();
        pole_imie();
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
    public String wpisz_imie() {
        JPanel panel = new JPanel();
        JLabel komunikat = new JLabel("Wpisz imię dla pupila: ");
        JTextField textField = new JTextField(20);

        panel.add(komunikat);
        panel.add(textField);

        while (true) {
            int result = JOptionPane.showConfirmDialog(null, panel, "Wprowadź imię", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                imie = textField.getText();
                if (!imie.isEmpty()) {
                    return imie;
                } else {
                    JOptionPane.showMessageDialog(null, "Imię nie może być puste. Wprowadź imię.");
                }
            } else {
                //return null;
                JOptionPane.showMessageDialog(null, "Musisz wprowadzić imie!");
            }
        }
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
<<<<<<< Updated upstream

        ImageIcon salon_zdj = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("salon.jpg")));
        label_tlo[0].setIcon(salon_zdj);
        panel_tlo[0].add(label_tlo[0]);



=======
        ImageIcon salon_zdj = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("salon.jpg")));
        label_tlo[0].setIcon(salon_zdj);
        panel_tlo[0].add(label_tlo[0]);
        //label_tlo[0].revalidate();
        label_tlo[0].repaint();

    }

    public void pole_imie()
    {
        panel_tlo[1] = new JPanel();
        panel_tlo[1].setBounds(1024/2-80,30,200,70);
        panel_tlo[1].setBackground(Color.green);
        panel_tlo[1].setLayout(null);

        label_tlo[0].add(panel_tlo[1]);
        label_tlo[0].repaint();
>>>>>>> Stashed changes
    }
}