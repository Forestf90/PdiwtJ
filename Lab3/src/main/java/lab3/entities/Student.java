package lab3.entities;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Student {

    @NotNull
    @Size(min=2, max=30)
    private String name;

    @NotNull
    @Size(min=17,max=25)
    private Integer age;

    @NotNull
    private Position position;



    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}


enum Position{
    Student,
    Lecturer,
    Staff
}
