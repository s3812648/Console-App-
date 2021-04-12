package Print;

import Enrollment.ExistedStudentEnrollmentManager;
import Enrollment.StudentEnrollment;
import Utils.Utils;

public class CourseEnrollment implements Chain {
    private Chain nextInChain;
    ExistedStudentEnrollmentManager manager = ExistedStudentEnrollmentManager.getInstance();
    private Utils utils = new Utils();

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextInChain = nextChain;
    }

    @Override
    public void print(Request request) {
        if (request.getPrintType().equals("print course")) {
            int count = 0;
            System.out.println("Input course: ");
            String id = utils.getInput();
            for (StudentEnrollment se : manager.getAllEnrollment()) {
                if (se.getCourseId().equals(id)) {
                    System.out.println(se.toString());
                    count +=1;
                }
            }
            System.out.println("There are (" + count + ") enrollments with the course id " + id);
        } else {
            System.out.println("End");
        }

    }

}
