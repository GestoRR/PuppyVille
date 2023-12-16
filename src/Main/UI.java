package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class UI {
    Manager man;
    JFrame okno;
    public static PrzechowywanieZmiennych przechowywanie = new PrzechowywanieZmiennych();
    public Menu menu = new Menu();
    public static TimerKlasa timer = new TimerKlasa();


    public String obecne_polozenie = "salon";
    public String poprzednie_polozenie = "salon";

    public UI(Manager man){
        this.man = man;
        TworzenieOkna();
        okno.setVisible(true);
        //wpisz_imie();
        generuj_obraz();
        timer.startTimer();
        menu.Menu();
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
                przechowywanie.imie = textField.getText();
                if (!przechowywanie.imie.isEmpty()) {
                    return przechowywanie.imie;
                } else {
                    JOptionPane.showMessageDialog(null, "Imię nie może być puste. Wprowadź imię.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Musisz wprowadzić imie!");
            }
        }
    }


    public void wstaw_przycisk(int NumerTla, int x, int y, int szer, int wys){
        if (NumerTla == 0 ){
            if (poprzednie_polozenie.equals("kuchnia")){System.out.println(poprzednie_polozenie);przechowywanie.panel_tlo[1].remove(przechowywanie.przycisk_interakcje[1]);}
            if (poprzednie_polozenie.equals("dwor")){System.out.println(poprzednie_polozenie);przechowywanie.panel_tlo[2].remove(przechowywanie.przycisk_interakcje[2]);}
            if (poprzednie_polozenie.equals("lazienka")){System.out.println(poprzednie_polozenie);przechowywanie.panel_tlo[3].remove(przechowywanie.przycisk_interakcje[3]);}
            if (poprzednie_polozenie.equals("sypialnia")){System.out.println(poprzednie_polozenie);przechowywanie.panel_tlo[4].remove(przechowywanie.przycisk_interakcje[4]);}
            return;
        }
        przechowywanie.przycisk_interakcje[NumerTla] = new JButton();
        JButton przyc =  przechowywanie.przycisk_interakcje[NumerTla];
        JPanel tlo = przechowywanie.panel_tlo[NumerTla];
        if (poprzednie_polozenie.equals("kuchnia")){System.out.println(poprzednie_polozenie);przechowywanie.panel_tlo[1].remove(przechowywanie.przycisk_interakcje[1]);}
        if (poprzednie_polozenie.equals("dwor")){System.out.println(poprzednie_polozenie);przechowywanie.panel_tlo[2].remove(przechowywanie.przycisk_interakcje[2]);}
        if (poprzednie_polozenie.equals("lazienka")){System.out.println(poprzednie_polozenie);przechowywanie.panel_tlo[3].remove(przechowywanie.przycisk_interakcje[3]);}
        if (poprzednie_polozenie.equals("sypialnia")){System.out.println(poprzednie_polozenie);przechowywanie.panel_tlo[4].remove(przechowywanie.przycisk_interakcje[4]);}
        przyc.setBounds(x,y,szer,wys);
        tlo.add(przyc);
        przyc.setOpaque(false); //Przycisk jest przezroczysty
        przyc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (NumerTla){
                    case 1 -> {przechowywanie.obslugaInterakcji(1);}
                    case 2 -> {przechowywanie.obslugaInterakcji(2);}
                    case 3 -> {przechowywanie.obslugaInterakcji(3);}
                    case 4 -> {przechowywanie.obslugaInterakcji(4);}
                }
            }
        });

    }
    public void wstaw_tlo(int NumerTla, String NazwaZdjecia){
        //Panel z tlem na cala strone
        przechowywanie.panel_tlo[NumerTla] = new JPanel();
        przechowywanie.panel_tlo[NumerTla].setBounds(0,0,1024,768);
        przechowywanie.panel_tlo[NumerTla].setBackground(Color.red);
        przechowywanie.panel_tlo[NumerTla].setLayout(null);
        okno.add(przechowywanie.panel_tlo[NumerTla]);

        przechowywanie.label_tlo[NumerTla] = new JLabel();
        przechowywanie.label_tlo[NumerTla].setBounds(0,0,1024,768);

        ImageIcon tlo_zdj = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(NazwaZdjecia)));
        przechowywanie.label_tlo[NumerTla].setIcon(tlo_zdj);
        przechowywanie.panel_tlo[NumerTla].add(przechowywanie.label_tlo[ NumerTla]);
        przechowywanie.label_tlo[NumerTla].repaint();

        //Wywołuje metode, która wstawia obiekty, które są zawsze w tym samym miejscu i są takie same
        stale_obiekty(NumerTla);

    }

    public void wstaw_objekt(int NumerTla, String NazwaZdjecia, int x, int y, int szer, int wys, Boolean ikona){
        JLabel label_objekt = new JLabel();
        label_objekt.setBounds(x,y,szer,wys);

        ImageIcon objekt_zdj = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(NazwaZdjecia)));
        label_objekt.setIcon(objekt_zdj);

        przechowywanie.label_tlo[NumerTla].add(label_objekt);
        przechowywanie.label_tlo[NumerTla].repaint();


        if (ikona == Boolean.TRUE){
            przechowywanie.label_ikony.add(label_objekt);
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
        panel_imie.setLayout(null);
        panel_imie.setOpaque(true);
        panel_imie.setBackground(Color.red);
        przechowywanie.label_tlo[NumerTla].add(panel_imie);
        przechowywanie.label_tlo[NumerTla].repaint();

        JLabel label_imie = new JLabel();
        label_imie.setBounds(0,0,300,70);
        Font czcionka_do_imienia = new Font("Arial",Font.BOLD,48);
        label_imie.setFont(czcionka_do_imienia);
        label_imie.setHorizontalAlignment(JLabel.CENTER);
        label_imie.setText(przechowywanie.imie);
        label_imie.setForeground(Color.white);
        label_imie.setBackground(Color.gray);
        label_imie.setOpaque(true);
        panel_imie.add(label_imie);

        String[] ikony = {"zdrowie_ikona.png", "glod_ikona.png", "zmeczenie_ikona.png", "szczescie_ikona.png"};
        for (int i = 0; i < ikony.length; i++) {
            JLabel label_objekt = new JLabel();
            label_objekt.setBounds(10, 10 + i * 60, 70, 70);
            ImageIcon objekt_zdjecie = new ImageIcon(Objects.requireNonNull(UI.class.getClassLoader().getResource(ikony[i])));
            label_objekt.setIcon(objekt_zdjecie);
            przechowywanie.label_punkty_vitalne[i] = new JLabel();
            przechowywanie.label_punkty_vitalne[i].setBounds(60,20 + i*60,70,50);
            przechowywanie.label_punkty_vitalne[i].setFont(new Font("Arial", Font.BOLD,26));
            przechowywanie.label_punkty_vitalne[i].setHorizontalAlignment(SwingConstants.CENTER);
            przechowywanie.label_punkty_vitalne[i].setText(String.valueOf(przechowywanie.ilosc_punkty_vitalne[i]+"%"));
            przechowywanie.label_punkty_vitalne[i].setBackground(Color.getHSBColor(272f/360f, 60f/100f, 82f/100f));
            if (przechowywanie.ilosc_punkty_vitalne[i]>=70) {przechowywanie.label_punkty_vitalne[i].setForeground(Color.green);}
            else if (przechowywanie.ilosc_punkty_vitalne[i]<70&przechowywanie.ilosc_punkty_vitalne[i]>=30) {przechowywanie.label_punkty_vitalne[i].setForeground(Color.yellow);}
            else if (przechowywanie.ilosc_punkty_vitalne[i]<30) {przechowywanie.label_punkty_vitalne[i].setForeground(Color.getHSBColor(0,0.9f,0.7f));}
            przechowywanie.label_punkty_vitalne[i].setOpaque(true);
            przechowywanie.label_tlo[NumerTla].add(przechowywanie.label_punkty_vitalne[i]);
            przechowywanie.label_tlo[NumerTla].add(label_objekt);
            przechowywanie.label_tlo[NumerTla].repaint();
        }

        //JLabel z iloscia kredytow
        przechowywanie.label_ilosc_kredytow = new JLabel();
        przechowywanie.label_ilosc_kredytow.setBounds(750,30,100,70);
        przechowywanie.label_ilosc_kredytow.setFont(czcionka_do_imienia);
        przechowywanie.label_ilosc_kredytow.setHorizontalAlignment(JLabel.CENTER);
        przechowywanie.label_ilosc_kredytow.setText(String.valueOf(przechowywanie.ilosc_kredytow));
        przechowywanie.label_ilosc_kredytow.setForeground(Color.white);
        przechowywanie.label_ilosc_kredytow.setBackground(Color.gray);
        przechowywanie.label_ilosc_kredytow.setOpaque(true);
        przechowywanie.label_tlo[NumerTla].add(przechowywanie.label_ilosc_kredytow);
        przechowywanie.label_tlo[NumerTla].repaint();
        //JLabel z dodawaniem kredytow
        JLabel label_dodawanie_kredytow = new JLabel("+");
        Font czcionka_dodawanie = new Font("Arial",Font.BOLD,75);
        label_dodawanie_kredytow.setFont(czcionka_dodawanie);
        label_dodawanie_kredytow.setHorizontalAlignment(JLabel.CENTER);
        label_dodawanie_kredytow.setBounds(890,30,70,70);
        label_dodawanie_kredytow.setBackground(Color.gray);
        label_dodawanie_kredytow.setForeground(Color.white);
        label_dodawanie_kredytow.setOpaque(true);
        przechowywanie.label_tlo[NumerTla].add(label_dodawanie_kredytow);
        przechowywanie.label_tlo[NumerTla].repaint();


        dodawanie_kredytow dodawanieKredytow = new dodawanie_kredytow(label_dodawanie_kredytow);
    }

    public void przechodzenie(int NumerTla, String NazwaZdjecia, JLabel label_objekt){

        for (int i = 0 ; i<przechowywanie.label_ikony.size();i++)
        {
            przechowywanie.label_tlo[NumerTla].remove(przechowywanie.label_ikony.get(i));
        }


        if (NumerTla == 0)//Wiem, że to jest salon
        {
            switch (NazwaZdjecia) {
                case "kuchnia_ikona.png" -> {
                    poprzednie_polozenie = "salon";
                    obecne_polozenie = "kuchnia";
                    kuchnia();
                }
                case "sypialnia_ikona.png" -> {
                    poprzednie_polozenie = "salon";
                    obecne_polozenie = "sypialnia";
                    sypialnia();
                }
                case "dwor_ikona.png" -> {
                    poprzednie_polozenie = "salon";
                    obecne_polozenie = "dwor";
                    dwor();
                }
                case "lazienka_ikona.png" -> {
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
                    poprzednie_polozenie = "kuchnia";
                    obecne_polozenie = "salon";
                    salon();
                }
                case "sypialnia_ikona.png" -> {
                    poprzednie_polozenie = "kuchnia";
                    obecne_polozenie = "sypialnia";
                    sypialnia();
                }
                case "dwor_ikona.png" -> {
                    poprzednie_polozenie = "kuchnia";
                    obecne_polozenie = "dwor";
                    dwor();
                }
                case "lazienka_ikona.png" -> {
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
                    poprzednie_polozenie = "dwor";
                    obecne_polozenie = "salon";
                    salon();
                }
                case "sypialnia_ikona.png" -> {
                    poprzednie_polozenie = "dwor";
                    obecne_polozenie = "sypialnia";
                    sypialnia();
                }
                case "kuchnia_ikona.png" -> {
                    poprzednie_polozenie = "dwor";
                    obecne_polozenie = "kuchnia";
                    kuchnia();
                }
                case "lazienka_ikona.png" -> {
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
                    poprzednie_polozenie = "lazienka";
                    obecne_polozenie = "salon";
                    salon();
                }
                case "sypialnia_ikona.png" -> {
                    poprzednie_polozenie = "lazienka";
                    obecne_polozenie = "sypialnia";
                    sypialnia();
                }
                case "kuchnia_ikona.png" -> {
                    poprzednie_polozenie = "lazienka";
                    obecne_polozenie = "kuchnia";
                    kuchnia();
                }
                case "dwor_ikona.png" -> {
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
                    poprzednie_polozenie = "sypialnia";
                    obecne_polozenie = "salon";
                    salon();
                }
                case "lazienka_ikona.png" -> {
                    poprzednie_polozenie = "sypialnia";
                    obecne_polozenie = "lazienka";
                    lazienka();
                }
                case "kuchnia_ikona.png" -> {
                    poprzednie_polozenie = "sypialnia";
                    obecne_polozenie = "kuchnia";
                    kuchnia();
                }
                case "dwor_ikona.png" -> {
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
        wstaw_przycisk(0,1,1,1,1);
    }

    public void kuchnia(){
        wstaw_tlo(1,"kuchnia.png");
        wstaw_objekt(1,"bohater.png", 300, 450, 200,190, Boolean.FALSE);
        wstaw_przycisk(1,3,150,195,359);
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
        wstaw_objekt(2,"pilka.png", 30, 600, 100,100, Boolean.FALSE);
        wstaw_przycisk(2,30,600,80,100);
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
        wstaw_przycisk(3,662,485,325,150);

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
        wstaw_przycisk(4,350,420,265,200);
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