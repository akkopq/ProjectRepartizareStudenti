import java.io.*;
import java.util.*;

public class ExamRoomAssignmentSystem {
    private final InputManager inputManager;
    private final OutputManager outputManager;
    private final FileManager fileManager;
    private List<String> students;

    private ExamRoomAssigner examRoomAssigner;

    public static void main(String[] args) {
        ExamRoomAssignmentSystem app = new ExamRoomAssignmentSystem();
        app.run();
    }

    public ExamRoomAssignmentSystem() {
        inputManager = new InputManager();
        outputManager = new OutputManager();
        fileManager = new FileManager();
        students = fileManager.readStudentsFromFile("students.txt");
        examRoomAssigner = new ExamRoomAssigner(students);
    }

    public void run() {
        boolean running = true;

        while (running) {
            outputManager.displayMenu();
            int choice = inputManager.getUserChoice();
            switch (choice) {
                case 1:
                    students.addAll(inputManager.addStudentsManually());
                    break;
                case 2:
                    students.addAll(fileManager.readStudentsFromFile(inputManager.getFilePath()));
                    break;
                case 3:
                    examRoomAssigner.assignStudentsToExamRooms(outputManager);
                    break;
                case 4:
                    viewAssignedExamRooms();
                    break;
                case 5:
                    clearStudentsFile();
                    break;
                case 6:
                    updateStudentsFile();
                    break;
                case 7:
                    exitProgram();
                    running = false;
                    break;
                default:
                    outputManager.displayMessage("Alegere invalida. Selectati un nr. de la 1 si 7.");
            }
        }
    }

    private void viewAssignedExamRooms() {
        try (BufferedReader reader = new BufferedReader(new FileReader("assigned_students.txt"))) {
            String line;
            int classroomNumber = 1;
            boolean isLastLineEmpty = false;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    if (!isLastLineEmpty) {
                        printClassroom(classroomNumber);
                        printClassroomMatrix(classroomNumber);
                        classroomNumber++;
                    }
                    isLastLineEmpty = true;
                    continue;
                } else if (line.startsWith("Classroom")) {
                    isLastLineEmpty = false;
                } else {
                    isLastLineEmpty = false;
                }
            }
            printClassroom(classroomNumber);
            printClassroomMatrix(classroomNumber);
        } catch (IOException e) {
            outputManager.displayMessage(e.getMessage());
        }
    }


    private void printClassroom(int classroomNumber) {
        outputManager.displayMessage("Classroom " + classroomNumber + " Student List:");
        int studentNumber = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader("assigned_students.txt"))) {
            String line;
            boolean inTargetClassroom = false;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    inTargetClassroom = false;
                } else if (line.startsWith("Classroom " + classroomNumber)) {
                    inTargetClassroom = true;
                } else if (inTargetClassroom) {
                    if (studentNumber <= 9)
                    {
                        outputManager.displayMessage(" "+ studentNumber + ". " + line);
                    }else {
                        outputManager.displayMessage(studentNumber + ". " + line); }
                    studentNumber++;
                }
            }
        } catch (IOException e) {
            outputManager.displayMessage(e.getMessage());
        }
    }


    private void printClassroomMatrix(int classroomNumber) {
        System.out.println();
        outputManager.displayMessage("Repartizare studenti:");
        int studentCount = getStudentCountInClassroom(classroomNumber);
        int studentNumber = 1;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                if (studentNumber <= studentCount) {
                    System.out.print(studentNumber + "\t");
                } else {
                    System.out.print("*\t");
                }
                studentNumber++;
            }
            System.out.println();
        }
        System.out.println();
    }

    private int getStudentCountInClassroom(int classroomNumber) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("assigned_students.txt"))) {
            String line;
            boolean inTargetClassroom = false;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    inTargetClassroom = false;
                } else if (line.startsWith("Classroom " + classroomNumber)) {
                    inTargetClassroom = true;
                } else if (inTargetClassroom) {
                    count++;
                }
            }
        } catch (IOException e) {
            outputManager.displayMessage(e.getMessage());
        }
        return count;
    }

    private void clearStudentsFile() {
        students.clear();
        outputManager.displayMessage("Lista de studenti a fost stearsa.");
    }

    private void updateStudentsFile() {
        fileManager.writeStudentsToFile("students.txt", students);
        outputManager.displayMessage("Lista de studenti a fost modificata.");
    }

    private void exitProgram() {
        outputManager.displayMessage("Inchidere program...");
    }
}
