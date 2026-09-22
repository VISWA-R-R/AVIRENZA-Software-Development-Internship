import java.util.ArrayList;
import java.util.Scanner;

public class StudentRecordManager {

    // Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);

    // Store student records
    private static final ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        boolean running = true;

        System.out.println();
        System.out.println("=================================================");
        System.out.println("       STUDENT RECORD MANAGEMENT SYSTEM");
        System.out.println("             AVIRENZA TECHNOLOGIES");
        System.out.println("=================================================");

        while (running) {

            displayMenu();

            int choice = getMenuChoice();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    running = false;
                    System.out.println();
                    System.out.println("Thank you for using Student Record Manager.");
                    System.out.println("Program ended successfully.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    // =====================================================
    // STUDENT CLASS
    // =====================================================

    static class Student {

        private int id;
        private String name;
        private String course;
        private double marks;

        public Student(int id, String name, String course, double marks) {
            this.id = id;
            this.name = name;
            this.course = course;
            this.marks = marks;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCourse() {
            return course;
        }

        public double getMarks() {
            return marks;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public void setMarks(double marks) {
            this.marks = marks;
        }

        public String getGrade() {

            if (marks >= 90) {
                return "A+";
            } else if (marks >= 80) {
                return "A";
            } else if (marks >= 70) {
                return "B";
            } else if (marks >= 60) {
                return "C";
            } else if (marks >= 50) {
                return "D";
            } else {
                return "F";
            }
        }
    }

    // =====================================================
    // MENU
    // =====================================================

    private static void displayMenu() {

        System.out.println();
        System.out.println("================ MAIN MENU =================");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.println("============================================");

    }

    // =====================================================
    // MENU VALIDATION
    // =====================================================

    private static int getMenuChoice() {

        while (true) {

            System.out.print("Enter your choice (1-6): ");

            if (scanner.hasNextInt()) {

                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice >= 1 && choice <= 6) {
                    return choice;
                }

                System.out.println(
                        "Invalid choice. Please enter a number from 1 to 6."
                );

            } else {

                System.out.println(
                        "Invalid input. Please enter a number."
                );

                scanner.nextLine();
            }
        }
    }

    // =====================================================
    // ADD STUDENT
    // =====================================================

    private static void addStudent() {

        System.out.println();
        System.out.println("=============== ADD STUDENT =================");

        int id = getStudentId();

        // Check duplicate ID
        if (findStudentById(id) != null) {

            System.out.println();
            System.out.println("ERROR: Student ID already exists.");

            return;
        }

        String name = getStudentName();

        String course = getCourse();

        double marks = getMarks();

        Student student = new Student(
                id,
                name,
                course,
                marks
        );

        students.add(student);

        System.out.println();
        System.out.println("Student added successfully!");
    }

    // =====================================================
    // GET STUDENT ID
    // =====================================================

    private static int getStudentId() {

        while (true) {

            System.out.print("Enter Student ID: ");

            if (scanner.hasNextInt()) {

                int id = scanner.nextInt();
                scanner.nextLine();

                if (id > 0) {
                    return id;
                }

                System.out.println(
                        "Student ID must be greater than zero."
                );

            } else {

                System.out.println(
                        "Invalid ID. Please enter a number."
                );

                scanner.nextLine();
            }
        }
    }

    // =====================================================
    // GET STUDENT NAME
    // =====================================================

    private static String getStudentName() {

        while (true) {

            System.out.print("Enter Student Name: ");

            String name = scanner.nextLine().trim();

            if (!name.isEmpty()) {
                return name;
            }

            System.out.println(
                    "Name cannot be empty."
            );
        }
    }

    // =====================================================
    // GET COURSE
    // =====================================================

    private static String getCourse() {

        while (true) {

            System.out.print("Enter Course: ");

            String course = scanner.nextLine().trim();

            if (!course.isEmpty()) {
                return course;
            }

            System.out.println(
                    "Course cannot be empty."
            );
        }
    }

    // =====================================================
    // GET MARKS
    // =====================================================

    private static double getMarks() {

        while (true) {

            System.out.print("Enter Marks (0-100): ");

            if (scanner.hasNextDouble()) {

                double marks = scanner.nextDouble();
                scanner.nextLine();

                if (marks >= 0 && marks <= 100) {
                    return marks;
                }

                System.out.println(
                        "Marks must be between 0 and 100."
                );

            } else {

                System.out.println(
                        "Invalid marks. Please enter a number."
                );

                scanner.nextLine();
            }
        }
    }

    // =====================================================
    // VIEW STUDENTS
    // =====================================================

    private static void viewStudents() {

        System.out.println();
        System.out.println("=============== STUDENT RECORDS =============");

        if (students.isEmpty()) {

            System.out.println("No student records found.");

            return;
        }

        System.out.printf(
                "%-8s %-20s %-20s %-10s %-8s%n",
                "ID",
                "NAME",
                "COURSE",
                "MARKS",
                "GRADE"
        );

        System.out.println(
                "------------------------------------------------------------------"
        );

        for (Student student : students) {

            System.out.printf(
                    "%-8d %-20s %-20s %-10.2f %-8s%n",
                    student.getId(),
                    student.getName(),
                    student.getCourse(),
                    student.getMarks(),
                    student.getGrade()
            );
        }

        System.out.println(
                "------------------------------------------------------------------"
        );

        System.out.println(
                "Total Students: " + students.size()
        );
    }

    // =====================================================
    // SEARCH STUDENT
    // =====================================================

    private static void searchStudent() {

        System.out.println();
        System.out.println("=============== SEARCH STUDENT ===============");

        int id = getSearchId();

        Student student = findStudentById(id);

        if (student == null) {

            System.out.println(
                    "Student with ID " + id + " not found."
            );

            return;
        }

        displayStudent(student);
    }

    // =====================================================
    // UPDATE STUDENT
    // =====================================================

    private static void updateStudent() {

        System.out.println();
        System.out.println("=============== UPDATE STUDENT ===============");

        int id = getSearchId();

        Student student = findStudentById(id);

        if (student == null) {

            System.out.println(
                    "Student with ID " + id + " not found."
            );

            return;
        }

        System.out.println(
                "Current student: " + student.getName()
        );

        System.out.println();

        String newName = getStudentName();

        String newCourse = getCourse();

        double newMarks = getMarks();

        student.setName(newName);
        student.setCourse(newCourse);
        student.setMarks(newMarks);

        System.out.println();
        System.out.println(
                "Student record updated successfully!"
        );
    }

    // =====================================================
    // DELETE STUDENT
    // =====================================================

    private static void deleteStudent() {

        System.out.println();
        System.out.println("=============== DELETE STUDENT ===============");

        int id = getSearchId();

        Student student = findStudentById(id);

        if (student == null) {

            System.out.println(
                    "Student with ID " + id + " not found."
            );

            return;
        }

        System.out.println(
                "Student found: " + student.getName()
        );

        System.out.print(
                "Are you sure you want to delete? (Y/N): "
        );

        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("Y")) {

            students.remove(student);

            System.out.println();
            System.out.println(
                    "Student record deleted successfully!"
            );

        } else {

            System.out.println();
            System.out.println(
                    "Delete operation cancelled."
            );
        }
    }

    // =====================================================
    // SEARCH ID VALIDATION
    // =====================================================

    private static int getSearchId() {

        while (true) {

            System.out.print("Enter Student ID: ");

            if (scanner.hasNextInt()) {

                int id = scanner.nextInt();
                scanner.nextLine();

                if (id > 0) {
                    return id;
                }

                System.out.println(
                        "ID must be greater than zero."
                );

            } else {

                System.out.println(
                        "Invalid ID. Please enter a number."
                );

                scanner.nextLine();
            }
        }
    }

    // =====================================================
    // FIND STUDENT
    // =====================================================

    private static Student findStudentById(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    // =====================================================
    // DISPLAY STUDENT
    // =====================================================

    private static void displayStudent(Student student) {

        System.out.println();
        System.out.println("------------- STUDENT DETAILS ---------------");

        System.out.println("ID     : " + student.getId());
        System.out.println("Name   : " + student.getName());
        System.out.println("Course : " + student.getCourse());
        System.out.println("Marks  : " + student.getMarks());
        System.out.println("Grade  : " + student.getGrade());

        System.out.println(
                "---------------------------------------------"
        );
    }
}