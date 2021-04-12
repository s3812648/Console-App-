package Print;

import Enrollment.ExistedStudentEnrollmentManager;
import Enrollment.StudentEnrollment;
import Utils.Utils;

public class SemEnrollment implements Chain {
    private Chain nextInChain;
    ExistedStudentEnrollmentManager manager = ExistedStudentEnrollmentManager.getInstance();
    private Utils utils = new Utils();

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void print(Request request) {
        if (request.getPrintType().equals("print semester")) {
            int count = 0;
            System.out.println("Input semester: ");
            String semester = utils.getInput();
            for(StudentEnrollment se : manager.getAllEnrollment()) {
                if (se.getSem().equals(semester)) {
                    System.out.println(se.toString());
                    count+=1;
                }
            }
            System.out.println("Found (" + count + ") enrollments with the provided semester");
        } else {
            nextInChain.print(request);
        }
    }
}
