package Enrollment;

import DuplicatedCheck.Pair;
import DuplicatedCheck.Visitable;
import DuplicatedCheck.Visitor;

import java.util.ArrayList;
import java.util.List;

public class ExistedStudentEnrollmentManager implements EnrollmentManager, Visitable{
    private List<StudentEnrollment> studentEnrollmentList = new ArrayList<>();
    private StudentEnrollment toBeCompare;

    private static ExistedStudentEnrollmentManager instance = new ExistedStudentEnrollmentManager();

    private ExistedStudentEnrollmentManager(){}

    public static ExistedStudentEnrollmentManager getInstance(){
        return instance;
    }

    public void setToBeCompare(StudentEnrollment toBeCompare){
        this.toBeCompare = toBeCompare;
    }

    @Override
    public void newStudentEnrollment(StudentEnrollment newEnrollment){
        studentEnrollmentList.add(newEnrollment);
        System.out.println("\nYour Enrollment has been created\n" + newEnrollment.toString());
    }

    @Override
    public void updateStudentEnrollment(StudentEnrollment toBeUpdated, StudentEnrollment update) {
        toBeUpdated.setStudent(update.getStudent());
        toBeUpdated.setCourse(update.getCourse());
        toBeUpdated.setSem(update.getSem());
        System.out.println("Your Enrollment has been updated \n" + toBeUpdated.toString());
    }

    @Override
    public void deleteStudentEnrollment(StudentEnrollment enrollment) {
        studentEnrollmentList.remove(enrollment);
        System.out.println("\nYour Enrollment has been deleted\n");
    }

    @Override
    public List<StudentEnrollment> getAllEnrollment(){
        return this.studentEnrollmentList;
    }

    @Override
    public Pair invite(Visitor visitor){
        return visitor.visit(this.studentEnrollmentList, toBeCompare);
    }
}
