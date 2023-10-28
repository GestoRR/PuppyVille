package Main;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.util.Objects;

public class UI {
    Manager man;
    JFrame okno;
    public JPanel panel_tlo[] = new JPanel[10];
    public JLabel label_tlo[] = new JLabel[10];
    public String imie="Kokosik";
    public String polozenie = "salon";

    public UI(Manager man){
        this.man = man;
        TworzenieOkna();
        okno.setVisible(true);
        //wpisz_imie();
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

        ImageIcon tlo_zdj = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(NazwaZdjecia)));
        label_tlo[NumerTla].setIcon(tlo_zdj);
        panel_tlo[NumerTla].add(label_tlo[ NumerTla]);
        label_tlo[NumerTla].repaint();

        //Wywołuje metode, która wstawia obiekty, które są zawsze w tym samym miejscu i są takie same
        stale_obiekty(NumerTla);

    }

    public void wstaw_objekt(int NumerTla, String NazwaZdjecia, int x, int y, int szer, int wys){
        JLabel label_objekt = new JLabel();
        label_objekt.setBounds(x,y,szer,wys);

        ImageIcon objekt_zdj = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(NazwaZdjecia)));
        label_objekt.setIcon(objekt_zdj);

        label_tlo[NumerTla].add(label_objekt);
        label_tlo[NumerTla].repaint();

    }

    public void stale_obiekty(int NumerTla){
        //Wstawiam na stałe JLabel z imieniem na każdym tle jaki wystąpi
        JPanel panel_imie = new JPanel();
        panel_imie.setBounds(1024/2-150,30,300,70);
        panel_imie.setOpaque(false);
        label_tlo[NumerTla].add(panel_imie);
        label_tlo[NumerTla].repaint();

        JLabel label_imie = new JLabel();
        label_imie.setBounds(0,0,300,70);
        Font czcionka_do_imienia = new Font("Arial",Font.BOLD,48);
        label_imie.setFont(czcionka_do_imienia);
        label_imie.setHorizontalAlignment(JLabel.CENTER);
        label_imie.setText(imie);
        label_imie.setForeground(Color.white);
        //label_imie.setBorder(new CompoundBorder( // sets two borders
        //        BorderFactory.createMatteBorder(10, 10, 10, 10, Color.darkGray), // outer border
        //        BorderFactory.createEmptyBorder(10, 10, 10, 10))); // inner invisible border as the margin
        label_imie.setBackground(Color.lightGray);
        label_imie.setOpaque(true);
        panel_imie.add(label_imie);
        //Ikona zdrowie
        JLabel label_zdrowie = new JLabel();
        label_zdrowie.setBounds(10,10,70,70);
        ImageIcon objekt_zdj_zdrowie = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("zdrowie_ikona.png")));
        label_zdrowie.setIcon(objekt_zdj_zdrowie);
        label_tlo[NumerTla].add(label_zdrowie);
        label_tlo[NumerTla].repaint();
        //Ikona glod
        JLabel label_objekt_glod = new JLabel();
        label_objekt_glod.setBounds(10,70,70,70);
        ImageIcon objekt_zdj_glod = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("glod_ikona.png")));
        label_objekt_glod.setIcon(objekt_zdj_glod);
        label_tlo[NumerTla].add(label_objekt_glod);
        label_tlo[NumerTla].repaint();
        //Ikona zmeczenie
        JLabel label_objekt_zmeczenie = new JLabel();
        label_objekt_zmeczenie.setBounds(10,70+60,70,70);
        ImageIcon objekt_zdj_zmeczenie = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("zmeczenie_ikona.png")));
        label_objekt_zmeczenie.setIcon(objekt_zdj_zmeczenie);
        label_tlo[NumerTla].add(label_objekt_zmeczenie);
        label_tlo[NumerTla].repaint();
        //Ikona szczescie
        JLabel label_objekt_szczescie = new JLabel();
        label_objekt_szczescie.setBounds(10,70+120,70,70);
        ImageIcon objekt_zdj_szczescie = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("szczescie_ikona.png")));
        label_objekt_szczescie.setIcon(objekt_zdj_szczescie);
        label_tlo[NumerTla].add(label_objekt_szczescie);
        label_tlo[NumerTla].repaint();
    }

    public void salon(){
        wstaw_tlo(0,"salon.jpg");
        wstaw_objekt(0,"bohater.png", 340, 320, 200,190);
        wstaw_objekt(0,"dwor_ikona.png",806,648,70,70);
        wstaw_objekt(0,"kuchnia_ikona.png",149,648,70,70);
        wstaw_objekt(0,"lazienka_ikona.png",587,648,70,70);
        wstaw_objekt(0,"sypialnia_ikona.png",368,648,70,70);
    }

    public void kuchnia(){
        wstaw_tlo(1,"kuchnia.png");
        wstaw_objekt(1,"bohater.png", 300, 450, 200,190);
        wstaw_objekt(1,"dwor_ikona.png",806,648,70,70);
        wstaw_objekt(1,"salon_ikona.png",149,648,70,70);
        wstaw_objekt(1,"lazienka_ikona.png",587,648,70,70);
        wstaw_objekt(1,"sypialnia_ikona.png",368,648,70,70);
    }
    public void generuj_obraz(){
        //0 = salon, 1 = kuchnia
        salon();


    }

}