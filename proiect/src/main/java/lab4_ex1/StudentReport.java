package main.java.lab4_ex1;

class StudentReport extends Report{
    private Student[] students;

    public StudentReport(Student[] students) {
        this.students = students;
    }

    @Override
    public void generate(Object[] args) {
        builder.append("Student Report\n");
        for (Student student : students) { // de modificat
            builder.append(student.studentName());
            builder.append(student.studentID());
            builder.append(student.grade());
        }

    }
}
