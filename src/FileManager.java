import java.io.*;
import java.util.*;

public class FileManager {
    public List<String> readStudentsFromFile(String filePath) {
        List<String> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    students.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Eroare citire din fisier: " + e.getMessage());
        }
        return students;
    }

    public void writeStudentsToFile(String filePath, List<String> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (String student : students) {
                writer.println(student);
            }
        } catch (IOException e) {
            System.err.println("Eroare scriere in fisier: " + e.getMessage());
        }
    }
}
