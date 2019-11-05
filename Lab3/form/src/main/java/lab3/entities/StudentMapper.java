package lab3.entities;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student s = new Student();
        s.setName(resultSet.getString("name"));
        s.setAge(resultSet.getInt("age"));
        s.setEu(resultSet.getBoolean("eu"));
        s.setPosition(Position.values()[resultSet.getInt("position")]);
        return s;
    }
}
