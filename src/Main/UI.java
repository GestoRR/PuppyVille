package Main;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UI {
    Manager man;
    JFrame okno;
    public JPanel panel_tlo[] = new JPanel[10];
    public JLabel label_tlo[] = new JLabel[10];
    private List<JLabel> label_ikony = new ArrayList<>();

    public int ilosc_kredytow = 0;
    public String imie="Kokosik";
    public String obecne_polozenie = "salon";
    public String poprzednie_polozenie = "salon";

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

    public void wstaw_objekt(int NumerTla, String NazwaZdjecia, int x, int y, int szer, int wys, Boolean ikona){
        JLabel label_objekt = new JLabel();
        label_objekt.setBounds(x,y,szer,wys);

        ImageIcon objekt_zdj = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(NazwaZdjecia)));
        label_objekt.setIcon(objekt_zdj);

        label_tlo[NumerTla].add(label_objekt);
        label_tlo[NumerTla].repaint();


        if (ikona == Boolean.TRUE){
            label_ikony.add(label_objekt);
            label_objekt.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    przechodzenie(NumerTla,NazwaZdjecia,label_objekt);
                }
            });
        }

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
        label_imie.setBackground(Color.gray);
        label_imie.setOpaque(true);
        panel_imie.add(label_imie);

        String[] ikony = {"zdrowie_ikona.png", "glod_ikona.png", "zmeczenie_ikona.png", "szczescie_ikona.png"};
        for (int i = 0; i < ikony.length; i++) {
            JLabel label_objekt = new JLabel();
            label_objekt.setBounds(10, 10 + i * 60, 70, 70);
            ImageIcon objekt_zdjecie = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(ikony[i])));
            label_objekt.setIcon(objekt_zdjecie);
            label_tlo[NumerTla].add(label_objekt);
            label_tlo[NumerTla].repaint();
        }

        //JLabel z iloscia kredytow
        JLabel label_ilosc_kredytow = new JLabel();
        label_ilosc_kredytow.setBounds(750,30,100,70);
        label_ilosc_kredytow.setFont(czcionka_do_imienia);
        label_ilosc_kredytow.setHorizontalAlignment(JLabel.CENTER);
        label_ilosc_kredytow.setText(String.valueOf(ilosc_kredytow));
        label_ilosc_kredytow.setForeground(Color.white);
        label_ilosc_kredytow.setBackground(Color.gray);
        label_ilosc_kredytow.setOpaque(true);
        label_tlo[NumerTla].add(label_ilosc_kredytow);
        label_tlo[NumerTla].repaint();
        //JLabel z dodawaniem kredytow
        JLabel label_dodawanie_kredytow = new JLabel("+");
        Font czcionka_dodawanie = new Font("Arial",Font.BOLD,75);
        label_dodawanie_kredytow.setFont(czcionka_dodawanie);
        label_dodawanie_kredytow.setHorizontalAlignment(JLabel.CENTER);
        label_dodawanie_kredytow.setBounds(890,30,70,70);
        label_dodawanie_kredytow.setBackground(Color.gray);
        label_dodawanie_kredytow.setForeground(Color.white);
        label_dodawanie_kredytow.setOpaque(true);
        label_tlo[NumerTla].add(label_dodawanie_kredytow);
        label_tlo[NumerTla].repaint();

        dodawanie_kredytow dodawanieKredytow = new dodawanie_kredytow(label_dodawanie_kredytow);
    }

    public void przechodzenie(int NumerTla, String NazwaZdjecia, JLabel label_objekt){

        for (int i = 0 ; i<label_ikony.size();i++)
        {
            label_tlo[NumerTla].remove(label_ikony.get(i));
        }


        if (NumerTla == 0)//Wiem, że to jest salon
        {
            switch (NazwaZdjecia) {
                case "kuchnia_ikona.png" -> {
                    System.out.println("Salon, ikona:Kuchnia");
                    poprzednie_polozenie = "salon";
                    obecne_polozenie = "kuchnia";
                    kuchnia();
                }
                case "sypialnia_ikona.png" -> {
                    System.out.println("Salon, ikona:sypialnia");
                    poprzednie_polozenie = "salon";
                    obecne_polozenie = "sypialnia";
                    sypialnia();
                }
                case "dwor_ikona.png" -> {
                    System.out.println("Salon, ikona:dwor");
                    poprzednie_polozenie = "salon";
                    obecne_polozenie = "dwor";
                    dwor();
                }
                case "lazienka_ikona.png" -> {
                    System.out.println("Salon, ikona:lazienka");
                    poprzednie_polozenie = "salon";
                    obecne_polozenie = "lazienka";
                    lazienka();
                }
            }
        }
        else if (NumerTla == 1) //Wiem, że to kuchnia
        {
            switch (NazwaZdjecia) {
                case "salon_ikona.png" -> {
                    System.out.println("Kuchnia, ikona:salon");
                    poprzednie_polozenie = "kuchnia";
                    obecne_polozenie = "salon";
                    salon();
                }
                case "sypialnia_ikona.png" -> {
                    System.out.println("Kuchnia, ikona:sypialnia");
                    poprzednie_polozenie = "kuchnia";
                    obecne_polozenie = "sypialnia";
                    sypialnia();
                }
                case "dwor_ikona.png" -> {
                    System.out.println("Kuchnia, ikona:dwor");
                    poprzednie_polozenie = "kuchnia";
                    obecne_polozenie = "dwor";
                    dwor();
                }
                case "lazienka_ikona.png" -> {
                    System.out.println("Kuchnia, ikona:lazienka");
                    poprzednie_polozenie = "kuchnia";
                    obecne_polozenie = "lazienka";
                    lazienka();
                }
            }
        }
        else if (NumerTla == 2) //Wiem, że to dwor
        {
            switch (NazwaZdjecia) {
                case "salon_ikona.png" -> {
                    System.out.println("Dwor, ikona:salon");
                    poprzednie_polozenie = "dwor";
                    obecne_polozenie = "salon";
                    salon();
                }
                case "sypialnia_ikona.png" -> {
                    System.out.println("Dwor, ikona:sypialnia");
                    poprzednie_polozenie = "dwor";
                    obecne_polozenie = "sypialnia";
                    sypialnia();
                }
                case "kuchnia_ikona.png" -> {
                    System.out.println("Dwor, ikona:kuchnia");
                    poprzednie_polozenie = "dwor";
                    obecne_polozenie = "kuchnia";
                    kuchnia();
                }
                case "lazienka_ikona.png" -> {
                    System.out.println("Dwor, ikona:lazienka");
                    poprzednie_polozenie = "dwor";
                    obecne_polozenie = "lazienka";
                    lazienka();
                }
            }
        }
        else if (NumerTla == 3) //Wiem, że to lazienka
        {
            switch (NazwaZdjecia) {
                case "salon_ikona.png" -> {
                    System.out.println("Lazienka, ikona:salon");
                    poprzednie_polozenie = "lazienka";
                    obecne_polozenie = "salon";
                    salon();
                }
                case "sypialnia_ikona.png" -> {
                    System.out.println("Lazienka, ikona:sypialnia");
                    poprzednie_polozenie = "lazienka";
                    obecne_polozenie = "sypialnia";
                    sypialnia();
                }
                case "kuchnia_ikona.png" -> {
                    System.out.println("Lazienka, ikona:kuchnia");
                    poprzednie_polozenie = "lazienka";
                    obecne_polozenie = "kuchnia";
                    kuchnia();
                }
                case "dwor_ikona.png" -> {
                    System.out.println("Lazienka, ikona:dwor");
                    poprzednie_polozenie = "lazienka";
                    obecne_polozenie = "dwor";
                    dwor();
                }
            }
        }
        else if (NumerTla == 4) //Wiem, że to sypialnia
        {
            switch (NazwaZdjecia) {
                case "salon_ikona.png" -> {
                    System.out.println("Sypialnia, ikona:salon");
                    poprzednie_polozenie = "sypialnia";
                    obecne_polozenie = "salon";
                    salon();
                }
                case "lazienka_ikona.png" -> {
                    System.out.println("Sypialnia, ikona:lazienka");
                    poprzednie_polozenie = "sypialnia";
                    obecne_polozenie = "lazienka";
                    lazienka();
                }
                case "kuchnia_ikona.png" -> {
                    System.out.println("Sypialnia, ikona:kuchnia");
                    poprzednie_polozenie = "sypialnia";
                    obecne_polozenie = "kuchnia";
                    kuchnia();
                }
                case "dwor_ikona.png" -> {
                    System.out.println("sypialnia, ikona:dwor");
                    poprzednie_polozenie = "sypialnia";
                    obecne_polozenie = "dwor";
                    dwor();
                }
            }
        }
    }

    public void salon(){
        wstaw_tlo(0,"salon.jpg");
        wstaw_objekt(0,"bohater.png", 340, 320, 200,190, Boolean.FALSE);
        wstaw_objekt(0,"dwor_ikona.png",806,648,70,70, Boolean.TRUE);
        wstaw_objekt(0,"kuchnia_ikona.png",149,648,70,70, Boolean.TRUE);
        wstaw_objekt(0,"lazienka_ikona.png",587,648,70,70, Boolean.TRUE);
        wstaw_objekt(0,"sypialnia_ikona.png",368,648,70,70, Boolean.TRUE);
    }

    public void kuchnia(){
        wstaw_tlo(1,"kuchnia.png");
        wstaw_objekt(1,"bohater.png", 300, 450, 200,190, Boolean.FALSE);

        switch (poprzednie_polozenie){
            case "salon", "dwor", "sypialnia", "lazienka" -> {
                wstaw_objekt(1,"dwor_ikona.png",806,648,70,70, Boolean.TRUE);
                wstaw_objekt(1,"salon_ikona.png",149,648,70,70, Boolean.TRUE);
                wstaw_objekt(1,"lazienka_ikona.png",587,648,70,70, Boolean.TRUE);
                wstaw_objekt(1,"sypialnia_ikona.png",368,648,70,70, Boolean.TRUE);
            }
        }

    }

    public void dwor(){
        wstaw_tlo(2,"dwor.png");
        wstaw_objekt(2,"bohater.png", 400, 530, 200,190, Boolean.FALSE);

        switch (poprzednie_polozenie){
            case "salon", "kuchnia", "sypialnia", "lazienka" -> {
                wstaw_objekt(2,"salon_ikona.png",806,648,70,70, Boolean.TRUE);
                wstaw_objekt(2,"kuchnia_ikona.png",149,648,70,70, Boolean.TRUE);
                wstaw_objekt(2,"lazienka_ikona.png",587,648,70,70, Boolean.TRUE);
                wstaw_objekt(2,"sypialnia_ikona.png",368,648,70,70, Boolean.TRUE);
            }
        }
    }

    public void lazienka(){
        wstaw_tlo(3,"lazienka.png");
        wstaw_objekt(3,"bohater.png", 500, 470, 200,190, Boolean.FALSE);

        switch (poprzednie_polozenie){
            case "salon", "kuchnia", "sypialnia", "dwor" -> {
                wstaw_objekt(3,"dwor_ikona.png",806,648,70,70, Boolean.TRUE);
                wstaw_objekt(3,"kuchnia_ikona.png",149,648,70,70, Boolean.TRUE);
                wstaw_objekt(3,"salon_ikona.png",587,648,70,70, Boolean.TRUE);
                wstaw_objekt(3,"sypialnia_ikona.png",368,648,70,70, Boolean.TRUE);
            }
        }
    }

    public void sypialnia(){
        wstaw_tlo(4,"sypialnia.png");
        wstaw_objekt(4,"bohater.png", 830, 480, 200,190, Boolean.FALSE);

        switch (poprzednie_polozenie){
            case "salon", "kuchnia", "dwor", "lazienka" -> {
                wstaw_objekt(4,"dwor_ikona.png",806,648,70,70, Boolean.TRUE);
                wstaw_objekt(4,"kuchnia_ikona.png",149,648,70,70, Boolean.TRUE);
                wstaw_objekt(4,"lazienka_ikona.png",587,648,70,70, Boolean.TRUE);
                wstaw_objekt(4,"salon_ikona.png",368,648,70,70, Boolean.TRUE);
            }
        }
    }
    public void generuj_obraz(){
        //0 = salon, 1 = kuchnia, 2 = dwor, 3 = lazienka, 4 = sypialnia
        salon();


    }

}