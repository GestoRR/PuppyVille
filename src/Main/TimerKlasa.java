package Main;
import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Służy do obsługi timera w celu odejmowania po jednym % każdego punktu vitalnego
 * co sekundę.
 */
public class TimerKlasa {
    /**
     * Nasz timer
     */
    private Timer timer;

    /**
     * Inicjalizuje timer
     */
    public TimerKlasa() {
        this.timer = new Timer();
    }

    /**
     * Rozpoczyna pracę timera z podanymi przez nas parametrami. Ponadto sprawdza nam czy punkty vitalne
     * nie osiągnęły wartości zera procent.
     */
    public void startTimer() {
        long delay = 1000; //1 sekunda (1 000 milisekund)

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                zadanieTimera(); //Metoda wykona się po 1 sekundzie (odjecie punktow vitalnych)
                sprawdzanieRozgrywki(); //Sprawdzam czy punkty vitalne nie spadna ponizej 0, a jesli spadna to robie obsługe
            }
        };

        //Uruchomienie timera z cyklicznym odliczaniem
        timer.scheduleAtFixedRate(task, delay , delay);
    }

    /**
     * Służy do ustawiania kolorów czcionki dla punktów vitalnych gdy spadną poniżej pewnych poziomów.
     */
    private static void zadanieTimera() {
        for (int i=0; i<4;i++) {
            UI.przechowywanie.ilosc_punkty_vitalne[i] = UI.przechowywanie.ilosc_punkty_vitalne[i] - 1;
            UI.przechowywanie.label_punkty_vitalne[i].setText(String.valueOf(UI.przechowywanie.ilosc_punkty_vitalne[i]+"%"));
            if (UI.przechowywanie.ilosc_punkty_vitalne[i]>=70) {UI.przechowywanie.label_punkty_vitalne[i].setForeground(Color.green);}
            else if (UI.przechowywanie.ilosc_punkty_vitalne[i]<70&UI.przechowywanie.ilosc_punkty_vitalne[i]>=30) {UI.przechowywanie.label_punkty_vitalne[i].setForeground(Color.yellow);}
            else if (UI.przechowywanie.ilosc_punkty_vitalne[i]<30) {UI.przechowywanie.label_punkty_vitalne[i].setForeground(Color.getHSBColor(0,0.9f,0.7f));}
        }
    }

    /**
     * Sprawdza czy jakikolwiek punkt vitalny jest równy zero. Jeśli jest to wyświetla komunikat końcowy.
     */
    private void sprawdzanieRozgrywki(){
        for (int i=0; i<4;i++){
            if (UI.przechowywanie.ilosc_punkty_vitalne[i]==0) {
                timer.cancel();
                wyswietlKomunikatKoncowy(i + 1);
                break;
            }
        }
    }

    /**
     * Służy do wyświetlania pouczającego komunikatu końcowego, gdy dojdzie do końca rozgrywki. Ponadto
     * występuje tutaj obsługa ponownej gry lub opuszczenia całkowicie rozgrywki.
     * @param numerPunktu Który punkt vitalny jest równy 0. Jest to istotne w celu dobrania odpowiedniego komunikatu końcowego.
     */
    private void wyswietlKomunikatKoncowy(int numerPunktu) {
        String komunikat = "";
        switch (numerPunktu) {
            case 1:
                komunikat = "Czystość to klucz do zdrowego pieska! Zapewnij mu regularne kąpiele i dbaj o higienę.";
                break;
            case 2:
                komunikat = "Pamietaj o regularnym karmieniu swojego pieska! Zaniedbanie posiłków może prowadzić do utraty punktów. Zadbaj o zdrową dietę, a twój pupilek będzie pełen energii!";
                break;
            case 3:
                komunikat = "Zadbaj o zdrowy sen swojego pieska! Brak odpowiedniej ilości snu może wpływać na jego samopoczucie. Upewnij się, że ma wygodne miejsce do spania.";
                break;
            case 4:
                komunikat = "Pamiętaj, że twój piesek potrzebuje twojej uwagi! Zabawa i pieszczoty są kluczowe dla jego dobrego samopoczucia. Następnym razem poświęć mu trochę czasu i zbuduj silną więź!";
                break;
        }

        //Wyświetl okno informacyjne
        JOptionPane.showMessageDialog(null, komunikat, "Koniec gry", JOptionPane.INFORMATION_MESSAGE);

        //Obsługa przycisków "Zagraj ponownie" i "Opuść grę"
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
            System.exit(0); //Zamknij wszystkie okna i zakończ program
        }
    }

    public static void main(String[] args) {
        TimerKlasa timerKlasa = new TimerKlasa();
        timerKlasa.startTimer();
    }}