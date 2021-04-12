package DuplicatedCheck;

import Student.Student;
import Course.Course;
import Enrollment.StudentEnrollment;
import List.StudentList;


import java.util.List;

public interface Visitor {
    public Pair visit(StudentList studentList, String studentId);
    public Pair visit(List<Course> courseList, String courseId);
    public Pair visit(List<StudentEnrollment> studentEnrollmentsList, StudentEnrollment enrollment);
}
