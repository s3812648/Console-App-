package Course;

import DuplicatedCheck.Pair;
import DuplicatedCheck.Visitor;
import DuplicatedCheck.Visitable;

import java.util.ArrayList;
import java.util.List;

public class Course implements Visitable {
    private String id;
    private String name;
    private int NumberOfCredits;

    private final List<Course> courseList = new ArrayList<>();
    private String toBeCompare;

    public Course(String id, String name, int numberOfCredits) {
        this.id = id;
        this.name = name;
        this.NumberOfCredits = numberOfCredits;
    }

    public Course(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfCredits() {
        return NumberOfCredits;
    }

    public void setNumberOfCredits(int NumberOfCredits) {
        this.NumberOfCredits = NumberOfCredits;
    }

    public List<Course> getCourseList(){
        return courseList;
    }

    public void addCourse(Course course){
        courseList.add(course);
    }

    public void setToBeCompared(String toBeCompared){
        this.toBeCompare = toBeCompare;
    }

    @Override
    public String toString(){
        return "Course [courseName = " + name + ", courseID = " + id + ", number_of_credits = " + NumberOfCredits + "]";
    }

    public void getAllCourse(){
        for (Course course : courseList) {
            System.out.println(course.toString());
        }
    }

    @Override
    public Pair invite(Visitor visitor) {
        return visitor.visit(this.courseList, toBeCompare);
    }

}
