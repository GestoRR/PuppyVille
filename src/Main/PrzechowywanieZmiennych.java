package Main;

import javax.swing.*;
import java.awt.*;
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
}
