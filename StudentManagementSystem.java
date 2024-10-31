import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StudentManagementSystem {
    private static final int MAX_STUDENTS = 100;
    // Array to store all student objects
    private static Student[] studentIds = new Student[MAX_STUDENTS];
    private static int studentCount = 0;
    // Scanner to read user input
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            // Show menu and get user's choice
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                checkAvailableSeats();
            } else if (choice == 2) {
                registerStudent();
            } else if (choice == 3) {
                deleteStudent();
            } else if (choice == 4) {
                findStudent();
            } else if (choice == 5) {
                storeStudentDetails();
            } else if (choice == 6) {
                loadStudentDetails();
            } else if (choice == 7) {
                viewStudentList();
            } else if (choice == 8) {
                moreControls();
            } else if (choice == 9) {
                generateSummaryReport();
            } else if (choice == 10) {
                generateDetailedReport();
            } else if (choice == 11) {
                System.out.println("Exiting the program !");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 11);
    }

    // Display the main menu
    private static void displayMenu() {
        System.out.println("\n--- Student Management System ---");
        System.out.println("1. Check available seats");
        System.out.println("2. Register student");
        System.out.println("3. Delete student");
        System.out.println("4. Find student");
        System.out.println("5. Store student details into a file ");
        System.out.println("6. Load student details from the file to the system ");
        System.out.println("7. View student list");
        System.out.println("8. More controls");
        System.out.println("9. Generate summary report");
        System.out.println("10. Generate detailed report");
        System.out.println("11. Exit");
    }

    // Check how many seats are still available
    private static void checkAvailableSeats() {
        int availableSeats = MAX_STUDENTS - studentCount;
        System.out.println("Available seats: " + availableSeats);
    }
    // Register a new student
    private static void registerStudent() {
        if (studentCount < MAX_STUDENTS) {
            System.out.print("Enter student ID (e.g., 'w1234567'): ");
            String studentId = scanner.nextLine();
            studentIds[studentCount] = new Student(studentId);
            studentCount++;
            System.out.println("Student registered successfully.");
        } else {
            System.out.println("No available seats.");
        }
    }
    // Delete a student from the system
    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        String studentId = scanner.nextLine();
        for (int i = 0; i < studentCount; i++) {
            if (studentIds[i].getStudentId().equals(studentId)) {
                for (int j = i; j < studentCount - 1; j++) {
                    studentIds[j] = studentIds[j + 1];
                }
                studentCount--;
                System.out.println("Student deleted successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }


    // Find a student in the system
    private static void findStudent() {
        System.out.print("Enter student ID to find: ");
        String studentId = scanner.nextLine();
        for (int i = 0; i < studentCount; i++) {
            if (studentIds[i].getStudentId().equals(studentId)) {
                System.out.println("Student found: " + studentIds[i].getName() + " at index: " + i);
                return;
            }
        }
        System.out.println("Student not found.");
    }
    // Save all student details to a file
    private static void storeStudentDetails() {
        try {
            FileWriter writer = new FileWriter("students.txt");
            for (int i = 0; i < studentCount; i++) {
                writer.write(studentIds[i] + "\n");
            }
            writer.close();
            System.out.println("Student details stored successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while storing student details.");
        }
    }
    // Load student details from a file
    private static void loadStudentDetails() {
        try {
            File file = new File("students.txt");
            Scanner fileScanner = new Scanner(file);
            studentCount = 0;
            while (fileScanner.hasNextLine() && studentCount < MAX_STUDENTS) {
                studentIds[studentCount] = new Student (fileScanner.nextLine());
                studentCount++;
            }
            fileScanner.close();
            System.out.println("Student details loaded successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading student details.");
        }
    }
    // View and sort the list of students
    private static void viewStudentList() {
        if (studentCount == 0) {
            System.out.println("No students registered.");
            return;
        }

        // Sort students by name (bubble sort)
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {

                String name1 = studentIds[j].getName();
                String name2 = studentIds[j + 1].getName();
                if (name1.compareToIgnoreCase(name2) > 0) {
                    // Swap students
                    Student temp = studentIds[j];
                    studentIds[j] = studentIds[j + 1];
                    studentIds[j + 1] = temp;
                }
            }
        }

        // Display sorted list
        System.out.println("Sorted list of students by name:");
        for (int i = 0; i < studentCount; i++) {
            String name = studentIds[i].getName();
            String id = studentIds[i].getStudentId();
            System.out.println(name + " - " + id);
        }
    }

    private static void  moreControls() {
        System.out.println("\nMore Controls");
        System.out.println("a. Add student name");
        System.out.println("b. Add module marks");
        System.out.print("Enter your choice (a or b): ");
        char subChoice = scanner.nextLine().charAt(0);

        if (subChoice == 'a') {
            addStudentName();
        } else if (subChoice == 'b') {
            addModuleMarks();
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static void addStudentName() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < studentCount; i++) {
            if (studentIds[i].getStudentId().equals(id)) {
                studentIds[i].setName(name);
                System.out.println("Name added successfully for student ID: " + id);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }
    // Add module marks for a student
    private static void addModuleMarks() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        boolean found = false;
        for (int i = 0; i < studentCount; i++) {
            if (studentIds[i].getStudentId().equals(id)) {
                for (int j = 0; j < 3; j++) {
                    System.out.print("Enter mark for Module " + (j + 1) + ": ");
                    double mark = scanner.nextDouble();
                    studentIds[i].setModule(j, new Module("Module " + (j + 1), mark));
                }
                scanner.nextLine(); // Consume newline
                System.out.println("Module marks added successfully for student ID: " + id);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }
    // Generate a summary report
    private static void generateSummaryReport() {
        System.out.println("\n--- Summary Report ---");
        System.out.println("Total student registrations: " + studentCount);

        int[] passCount = new int[3];
        for (int i = 0; i < studentCount; i++) {
            Module[] modules = studentIds[i].getModules();
            for (int j = 0; j < 3; j++) {
                if (modules[j] != null && modules[j].getMark() > 40) {
                    passCount[j]++;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            System.out.println("Students who passed Module " + (i + 1) + ": " + passCount[i]);
        }

    }

    private static void generateDetailedReport() {
        System.out.println("\n Detailed Report ");
        System.out.println("                           Total      \tAvg       \tGrade");

        for (int i = 0; i < studentCount; i++) {
            Student student = studentIds[i];


            System.out.println("student data:");
            System.out.println("  ID: " + student.getStudentId());
            System.out.println("  Name: " + student.getName());

            Module[] modules = student.getModules();
            System.out.println("  Modules:");
            for (int j = 0; j < 3; j++) {
                if (modules[j] != null) {
                    System.out.println("    Module " + (j+1) + ": " + modules[j].getMark());
                } else {
                    System.out.println("    Module " + (j+1) + ": null");
                }
            }

            double mod1 = (modules[0] != null) ? modules[0].getMark() : 0;
            double mod2 = (modules[1] != null) ? modules[1].getMark() : 0;
            double mod3 = (modules[2] != null) ? modules[2].getMark() : 0;

            double total = mod1 + mod2 + mod3;
            double average = (mod1 + mod2 + mod3) / 3;
            String grade = calculateGrade(average);

            System.out.printf("%s\t%s\t%.1f\t%.1f\t%.1f\t%.1f\t%.1f\t%s%n",
                    student.getStudentId(),
                    student.getName().isEmpty() ? "N/A" : student.getName(),
                    mod1, mod2, mod3,
                    total,
                    average,
                    grade);

            System.out.println();
        }
    }

    private static String calculateGrade(double average) {
        if (average >= 80) return "Distinction";
        if (average >= 70) return "Merit";
        if (average >= 40) return "Pass";
        return "Fail";
    }
}




