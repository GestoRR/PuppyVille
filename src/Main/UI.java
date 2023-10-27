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
        generuj_obraz();
    }

    public void TworzenieOkna(){
        okno = new JFrame();
        okno.setSize(1024,768);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.getContentPane().setBackground(Color.black);
        okno.setLayout(null);
        okno.setResizable(false);
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
                JOptionPane.showMessageDialog(null, "Musisz wprowadzić imie!");
            }
        }
    }



    public void wstaw_tlo(int NumerTla, String NazwaZdjecia){
        //Panel z tlem na cala strone
        panel_tlo[NumerTla] = new JPanel();
        panel_tlo[NumerTla].setBounds(0,0,1024,768);
        panel_tlo[NumerTla].setBackground(Color.red);
        panel_tlo[NumerTla].setLayout(null);
        okno.add(panel_tlo[NumerTla]);

        label_tlo[NumerTla] = new JLabel();
        label_tlo[NumerTla].setBounds(0,0,1024,768);

        ImageIcon salon_zdj = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(NazwaZdjecia)));
        label_tlo[NumerTla].setIcon(salon_zdj);
        panel_tlo[NumerTla].add(label_tlo[ NumerTla]);
        label_tlo[NumerTla].repaint();

        //Wstawiam na stałe JLabel z imieniem na każdym tle jaki będzie
        panel_tlo[1] = new JPanel();
        panel_tlo[1].setBounds(1024/2-150,30,300,70);
        panel_tlo[1].setOpaque(false);
        label_tlo[NumerTla].add(panel_tlo[1]);
        label_tlo[NumerTla].repaint();

        label_tlo[1] = new JLabel();
        label_tlo[1].setBounds(0,0,300,70);
        Font czcionka_do_imienia = new Font("Arial",Font.BOLD,48);
        label_tlo[1].setFont(czcionka_do_imienia);
        label_tlo[1].setHorizontalAlignment(JLabel.CENTER);
        label_tlo[1].setText(imie);
        label_tlo[1].setBackground(null);
        panel_tlo[1].add(label_tlo[1]);

    }

    public void wstaw_objekt(int NumerTla, String NazwaZdjecia){

    }

    public void generuj_obraz(){
        wstaw_tlo(0,"salon.jpg");
        wstaw_objekt(0,"xd");
    }

}