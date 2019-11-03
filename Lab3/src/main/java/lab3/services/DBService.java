package lab3.services;

import lab3.entities.Student;

import java.util.List;

public interface DBService {
    void saveStudent(Student student);

    List<Student> getAllStudents();
}
