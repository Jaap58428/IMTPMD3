package nl.itsjaap.week3.models;

public class CourseModel {

    private String name;
    private String ects;
    private String grade;
    private String period;

    public CourseModel(String n, String e, String g, String p) {
        this.name = n;
        this.ects = e;
        this.grade = g;
        this.period = p;
    }

    public String getName(){
        return name;
    }

    public String getEcts(){
        return String.valueOf(ects);
    }

    public String getGrade() {
        return grade;
    }

    public String getPeriod() {
        return period;
    }
}
