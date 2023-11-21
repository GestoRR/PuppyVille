package Main;
import java.util.Timer;
import java.util.TimerTask;


public class TimerKlasa {
    private Timer timer;

    public TimerKlasa() {
        this.timer = new Timer();
    }

    public void startTimer() {
        long delay = 2 * 60 * 1000; //2 minuty (120 000 milisekund)

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                zadanieTimera(); //Metoda wykona się po 2 minutach (odjecie punktow vitalnych)
            }
        };

        //Uruchomienie timera z cyklicznym odliczaniem
        timer.scheduleAtFixedRate(task, delay , delay);
    }

    private static void zadanieTimera() {
        for (int i=0; i<4;i++) {
            UI.przechowywanie.ilosc_punkty_vitalne[i] = UI.przechowywanie.ilosc_punkty_vitalne[i] - 1;
            UI.przechowywanie.label_punkty_vitalne[i].setText(String.valueOf(UI.przechowywanie.ilosc_punkty_vitalne[i]+"%"));
        }
    }

    public static void main(String[] args) {
        TimerKlasa timerKlasa = new TimerKlasa();
        timerKlasa.startTimer();
    }
}
