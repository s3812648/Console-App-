package Enrollment;

import Course.Course;
import Student.Student;

public class UpdateEnrollment implements Command {

    StudentEnrollment update;
    ExistedStudentEnrollmentManager manager = ExistedStudentEnrollmentManager.getInstance();
    Student oldStudent;
    Course oldCourse;
    String oldSem;
    int indexOfUpdate;

    public UpdateEnrollment(StudentEnrollment enrollment, int index) {
        this.update = enrollment;
        this.indexOfUpdate = index;
        this.oldStudent = manager.getAllEnrollment().get(indexOfUpdate).getStudent();
        this.oldCourse = manager.getAllEnrollment().get(indexOfUpdate).getCourse();
        this.oldSem = manager.getAllEnrollment().get(indexOfUpdate).getSem();
    }

    @Override
    public void execute() {
        StudentEnrollment toBeUpdated = manager.getAllEnrollment().get(indexOfUpdate);
        manager.updateStudentEnrollment(toBeUpdated, update);
    }

    @Override
    public void undo() {
        StudentEnrollment toBeReverted = manager.getAllEnrollment().get(indexOfUpdate);
        //Revert info to old info
        toBeReverted.setStudent(this.oldStudent);
        toBeReverted.setCourse(this.oldCourse);
        toBeReverted.setSem(this.oldSem);
        System.out.println("Successfully Undone");
    }

}
