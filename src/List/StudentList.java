package List;

import DuplicatedCheck.Visitable;
import DuplicatedCheck.Pair;
import DuplicatedCheck.Visitor;
import Student.Student;

import java.util.ArrayList;
import java.util.List;
public class StudentList implements Repeater, Visitable {
    List<Student> students = new ArrayList<>();
    int currentSelection = 0;
    String toBeCompared;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    public void setToBeCompared(String toBeCompared) {
        this.toBeCompared = toBeCompared;
    }
    @Override
    public void reset() {
        currentSelection = 0;
    }
    @Override
    public boolean hasNext() {
        if (currentSelection >= students.size()) {
            reset();
            return false;
        }
        return true;
    }

    @Override
    public Student next() {
        return students.get(currentSelection++);
    }

    @Override
    public Pair invite(Visitor visitor) {
        return visitor.visit(this, toBeCompared);
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }
}
