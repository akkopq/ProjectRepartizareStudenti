public class OutputManager {
    public void displayMenu() {
        System.out.println("+-----------------------------------------------+");
        System.out.println("|          Sistem Repartizare Studenti          |");
        System.out.println("+-----------------------------------------------+");
        System.out.println("| 1. Adauga manual studenti                     |");
        System.out.println("| 2. Importa studenti din fisier                |");
        System.out.println("| 3. Repartizeaza studenti in sali              |");
        System.out.println("| 4. Vizualizeaza repartizarea                  |");
        System.out.println("| 5. Sterge lista studenti                      |");
        System.out.println("| 6. Modifica lista studenti                    |");
        System.out.println("| 7. Iesire                                     |");
        System.out.println("+-----------------------------------------------+");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
