package Print;

import Enrollment.ExistedStudentEnrollmentManager;
import Enrollment.StudentEnrollment;

public class AllEnrollment implements Chain {

    private Chain nextInChain;
    ExistedStudentEnrollmentManager manager = ExistedStudentEnrollmentManager.getInstance();

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void print(Request request) {
        if (request.getPrintType().equals("print all")) {
            System.out.println("All available enrollments:");
            for(StudentEnrollment se : manager.getAllEnrollment()) {
                System.out.println(se.toString());
            }
        } else {
            nextInChain.print(request);
        }
    }
}
