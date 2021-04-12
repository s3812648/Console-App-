package Enrollment;

public class NewEnrollment implements Command {
    StudentEnrollment enrollment;
    ExistedStudentEnrollmentManager manager = ExistedStudentEnrollmentManager.getInstance();

    public NewEnrollment(StudentEnrollment enrollment){
        this.enrollment = enrollment;
    }

    @Override
    public void execute(){
        manager.newStudentEnrollment(enrollment);
    }

    @Override
    public void undo(){
        manager.getAllEnrollment().remove(enrollment);
        System.out.println("Successfully Undone");
    }


}
