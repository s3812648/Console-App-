package Enrollment;

import java.util.List;

public interface EnrollmentManager {
    public List<StudentEnrollment> getAllEnrollment();
    public void newStudentEnrollment(StudentEnrollment newEnrollment);
    public void updateStudentEnrollment(StudentEnrollment toBeUpdate, StudentEnrollment update);
    public void deleteStudentEnrollment(StudentEnrollment enrollment);

}
