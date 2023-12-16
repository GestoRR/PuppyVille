package Main;
import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class TimerKlasa {
    private Timer timer;
    public TimerKlasa() {
        this.timer = new Timer();
    }

    public void startTimer() {
        long delay = 200; //5 sekund (120 000 milisekund)

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                zadanieTimera(); //Metoda wykona się po 5 sekundach (odjecie punktow vitalnych)
                sprawdzanieRozgrywki(); //Sprawdzam czy punkty vitalne nie spadna ponizej 0, a jesli spadna to robie obsługe
            }
        };

        //Uruchomienie timera z cyklicznym odliczaniem
        timer.scheduleAtFixedRate(task, delay , delay);
    }

    private static void zadanieTimera() {
        for (int i=0; i<4;i++) {
            UI.przechowywanie.ilosc_punkty_vitalne[i] = UI.przechowywanie.ilosc_punkty_vitalne[i] - 1;
            UI.przechowywanie.label_punkty_vitalne[i].setText(String.valueOf(UI.przechowywanie.ilosc_punkty_vitalne[i]+"%"));
            if (UI.przechowywanie.ilosc_punkty_vitalne[i]>=70) {UI.przechowywanie.label_punkty_vitalne[i].setForeground(Color.green);}
            else if (UI.przechowywanie.ilosc_punkty_vitalne[i]<70&UI.przechowywanie.ilosc_punkty_vitalne[i]>=30) {UI.przechowywanie.label_punkty_vitalne[i].setForeground(Color.yellow);}
            else if (UI.przechowywanie.ilosc_punkty_vitalne[i]<30) {UI.przechowywanie.label_punkty_vitalne[i].setForeground(Color.getHSBColor(0,0.9f,0.7f));}
        }
    }
    private void sprawdzanieRozgrywki(){
        for (int i=0; i<4;i++){
            if (UI.przechowywanie.ilosc_punkty_vitalne[i]==65) {
                timer.cancel(); // Zatrzymaj timer, gdy wartość punktu vitalnego spadnie do 0
                wyswietlKomunikatKoncowy(i + 1); // Wyświetl komunikat zależny od numeru punktu vitalnego
                break;
            }
        }
    }

    private void wyswietlKomunikatKoncowy(int numerPunktu) {
        String komunikat = "";
        switch (numerPunktu) {
            case 1:
                komunikat = "Koniec gry. Punkt vitalny 1 spadł do 0.";
                break;
            case 2:
                komunikat = "Koniec gry. Punkt vitalny 2 spadł do 0.";
                break;
            case 3:
                komunikat = "Koniec gry. Punkt vitalny 3 spadł do 0.";
                break;
            case 4:
                komunikat = "Koniec gry. Punkt vitalny 4 spadł do 0.";
                break;
        }

// Wyświetl okno informacyjne
        JOptionPane.showMessageDialog(null, komunikat, "Koniec gry", JOptionPane.INFORMATION_MESSAGE);

        // Dodaj obsługę przycisków "Zagraj ponownie" i "Opuść grę"
        Object[] options = {"Zagraj ponownie", "Opuść grę"};
        int choice = JOptionPane.showOptionDialog(null, "Co chcesz zrobić?", "Wybór", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.YES_OPTION) {
            for (int i=0;i<4;i++) {
                UI.przechowywanie.ilosc_punkty_vitalne[i] = 0;
                UI.przechowywanie.ilosc_kredytow = 0;
                UI.przechowywanie.inicjalizujPunktyVitalne();
                UI.przechowywanie.label_punkty_vitalne[i].setText(String.valueOf(UI.przechowywanie.ilosc_punkty_vitalne[i]+"%"));
                UI.przechowywanie.label_punkty_vitalne[i].setForeground(Color.green);
                UI.przechowywanie.label_ilosc_kredytow.setText(String.valueOf(UI.przechowywanie.ilosc_kredytow));
            }
            timer = new Timer();
            startTimer();

        } else if (choice == JOptionPane.NO_OPTION) {
            System.exit(0); // Zamknij wszystkie okna i zakończ program
        }
    }

    public static void main(String[] args) {
        TimerKlasa timerKlasa = new TimerKlasa();
        timerKlasa.startTimer();
    }}