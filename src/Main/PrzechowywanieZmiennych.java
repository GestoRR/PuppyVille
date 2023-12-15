package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PrzechowywanieZmiennych {
    public int ilosc_kredytow;
    public JLabel label_ilosc_kredytow;
    public List<JLabel> label_ikony = new ArrayList<>();
    public String imie = "Kokosik";
    public JPanel[] panel_tlo = new JPanel[10];
    public JLabel[] label_tlo = new JLabel[10];
    public JLabel[] label_punkty_vitalne = new JLabel[4];
    public int[] ilosc_punkty_vitalne = new int[4];
    public JButton[] przycisk_interakcje = new JButton[5];

    public PrzechowywanieZmiennych(){
        inicjalizujPunktyVitalne();
    }

    public void dodajPunkt(int ilosc, char znak){
        if (znak == '+'){
            ilosc_kredytow = ilosc_kredytow + ilosc;
        } else if (znak == '-') {
            ilosc_kredytow = ilosc_kredytow - ilosc;
        }
        label_ilosc_kredytow.setText(String.valueOf(ilosc_kredytow));
    }

    public void inicjalizujPunktyVitalne() {
        for (int i = 0; i < ilosc_punkty_vitalne.length; i++) {
            ilosc_punkty_vitalne[i] = 100;
        }
    }

    public void obslugaInterakcj(int numerTla){
        JFrame interakcja = new JFrame();
        interakcja.setResizable(false);
        String string1 = null;
        // Tworzenie tekstu na środku
        switch (numerTla) {
            case 1 -> {
                string1 = "głodu";
            }
            case 2 -> {
                string1 = "zabawy";
            }
            case 3 -> {
                string1 = "zdrowia";
            }
            case 4 -> {
                string1 = "wyspania";
            }

        }
        int int1 = 30;
        int int2 = 3;
        String string2 = "kredyty";
        String tekst = "Czy chcesz kupić " + int1 + "%" +" " + string1 + " za " + int2 + " " + string2;
        JLabel label = new JLabel(tekst);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        // Dodawanie tekstu do panelu
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);

        // Tworzenie przycisków "Ok" i "Anuluj"
        JButton okButton = new JButton("Ok");
        JButton anulujButton = new JButton("Anuluj");

        // Dodawanie obsługi zdarzeń dla przycisków
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (numerTla){
                    case 1 -> {
                        if (ilosc_kredytow < 1) {
                            if (ilosc_punkty_vitalne[0]<70) {
                                ilosc_punkty_vitalne[0] += 30;
                            }
                            else
                            {
                                ilosc_punkty_vitalne[0] = 100;
                            }
                            label_punkty_vitalne[0].setText(String.valueOf(ilosc_punkty_vitalne[0]+"%"));}
                        else
                        {
                            //label.setText("Niewystarczająca ilość środków do tego zakupu!");
                        }
                    }
                    case 2 -> {}
                    case 3 -> {}
                    case 4 -> {}
                }
                interakcja.dispose();
            }
        });

        anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interakcja.dispose(); // zamknij okno po naciśnięciu "Anuluj"
            }
        });

        // Dodawanie przycisków do panelu
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(anulujButton);

        // Dodawanie panelu z tekstem i panelu z przyciskami do głównego panelu
        interakcja.add(panel, BorderLayout.CENTER);
        interakcja.add(buttonPanel, BorderLayout.SOUTH);

        // Ustawienia okna
        interakcja.setSize(400, 200);
        interakcja.setLocationRelativeTo(null);
        interakcja.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        interakcja.setVisible(true);





    }
}
