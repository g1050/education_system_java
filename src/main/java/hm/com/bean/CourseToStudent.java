package hm.com.bean;

public class CourseToStudent {
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
}