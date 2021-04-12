package Enrollment;

public class DeleteEnrollment implements Command {

    StudentEnrollment toBeDelete;
    ExistedStudentEnrollmentManager manager = ExistedStudentEnrollmentManager.getInstance();

    public DeleteEnrollment(StudentEnrollment enrollment){
        this.toBeDelete = enrollment;
    }

    @Override
    public void execute(){
        manager.deleteStudentEnrollment(toBeDelete);
    }

    @Override
    public void undo(){
        manager.newStudentEnrollment(toBeDelete);
        System.out.println("Successfully Undone");
    }
}
