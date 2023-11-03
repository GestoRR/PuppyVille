package Main;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PrzechowywanieZmiennych {
    public int ilosc_kredytow;
    public JLabel label_ilosc_kredytow;
    public List<JLabel> label_ikony = new ArrayList<>();
    public String imie = "Kokosik";
    public JPanel[] panel_tlo = new JPanel[10];
    public JLabel[] label_tlo = new JLabel[10];

    public void dodajPunkt(int ilosc, char znak){
        if (znak == '+'){
            ilosc_kredytow = ilosc_kredytow + ilosc;
        } else if (znak == '-') {
            ilosc_kredytow = ilosc_kredytow - ilosc;
        }
        System.out.println(ilosc_kredytow);
        label_ilosc_kredytow.setText(String.valueOf(ilosc_kredytow));
    }
    public int wyswietlIloscKredytow(){
        return ilosc_kredytow;
    }
}
