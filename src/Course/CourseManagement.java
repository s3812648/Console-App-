package Course;

public class CourseManagement {
    public static Course newCourse(String id, String name, int NumberOfCredits) {
        return new Course(id, name, NumberOfCredits);
    }
}
