package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class dodawanie_kredytow {
    private JLabel label_dodawanie_kredytow;

    public dodawanie_kredytow(JLabel label_dodawanie_kredytow) {
        this.label_dodawanie_kredytow = label_dodawanie_kredytow;
        label_dodawanie_kredytow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    wyswietlPopupMenu(e.getX(), e.getY());
                }
            }
        });
    }

    private void wyswietlPopupMenu(int x, int y) {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenu kategorieMenu = new JMenu("Kategorie");

        JMenuItem matematykaMenuItem = new JMenuItem("Matematyka");
        matematykaMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PoziomTrudnosciMenu("Matematyka");
            }
        });

        JMenuItem tlumaczenieMenuItem = new JMenuItem("Tłumaczenie");
        tlumaczenieMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PoziomTrudnosciMenu("Tłumaczenie");
            }
        });

        JMenuItem zagadkiMenuItem = new JMenuItem("Zagadki");
        zagadkiMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PoziomTrudnosciMenu("Zagadki");
            }
        });

        kategorieMenu.add(matematykaMenuItem);
        kategorieMenu.add(tlumaczenieMenuItem);
        kategorieMenu.add(zagadkiMenuItem);

        popupMenu.add(kategorieMenu);
        popupMenu.show(label_dodawanie_kredytow, x, y);
    }

    private void PoziomTrudnosciMenu(String kategoria) {
        JPopupMenu PoziomTrudnosciMenu = new JPopupMenu();

        JMenuItem latwyMenuItem = new JMenuItem("Łatwy");
        latwyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame zadanieFrame = new JFrame(kategoria + " - Łatwy");

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                JLabel zadanieLabel;
                String poprawnaOdpowiedz;

                if (kategoria.equals("Matematyka")) {
                    int a = ThreadLocalRandom.current().nextInt(3, 15 + 1);
                    int b = ThreadLocalRandom.current().nextInt(3, 9 + 1);
                    zadanieLabel = new JLabel(a+"*"+b+"=");
                    poprawnaOdpowiedz = String.valueOf(a*b);
                } else if (kategoria.equals("Tłumaczenie")) {
                    String[] odpowiedzi = TlumaczenieLatwe();
                    zadanieLabel = new JLabel("Translate '"+odpowiedzi[0]+"' to Polish:");
                    poprawnaOdpowiedz = odpowiedzi[1];
                } else {
                    String[] odpowiedzi = ZagadkiLatwe();
                    zadanieLabel = new JLabel(odpowiedzi[0]);
                    poprawnaOdpowiedz = odpowiedzi[1];
                }

                zadanieLabel.setHorizontalAlignment(SwingConstants.CENTER);
                zadanieLabel.setFont(new Font("Arial", Font.PLAIN, 26));
                panel.add(zadanieLabel);

                JTextField pole_na_odpowiedz = new JTextField();
                pole_na_odpowiedz.setFont(new Font("Arial", Font.PLAIN, 26));
                pole_na_odpowiedz.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(pole_na_odpowiedz);

                JButton odpowiedzPrzycisk = new JButton("Ostateczna odpowiedź");
                odpowiedzPrzycisk.setHorizontalAlignment(SwingConstants.CENTER);
                odpowiedzPrzycisk.setFont(new Font("Arial", Font.PLAIN, 26));
                JPanel panelPrzycisk = new JPanel();
                panel.add(panelPrzycisk);
                panelPrzycisk.add(odpowiedzPrzycisk);


                odpowiedzPrzycisk.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String odpowiedzUzytkownika = pole_na_odpowiedz.getText();
                        if (odpowiedzUzytkownika.equalsIgnoreCase(poprawnaOdpowiedz)) {
                            // Poprawna odpowiedz
                            JOptionPane.showMessageDialog(null, "Super, poprawna odpowiedz! Dostajesz 1 kredyt :)!");
                            UI.przechowywanie.dodajPunkt(1,'+');
                        } else {
                            // Niepoprawna odpowiedz
                            JOptionPane.showMessageDialog(null, "Odpowiedź niepoprawna. Tracisz 1 kredyt :(");
                            UI.przechowywanie.dodajPunkt(1,'-');
                        }
                        zadanieFrame.dispose();
                    }
                });

                zadanieFrame.add(panel);
                zadanieFrame.pack();
                zadanieFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                zadanieFrame.setLocationRelativeTo(null);
                zadanieFrame.setVisible(true);
            }
        });

        JMenuItem trudnyMenuItem = new JMenuItem("Trudny");
        trudnyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame zadanieFrame = new JFrame(kategoria + " - trudny");

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                JLabel zadanieLabel;
                String poprawnaOdpowiedz;

                if (kategoria.equals("Matematyka")) {
                    int[] odpowiedzi = MatmaTrudne();
                    char[] znak1 = Character.toChars(odpowiedzi[3]);
                    char[] znak2 = Character.toChars(odpowiedzi[4]);
                    zadanieLabel = new JLabel(odpowiedzi[0]+String.valueOf(znak1)+odpowiedzi[1]+String.valueOf(znak2)+odpowiedzi[2]);
                    poprawnaOdpowiedz = String.valueOf(odpowiedzi[5]);
                } else if (kategoria.equals("Tłumaczenie")) {
                    String[] odpowiedzi = TlumaczenieTrudne();
                    zadanieLabel = new JLabel("Przetlumacz '"+odpowiedzi[0]+"' na Angielski:");
                    poprawnaOdpowiedz = odpowiedzi[1];
                } else {
                    String[] odpowiedzi = ZagadkiTrudne();
                    zadanieLabel = new JLabel(odpowiedzi[0]);
                    poprawnaOdpowiedz = odpowiedzi[1];
                }

                zadanieLabel.setHorizontalAlignment(SwingConstants.CENTER);
                zadanieLabel.setFont(new Font("Arial", Font.PLAIN, 26));
                panel.add(zadanieLabel);

                JTextField pole_na_odpowiedz = new JTextField();
                pole_na_odpowiedz.setFont(new Font("Arial", Font.PLAIN, 26));
                pole_na_odpowiedz.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(pole_na_odpowiedz);

                JButton odpowiedzPrzycisk = new JButton("Ostateczna odpowiedź");
                odpowiedzPrzycisk.setHorizontalAlignment(SwingConstants.CENTER);
                odpowiedzPrzycisk.setFont(new Font("Arial", Font.PLAIN, 26));
                JPanel panelPrzycisk = new JPanel();
                panel.add(panelPrzycisk);
                panelPrzycisk.add(odpowiedzPrzycisk);


                odpowiedzPrzycisk.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String odpowiedzUzytkownika = pole_na_odpowiedz.getText();
                        if (odpowiedzUzytkownika.equalsIgnoreCase(poprawnaOdpowiedz)) {
                            //Poprawna odpowiedz
                            JOptionPane.showMessageDialog(null, "Super, poprawna odpowiedz! Dostajesz 3 kredyty :)!");
                            UI.przechowywanie.dodajPunkt(3,'+');

                        } else {
                            //Niepoprawna odpowiedz
                            JOptionPane.showMessageDialog(null, "Odpowiedź niepoprawna. Tracisz 3 kredyty :(");
                            UI.przechowywanie.dodajPunkt(3,'-');
                        }
                        zadanieFrame.dispose();
                    }
                });

                zadanieFrame.add(panel);
                zadanieFrame.pack();
                zadanieFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                zadanieFrame.setLocationRelativeTo(null);
                zadanieFrame.setVisible(true);
            }
        });

        PoziomTrudnosciMenu.add(latwyMenuItem);
        PoziomTrudnosciMenu.add(trudnyMenuItem);

        PoziomTrudnosciMenu.show(label_dodawanie_kredytow, 0, label_dodawanie_kredytow.getHeight());
    }

    private String[] TlumaczenieLatwe() {
        //Tworzymy słownik w postaci hashmapy
        Map<String, String> slownik = new HashMap<>();
        slownik.put("dog", "pies");
        slownik.put("cat", "kot");
        slownik.put("apple", "jabłko");
        slownik.put("car", "samochód");
        slownik.put("book", "książka");
        slownik.put("house", "dom");
        slownik.put("tree", "drzewo");
        slownik.put("computer", "komputer");
        slownik.put("flower", "kwiat");
        slownik.put("pencil", "ołówek");
        slownik.put("shoes", "buty");
        slownik.put("table", "stół");
        slownik.put("school", "szkoła");
        slownik.put("friend", "przyjaciel");
        slownik.put("bird", "ptak");
        slownik.put("music", "muzyka");
        slownik.put("city", "miasto");
        slownik.put("water", "woda");
        slownik.put("food", "jedzenie");
        slownik.put("sun", "słońce");

        //Lista słów angielskich
        List<String> slowaAngielskie = new ArrayList<>(slownik.keySet());

        //Losowe słowo do tłumaczenia
        int indeks = ThreadLocalRandom.current().nextInt(slowaAngielskie.size());
        String slowoDoTlumaczenia = slowaAngielskie.get(indeks);
        String poprawneTlumaczenie = slownik.get(slowoDoTlumaczenia);

        return new String[]{slowoDoTlumaczenia,poprawneTlumaczenie};


    }

    private String[] ZagadkiLatwe() {
        //Tworzymy zagadki i odpowiedzi w postaci hashmapy
        Map<String, String> zagadki = new HashMap<>();
        zagadki.put("On piąty jest w kolejce, gdzie stoi 12 miesięcy.", "maj");
        zagadki.put("Mierzy minuty, godziny, z nim się nigdy nie spóźnimy. Kiedy coś w nim się zepsuje, zegarmistrz go zreperuje.", "zegar");
        zagadki.put("Stoi przy drodze na jednej nodze.", "grzyb");
        zagadki.put("Cienki, gruby, w linie, w kratki. Można w nim pisać lub rysować kwiatki.", "zeszyt");
        zagadki.put("Jaki owad z tego słynie, że brzmi w trzcinie w Szczebrzeszynie?", "chrząszcz");

        //Lista zagadek
        List<String> zagadkiWyswietlanie = new ArrayList<>(zagadki.keySet());

        //Losowa zagadka
        int indeks = ThreadLocalRandom.current().nextInt(zagadkiWyswietlanie.size());
        String zagadkiDoWyswietlenia = zagadkiWyswietlanie.get(indeks);
        String rozwiazanieZagadki = zagadki.get(zagadkiDoWyswietlenia);

        return new String[]{zagadkiDoWyswietlenia,rozwiazanieZagadki};


    }

    private int[] MatmaTrudne(){
        //Losujemy dwa znaki spośród (+, -, *)
        char znak1;
        char znak2;
        Random rand = new Random();
        int los1 = rand.nextInt(3);
        int los2 = rand.nextInt(3);
        switch(los1){
            default -> znak1 = '+';
            case 1 -> znak1 = '-';
            case 2 -> znak1 = '*';
        }
        switch(los2){
            default -> znak2 = '+';
            case 1 -> znak2 = '-';
            case 2 -> znak2 = '*';
        }
        int a,b,c;
        if (znak1 == '*'){
            a = ThreadLocalRandom.current().nextInt(2, 9 + 1);
            b = ThreadLocalRandom.current().nextInt(2, 9 + 1);
            c = ThreadLocalRandom.current().nextInt(3, 160 + 1);
        }
        else if (znak2 == '*'){
            a = ThreadLocalRandom.current().nextInt(3, 160 + 1);
            b = ThreadLocalRandom.current().nextInt(2, 9 + 1);
            c = ThreadLocalRandom.current().nextInt(2, 9 + 1);
        }
        else if (znak1=='*' & znak2=='*'){
            a = ThreadLocalRandom.current().nextInt(3, 9 + 1);
            b = ThreadLocalRandom.current().nextInt(2, 9 + 1);
            c = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        }
        else {
            a = ThreadLocalRandom.current().nextInt(3, 160 + 1);
            b = ThreadLocalRandom.current().nextInt(3, 160 + 1);
            c = ThreadLocalRandom.current().nextInt(3, 160 + 1);
        }
        //Obliczanie poprawnej odpowiedzi
        int odp = 0;

        if (znak2!='*'){
            if (znak1 == '+') {
                odp = a + b;
            } else if (znak1 == '-') {
                odp = a - b;
            } else if (znak1 == '*') {
                odp = a * b;}

            if (znak2=='+'){
                odp = odp+c;
            }
            else {
                odp = odp-c;
            }
        }

        if (znak2=='*'){
            odp = b*c;
            if(znak1=='*'){
                odp = a*odp;
            }
            else if(znak1=='+'){
                odp = a+odp;
            }
            else{
                odp = a-odp;
            }
        }


        return new int[]{a,b,c, znak1, znak2,odp};
    }

    private String[] TlumaczenieTrudne() {
        //Tworzymy słownik w postaci hashmapy
        Map<String, String> slownik = new HashMap<>();
        slownik.put("bielizna", "lingerie");
        slownik.put("ekstrawagancja", "extravagance");
        slownik.put("konsekwentny", "consistent");
        slownik.put("kwintesencja", "quintessence");
        slownik.put("rozważny", "prudent");
        slownik.put("rozprzestrzenianie", "propagation");
        slownik.put("sporadyczny", "sporadic");
        slownik.put("antagonizm", "antagonism");
        slownik.put("arogancki", "arrogant");
        slownik.put("definicja", "definition");
        slownik.put("egzemplarz", "specimen");
        slownik.put("inspiracja", "inspiration");
        slownik.put("manipulacja", "manipulation");
        slownik.put("odzwierciedlenie", "reflection");
        slownik.put("okrutny", "cruel");
        slownik.put("subtelność", "subtlety");
        slownik.put("złożoność", "complexity");

        //Lista słów polskich
        List<String> slowaPolskie = new ArrayList<>(slownik.keySet());

        //Losowe słowo do tłumaczenia
        int indeks = ThreadLocalRandom.current().nextInt(slowaPolskie.size());
        String slowoDoTlumaczenia = slowaPolskie.get(indeks);
        String poprawneTlumaczenie = slownik.get(slowoDoTlumaczenia);

        return new String[]{slowoDoTlumaczenia,poprawneTlumaczenie};


    }

    private String[] ZagadkiTrudne() {
        //Tworzymy zagadki i odpowiedzi w postaci hashmapy
        Map<String, String> zagadki = new HashMap<>();
        zagadki.put("I'm full of keys, but I can't open any locks. What am I?", "A piano");
        zagadki.put("I'm a place where you can find stories and knowledge bound in sheets. What am I?", "A library");
        zagadki.put("I'm a fiery ball in the sky, bringing light and warmth to the world. What am I?", "The sun");
        zagadki.put("I'm a source of energy, but I'm stored in a small, portable container. What am I?", "A battery");
        zagadki.put("I have hands but can't clap, a face but can't smile. What am I?", "A clock");


        //Lista zagadek
        List<String> zagadkiWyswietlanie = new ArrayList<>(zagadki.keySet());

        //Losowa zagadka
        int indeks = ThreadLocalRandom.current().nextInt(zagadkiWyswietlanie.size());
        String zagadkiDoWyswietlenia = zagadkiWyswietlanie.get(indeks);
        String rozwiazanieZagadki = zagadki.get(zagadkiDoWyswietlenia);

        return new String[]{zagadkiDoWyswietlenia,rozwiazanieZagadki};


    }



}