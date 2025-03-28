package main.java.lab4_ex1;
import java.util.*;

public class School {
    private Student[] students;
    private Classroom[] classrooms;
    private Schedule schedules;

    public School(Student[] students) {

        classrooms = new Classroom[2];
        classrooms[0] = new Classroom(1,25);
        classrooms[1] = new Classroom(2,25);


        this.schedules = new Schedule(schedules.getSubjects());

    }
}
