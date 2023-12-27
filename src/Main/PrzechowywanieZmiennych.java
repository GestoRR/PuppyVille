package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Służy jako magazyn dla danych i to właśnie tutaj zachodzą manipulację danych takie jak dodawanie punktów
 * vitalnych, ich inicjalizacja oraz dodawanie/odejmowanie kredytów po odpowiedzi.
 */
public class PrzechowywanieZmiennych {
    /**
     * Przechowuje aktualną wartość kredytów użytkownika.
     */
    public int ilosc_kredytow;
    /**
     * Label na którym wyświetlana jest aktualna ilość kredytów. Używamy go dlatego, aby
     * można było aktualizować ilość kredytów bez zmiany sceny tylko po użyciu metody settext().
     */
    public JLabel label_ilosc_kredytow;
    /**
     * Lista labelów na ikony, które służą do przechodzenia między scenami. Stworzone w celu łatwiejszego
     * odwoływania się do danego labela.
     */
    public List<JLabel> label_ikony = new ArrayList<>();
    /**
     * Wartość imienia wpisanego przez użytkownika.
     */
    public String imie;
    /**
     * Tablica paneli dla tła scen. Numeracja zgodna z unikatowym NumerTla każdej sceny.
     */
    public JPanel[] panel_tlo = new JPanel[10];
    /**
     * Tablica labeli dla tła scen. Numeracja zgodna z unikatowym NumerTla każdej sceny.
     */
    public JLabel[] label_tlo = new JLabel[10];
    /**
     * Tablica labeli dla wyświetlania ilości punktów vitalnych.
     */
    public JLabel[] label_punkty_vitalne = new JLabel[4];
    /**
     * Tablica intów służąca do zapisywania aktualnej wartości wszystkich punktów vitalnych.
     */
    public int[] ilosc_punkty_vitalne = new int[4];
    /**
     * Tablica JButtonów, którą wykorzystujemy w celu tworzenia przycisków do interakcji.
     */
    public JButton[] przycisk_interakcje = new JButton[5];

    /**
     * Ma za zadanie wywołać metodę inicjalizujPunktyVitalne() w celu przypisania wartości początkowej
     * dla wszystkich punktów vitalnych.
     */
    public PrzechowywanieZmiennych(){
        inicjalizujPunktyVitalne();
    }

    /**
     * Służy do obsługi dodawania/odejmowania punktów po dobrej/złej odpowiedzi.
     * Następnie setuje w odpowiednim labelu nową wartość kredytów, aby ją odświeżyć bez zmiany sceny.
     * @param ilosc Określa ile punktów dodać/odjąć.
     * @param znak Określa czy punkty będziemy dodawać lub odejmować.
     */
    public void dodajPunkt(int ilosc, char znak){
        if (znak == '+'){
            ilosc_kredytow = ilosc_kredytow + ilosc;
        } else if (znak == '-') {
            ilosc_kredytow = ilosc_kredytow - ilosc;
        }
        label_ilosc_kredytow.setText(String.valueOf(ilosc_kredytow));
    }

    /**
     * Iteruje po tablicy ilosc_punkty_vitalne i każdemu punktowi przypisuje początkową wartość
     * 100(%).
     */
    public void inicjalizujPunktyVitalne() {
        for (int i = 0; i < ilosc_punkty_vitalne.length; i++) {
            ilosc_punkty_vitalne[i] = 100;
        }
    }

    /**
     * Służy do dodania funkcjonalności stworzonych wcześniej JButtonów, tj. stworzenie okna dialogowego
     * z zapytaniem czy chcemy kupić daną ilość punktu vitalnego za następującą ilość kredytów.
     * Następnie sprawdza czy mamy taką ilość kredytów i obsługuje nasze zapytanie.
     * @param numerTla Unikatowy numer dla każdego tła, do którego dodamy przycisk
     */
    public void obslugaInterakcji(int numerTla){
        JFrame interakcja = new JFrame();
        interakcja.setResizable(false);
        String string1 = null;
        //Tworzenie tekstu na środku
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

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);

        JButton okButton = new JButton("Ok");
        JButton anulujButton = new JButton("Anuluj");

        //Dodawanie obsługi zdarzeń dla przycisków
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (numerTla) {
                    case 1:
                        if (ilosc_kredytow < 3) {
                            JOptionPane.showMessageDialog(null, "Niewystarczająca ilość kredytów!", "Błąd", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (ilosc_punkty_vitalne[1] < 70) {
                                ilosc_punkty_vitalne[1] += 30;
                            } else {
                                ilosc_punkty_vitalne[1] = 100;
                            }
                            ilosc_kredytow-=3;
                            label_ilosc_kredytow.setText(String.valueOf(ilosc_kredytow));
                            if (UI.przechowywanie.ilosc_punkty_vitalne[1]>=70) {UI.przechowywanie.label_punkty_vitalne[1].setForeground(Color.green);}
                            else if (UI.przechowywanie.ilosc_punkty_vitalne[1]<70&UI.przechowywanie.ilosc_punkty_vitalne[1]>=30) {UI.przechowywanie.label_punkty_vitalne[1].setForeground(Color.yellow);}
                            else if (UI.przechowywanie.ilosc_punkty_vitalne[1]<30) {UI.przechowywanie.label_punkty_vitalne[1].setForeground(Color.getHSBColor(0,0.9f,0.7f));}
                            label_punkty_vitalne[1].setText(String.valueOf(ilosc_punkty_vitalne[1] + "%"));
                        }
                        break;

                    case 2:
                        if (ilosc_kredytow < 3) {
                            JOptionPane.showMessageDialog(null, "Niewystarczająca ilość kredytów!", "Błąd", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (ilosc_punkty_vitalne[3] < 70) {
                                ilosc_punkty_vitalne[3] += 30;
                            } else {
                                ilosc_punkty_vitalne[3] = 100;
                            }
                            ilosc_kredytow-=3;
                            label_ilosc_kredytow.setText(String.valueOf(ilosc_kredytow));
                            if (UI.przechowywanie.ilosc_punkty_vitalne[3]>=70) {UI.przechowywanie.label_punkty_vitalne[3].setForeground(Color.green);}
                            else if (UI.przechowywanie.ilosc_punkty_vitalne[3]<70&UI.przechowywanie.ilosc_punkty_vitalne[3]>=30) {UI.przechowywanie.label_punkty_vitalne[3].setForeground(Color.yellow);}
                            else if (UI.przechowywanie.ilosc_punkty_vitalne[3]<30) {UI.przechowywanie.label_punkty_vitalne[3].setForeground(Color.getHSBColor(0,0.9f,0.7f));}
                            label_punkty_vitalne[3].setText(String.valueOf(ilosc_punkty_vitalne[3] + "%"));
                        }
                        break;
                    case 3:
                        if (ilosc_kredytow < 3) {
                            JOptionPane.showMessageDialog(null, "Niewystarczająca ilość kredytów!", "Błąd", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (ilosc_punkty_vitalne[0] < 70) {
                                ilosc_punkty_vitalne[0] += 30;
                            } else {
                                ilosc_punkty_vitalne[0] = 100;
                            }
                            ilosc_kredytow-=3;
                            label_ilosc_kredytow.setText(String.valueOf(ilosc_kredytow));
                            if (UI.przechowywanie.ilosc_punkty_vitalne[0]>=70) {UI.przechowywanie.label_punkty_vitalne[0].setForeground(Color.green);}
                            else if (UI.przechowywanie.ilosc_punkty_vitalne[0]<70&UI.przechowywanie.ilosc_punkty_vitalne[0]>=30) {UI.przechowywanie.label_punkty_vitalne[0].setForeground(Color.yellow);}
                            else if (UI.przechowywanie.ilosc_punkty_vitalne[0]<30) {UI.przechowywanie.label_punkty_vitalne[0].setForeground(Color.getHSBColor(0,0.9f,0.7f));}
                            label_punkty_vitalne[0].setText(String.valueOf(ilosc_punkty_vitalne[0] + "%"));
                        }
                        break;
                    case 4:
                        if (ilosc_kredytow < 3) {
                            JOptionPane.showMessageDialog(null, "Niewystarczająca ilość kredytów!", "Błąd", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (ilosc_punkty_vitalne[2] < 70) {
                                ilosc_punkty_vitalne[2] += 30;
                            } else {
                                ilosc_punkty_vitalne[2] = 100;
                            }
                            ilosc_kredytow-=3;
                            label_ilosc_kredytow.setText(String.valueOf(ilosc_kredytow));
                            if (UI.przechowywanie.ilosc_punkty_vitalne[2]>=70) {UI.przechowywanie.label_punkty_vitalne[2].setForeground(Color.green);}
                            else if (UI.przechowywanie.ilosc_punkty_vitalne[2]<70&UI.przechowywanie.ilosc_punkty_vitalne[2]>=30) {UI.przechowywanie.label_punkty_vitalne[2].setForeground(Color.yellow);}
                            else if (UI.przechowywanie.ilosc_punkty_vitalne[2]<30) {UI.przechowywanie.label_punkty_vitalne[2].setForeground(Color.getHSBColor(0,0.9f,0.7f));}
                            label_punkty_vitalne[2].setText(String.valueOf(ilosc_punkty_vitalne[2] + "%"));
                        }
                        break;
                }
                interakcja.dispose();
            }
        });


        anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interakcja.dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(anulujButton);

        interakcja.add(panel, BorderLayout.CENTER);
        interakcja.add(buttonPanel, BorderLayout.SOUTH);

        interakcja.setSize(400, 200);
        interakcja.setLocationRelativeTo(null);
        interakcja.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        interakcja.setVisible(true);





    }
}