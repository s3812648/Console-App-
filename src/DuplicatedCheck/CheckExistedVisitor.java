package DuplicatedCheck;

import Course.Course;
import Enrollment.ExistedStudentEnrollmentManager;
import Enrollment.StudentEnrollment;
import Student.Student;
import List.StudentList;

import java.util.List;


public class CheckExistedVisitor implements Visitor {
    ExistedStudentEnrollmentManager manager = ExistedStudentEnrollmentManager.getInstance();

    @Override
    public Pair visit(StudentList studentList, String studentId) {
        while (studentList.hasNext()) {
            Student currentStudent = studentList.next();
            if (currentStudent.getId().equals(studentId)) {
                Pair found = new Pair(true, studentList.getStudents().indexOf(currentStudent));
                studentList.reset();
                return found;
            }
        }
        System.out.println("Sorry! No student ID found!");
        Pair unfound = new Pair(false, null);
        return unfound;
    }

    @Override
    public Pair visit(List<Course> courseList, String courseId) {
        for (Course course : courseList){
            if (course.getId().equals(courseId)){
                Pair found = new Pair(true, courseList.indexOf(course));
                return found;
            }
        }
        System.out.println("Sorry! No course ID found");
        Pair unfound = new Pair(false, null);
        return unfound;
    }

    @Override
    public Pair visit(List<StudentEnrollment> studentEnrollmentList, StudentEnrollment toBeCompared) {

        for (StudentEnrollment enrollment : manager.getAllEnrollment()){
            if (toBeCompared.equals(enrollment)){
                Pair found = new Pair(true, manager.getAllEnrollment().indexOf(enrollment));
                return found;
            }
        }
        Pair unfound = new Pair(false, null);
        return unfound;

    }
}
