package lab3.services;

import lab3.entities.Student;
import lab3.entities.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DBServiceImpl implements CommandLineRunner, DBService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void saveStudent(Student student) {

        String SQL = "INSERT INTO students (name, age, position, eu)" +
                "VALUES(?,?,?,?)";
        jdbcTemplate.update(SQL, student.getName(), String.valueOf(student.getAge())
                , student.getPosition().ordinal(), student.getEu());
    }

    @Override
    public List<Student> getAllStudents() {

        List list = jdbcTemplate.query("SELECT * from students", new StudentMapper());
        return list;
    }

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("DROP TABLE students IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE students(id SERIAL, name VARCHAR(255), age INT, position INT, eu BOOL)");
    }
}
