import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

public class ExamRoomAssigner {
    private List<String> students;

    public ExamRoomAssigner(List<String> students) {
        this.students = students;
    }

    public void assignStudentsToExamRooms(OutputManager outputManager) {
        if (students.isEmpty()) {
            outputManager.displayMessage("Lista de studenti este goala.");
            return;
        }
        Collections.sort(students);

        int totalStudents = students.size();
        int studentsPerRoom = totalStudents / 3;
        int remainingStudents = totalStudents % 3;

        try {
            PrintWriter writer = new PrintWriter("assigned_students.txt");
            int startIndex = 0;
            for (int room = 1; room <= 3; room++) {
                int roomSize = studentsPerRoom + (room <= remainingStudents ? 1 : 0);
                writer.println("Classroom " + room);
                for (int i = 0; i < roomSize; i++) {
                    writer.println(students.get(startIndex + i));
                }
                startIndex += roomSize;
                if (room < 3) {
                    writer.println();
                }
            }
            writer.close();
            outputManager.displayMessage("Studentii au fost repartizati si salvati in assigned_students.txt.");
        } catch (Exception e) {
            outputManager.displayMessage(e.getMessage());
        }
    }
}
