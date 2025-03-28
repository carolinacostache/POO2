package main.java.lab4_ex1;

public class SchoolSummaryReport extends Report {
    private Student[] students;
    private Classroom[] classrooms;
    private Schedule schedules;


    @Override
    public void generate(Object[] args) {
        builder.append("School Summary\n");
        for (Classroom classroom : classrooms ) { // de modificat
            builder.append(classroom.getClassNumber());
            for (Student student : students) {
                builder.append(student.studentName());
                builder.append(student.studentID());
                builder.append(student.grade());
            }
        }
    }
}
