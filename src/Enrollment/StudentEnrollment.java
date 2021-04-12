package Enrollment;

import Course.Course;
import Student.Student;

import java.util.Objects;

public class StudentEnrollment {
    private Student student;
    private Course course;
    private String sem;

    public StudentEnrollment(Student student, Course course, String sem) {
        this.student = student;
        this.course = course;
        this.sem = sem;
    }
    public String getStudentId(){
        return student.getId();
    }
    public String getCourseId(){
        return course.getId();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEnrollment that = (StudentEnrollment) o;
        return Objects.equals(student, that.student) &&
                Objects.equals(course, that.course) &&
                Objects.equals(sem, that.sem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course, sem);
    }

    @Override
    public String toString(){
        return "Enrolment {Student = [Student ID: " + student.getId() + ", Student name: " + student.getName() + ", Student birthdate: " + student.getBirthdate() +
                "]; Course = [Course ID: " + course.getId() + ", Course name: " + course.getName() + ", Course credits: " + course.getNumberOfCredits() + "]; Semester = " + sem + "}";
    }

}
