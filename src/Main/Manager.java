package Main;

/**
 * Wywołuje klasę UI tym samym uruchamiając program.
 */

public class Manager {
    UI ui = new UI(this);

    /**
     * Jest to metoda główna programu. Tworzy nową instancję klasy Manager, co rozpoczyna wykonanie programu.
     * @param args Argumenty linii poleceń przekazywane do programu.
     */
    public static void main(String[] args) {
        new Manager();
    }
    public Manager(){

    }
}