package hm.com.bean;

public class CourseToStudent {
    private Course course;

    private Teacher teacher;

    private Integer id;

    private Integer studentId;

    private Integer courseToTeacherId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseToTeacherId() {
        return courseToTeacherId;
    }

    public void setCourseToTeacherId(Integer courseToTeacherId) {
        this.courseToTeacherId = courseToTeacherId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}