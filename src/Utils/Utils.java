package Utils;

import Course.Course;
import DuplicatedCheck.CheckExistedVisitor;
import Enrollment.ExistedStudentEnrollmentManager;
import Enrollment.StudentEnrollment;
import DuplicatedCheck.CheckExistedVisitor;
import DuplicatedCheck.Pair;
import Student.Student;
import List.StudentList;
import java.util.Scanner;

    public class Utils {

        ExistedStudentEnrollmentManager manager = ExistedStudentEnrollmentManager.getInstance();
        CheckExistedVisitor checkDuplicate = new CheckExistedVisitor();

        public boolean checkEmptyEnrollmentList(){
            if (manager.getAllEnrollment().size() == 0) {
                System.out.println("\nYour Enrollment is empty\n");
                return false;
            }
            return true;
        }

        public String getInput(){
            Scanner scanner1 = new Scanner(System.in);
            if (scanner1.hasNext()) {
                return scanner1.next();
            }   else return null;
        }


        public StudentEnrollment formNewEnrollment(StudentList studentList, Course course1) {
            //get student from student id
            System.out.print("Input student id: ");
            String stuId = getInput();
            studentList.setToBeCompared(stuId);
            Pair resultStu = studentList.invite(checkDuplicate);
            if (!((Boolean)resultStu.existed)) return null;

            int indexStu = (Integer)resultStu.index;
            Student studentToEnroll = studentList.getStudents().get(indexStu);


            //get course from course name
            System.out.print("Input course id: ");
            String courseId = getInput();
            course1.setToBeCompared(courseId);
            Pair resultCourse = course1.invite(checkDuplicate);
            if (!((Boolean)resultCourse.existed)) return null;
            int indexCourse = (Integer)resultCourse.index;
            Course courseToEnroll = course1.getCourseList().get(indexCourse);

            //get semester
            System.out.print("Input semester: ");
            String semester = getInput();

            //create enrollment with Dependency Injection
            StudentEnrollment newEnrollment = new StudentEnrollment(studentToEnroll, courseToEnroll, semester);

            return  newEnrollment;
        }


        public String getUndo() {
            String undo = "";
            while (!(undo.toLowerCase().equals("y") || undo.toLowerCase().equals("n"))) {
                System.out.println("Do you want to undo? (y/n)");
                undo = getInput();
            }
            return undo.toLowerCase();
        }
    }

