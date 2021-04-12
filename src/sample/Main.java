package sample;
import Course.Course;
import Course.CourseManagement;
import DuplicatedCheck.CheckExistedVisitor;
import DuplicatedCheck.Pair;
import DuplicatedCheck.Visitor;
import Enrollment.*;
import Print.*;
import Student.Student;
import Student.StudentBuilder;
import Utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import List.StudentList;
public class Main {
    public static void main(String[] args) {

        Utils utilities = new Utils();

        //sample students which are held in a student list, created using Builder Pattern
        StudentBuilder stdBuilder1 = new StudentBuilder();
        StudentBuilder stdBuilder2 = new StudentBuilder();
        StudentBuilder stdBuilder3 = new StudentBuilder();
        StudentBuilder stdBuilder4 = new StudentBuilder();

        Student stud1 = stdBuilder1.addId("01").addName("Nguyen Hung Dung").addBirthdate("09/10/1995").buildStudent();
        Student stud2 = stdBuilder2.addId("02").addName("Nguyen Danh Duc").addBirthdate("03/01/1996").buildStudent();
        Student stud3 = stdBuilder3.addId("03").addName("Nguyen Dang Quang").addBirthdate("02/01/1989").buildStudent();
        Student stud4 = stdBuilder4.addId("04").addName("Nguyen Duc Dung").addBirthdate("01/06/1994").buildStudent();

        List<Student> students = new ArrayList<>();
        students.addAll(Arrays.asList(stud1,stud2,stud3,stud4));

        StudentList studentList = new StudentList();
        studentList.setStudents(students);


        //sample courses which are held in a course list, created using Factory Pattern
        Course course1 = CourseManagement.newCourse("COSC2500", "ICS", 12);
        Course course2 = CourseManagement.newCourse("COSC2440", "SADI", 12);
        Course course3 = CourseManagement.newCourse("ISYS2101", "SEPM", 12);
        Course course4 = CourseManagement.newCourse("COSC2652", "UCD", 12);

        course1.addCourse(course1);
        course1.addCourse(course2);
        course1.addCourse(course3);
        course1.addCourse(course4);

        //Chain Of Responsibility Pattern
        Chain link1 = new AllEnrollment();
        Chain link2 = new SemEnrollment();
        Chain link3 = new PrintStuEnrollment();
        Chain link4 = new CourseEnrollment();

        link1.setNextChain(link2);
        link2.setNextChain(link3);
        link3.setNextChain(link4);

        //Visitor Pattern
        CheckExistedVisitor checkDuplicate = new CheckExistedVisitor();

        //Singleton Pattern
        ExistedStudentEnrollmentManager manager = ExistedStudentEnrollmentManager.getInstance();

        //menu loop
        boolean quit = false;
        while(!quit) {
            System.out.println("\nEnrollment System! Please select the number below\n" +
                    "1. Add new enrollment\n" +
                    "2. Update your enrollment\n" +
                    "3. Delete your enrollment\n" +
                    "4. Print all enrollments\n" +
                    "5. Print enrollments for a student\n" +
                    "6. Print enrollments for a semester\n" +
                    "7. Print enrollments for a course \n" +
                    "------------------------------------\n" +
                    "8) Display all students\n" +
                    "9) Display all courses\n" +
                    "0) Exits");

            Scanner scanner1 = new Scanner(System.in);
            String option = "";

            if (scanner1.hasNext()) {
                option = scanner1.next();
            }

            switch (option) {
                case "1":
                    System.out.println("Add new enrollment:\n");
                    //create enrollment
                    StudentEnrollment newEnrollment = utilities.formNewEnrollment(studentList , course1);
                    if (newEnrollment == null) break;

                    //check for duplicated enrollment
                    manager.setToBeCompare(newEnrollment);
                    boolean duplicatedEnrollment = ((Boolean)manager.invite(checkDuplicate).existed);

                    if (duplicatedEnrollment) {
                        System.out.println("\nYour enrollment has already existed!");
                        break;
                    }

                    //Command Pattern
                    NewEnrollment adder = new NewEnrollment(newEnrollment);
                    adder.execute();
                    //provide the option of undo
                    String undo = utilities.getUndo();
                    if (undo.equals("y")) adder.undo();
                    break;

                case "2":
                    //check if any enrollment exists
                    boolean enrollmentExists = utilities.checkEmptyEnrollmentList();
                    if (!enrollmentExists) break;

                    System.out.println("Update your enrollment:\n");
                    StudentEnrollment toBeUpdated = utilities.formNewEnrollment(studentList, course1);
                    if (toBeUpdated == null) break;

                    //check if needed enrollment exists
                    manager.setToBeCompare(toBeUpdated);
                    Pair<Boolean,Integer> resultPair  = manager.invite(checkDuplicate);
                    boolean duplicatedEn = resultPair.existed;
                    Integer indexOfDup = resultPair.index;

                    if ((!duplicatedEn) && (indexOfDup == null)) {
                        System.out.println("\nEnrollment not found!\n");
                        break;
                    }

                    System.out.println("Enrollment found. Enter new update: ");
                    //Command Pattern
                    StudentEnrollment update = utilities.formNewEnrollment(studentList, course1);
                    UpdateEnrollment updater = new UpdateEnrollment(update, indexOfDup);
                    updater.execute();
                    String undo1 = utilities.getUndo();
                    if (undo1.equals("y")) updater.undo();

                    break;


                case "3":
                    //check if any enrollment exists
                    boolean enrollmentExists1 = utilities.checkEmptyEnrollmentList();
                    if (!enrollmentExists1) break;

                    System.out.println("Delete your enrollment:\n");
                    StudentEnrollment toBeDeleted = utilities.formNewEnrollment(studentList, course1);
                    if (toBeDeleted == null) break;

                    manager.setToBeCompare(toBeDeleted);
                    boolean duplicated = ((Boolean)manager.invite(checkDuplicate).existed);;
                    if (!duplicated) {
                        System.out.println("\nEnrollment not found! Nothing to delete.\n");
                        break;
                    }

                    //more Command Pattern
                    DeleteEnrollment remover = new DeleteEnrollment(toBeDeleted);
                    remover.execute();
                    String undo2 = utilities.getUndo();
                    if (undo2.equals("y")) remover.undo();

                    break;

                case "4":
                    //check if any enrollment exists
                    boolean enrollmentExists2 = utilities.checkEmptyEnrollmentList();
                    if (!enrollmentExists2) break;

                    Request req1 = new Request("print all");
                    link1.print(req1);
                    break;

                case "5":
                    //check if any enrollment exists
                    boolean enrollmentExists3 = utilities.checkEmptyEnrollmentList();
                    if (!enrollmentExists3) break;

                    Request req2 = new Request("Print student");
                    link1.print(req2);
                    break;

                case "6":
                    //check if any enrollment exists
                    boolean enrollmentExists4 = utilities.checkEmptyEnrollmentList();
                    if (!enrollmentExists4) break;

                    Request req3 = new Request("Print semester");
                    link1.print(req3);
                    break;

                case "7":
                    //check if any enrollment exists
                    boolean enrollmentExists5 = utilities.checkEmptyEnrollmentList();
                    if (!enrollmentExists5) break;

                    Request req4 = new Request("Print course");
                    link1.print(req4);
                    break;

                case "8":
                    studentList.displayAllStudents();
                    break;

                case "9":
                    course1.getAllCourse();
                    break;

                case "0":
                    System.out.println("Program Exits");
                    quit = true;
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }

        }
    }

}
