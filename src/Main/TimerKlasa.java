package Main;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class TimerKlasa {
    private Timer timer;

    public TimerKlasa() {
        this.timer = new Timer();
    }

    public void startTimer() {
        long delay = 1 * 1000; //5 sekund (120 000 milisekund)

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                zadanieTimera(); //Metoda wykona się po 5 sekundach (odjecie punktow vitalnych)
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

    public static void main(String[] args) {
        TimerKlasa timerKlasa = new TimerKlasa();
        timerKlasa.startTimer();
    }
}
