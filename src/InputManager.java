import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class InputManager {
    private Scanner scanner;

    public InputManager() {
        scanner = new Scanner(System.in);
    }

    public int getUserChoice() {
        System.out.print("Introduceti nr. actiunii pe care doriti sa o efectuati: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Alegere invalida. Selectati un nr. de la 1 si 7");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public List<String> addStudentsManually() {
        List<String> students = new ArrayList<>();
        scanner.nextLine();
        System.out.println("Adaugati numele studentilor. Introduceti 'ok' pentru confirmare:");
        String studentName;
        while (!(studentName = scanner.nextLine()).equalsIgnoreCase("ok")) {
            if (!studentName.trim().isEmpty()) {
                students.add(studentName);
            }
        }
        return students;
    }

    public String getFilePath() {
        System.out.print("Introduceti locatia fisierului: ");
        return scanner.next();
    }
}
