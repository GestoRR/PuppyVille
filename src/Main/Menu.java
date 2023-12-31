package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Służy do wyswietlenia i obsługi menu po wciśnięciu przycisku esc.
 */
public class Menu {
    /**
     * Czcionka uzywana do JButtonów zawierających napis "Zamknij Menu" oraz "Opuść Gre".
     */
    Font czcionka = new Font("Arial",Font.BOLD,48);

    /**
     * Służy do inicjalizacji ActionListenera na przycisk esc
     */
    public void Menu() {
        dodajListeneraNaEsc();
    }

    /**
     * Służy do wyświetlenia okna po naciśnięciu esc, zawierającego dwa JButtony: zamknij menu oraz opuść gre.
     * Następnie nakładane są ActionListenery na te JButtony w celu obsługi zdarzeń użytkownika.
     */
    private void dodajListeneraNaEsc() {
        ActionListener escapeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame oknoMenu = new JFrame("MENU");
                oknoMenu.setSize(553, 603);
                oknoMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                oknoMenu.setVisible(true);
                oknoMenu.setLocationRelativeTo(null);
                oknoMenu.setAlwaysOnTop(true);
                oknoMenu.setLayout(null);

                JButton opuscGre = new JButton("OPUŚĆ GRĘ");
                opuscGre.setBounds(50,300,453,160);
                opuscGre.setHorizontalAlignment(SwingConstants.CENTER);
                opuscGre.setFont(czcionka);
                opuscGre.setBackground(Color.red);
                opuscGre.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });

                oknoMenu.add(opuscGre);

                JButton zamknijMenu = new JButton("ZAMKNIJ MENU");
                zamknijMenu.setBounds(50,100,453,160);
                zamknijMenu.setHorizontalAlignment(SwingConstants.CENTER);
                zamknijMenu.setFont(czcionka);
                zamknijMenu.setBackground(Color.lightGray);
                zamknijMenu.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        oknoMenu.dispose();
                    }
                });
                oknoMenu.add(zamknijMenu);
            }
        };

        //Przechwycanie zdarzeń klawiatury nawet jeśli komponent nie ma ustawionego focusu.
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_TYPED && e.getKeyChar() == 27) {
                    escapeListener.actionPerformed(null);
                }
                return false;
            }
        });
    }


    public static void main(String[] args) {
        new Menu();
    }
}
