package lab3.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Student {

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @Min(17)
    @Max(25)
    private int age;

    @NotNull
    private Position position;

    @NotNull
    private boolean eu;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean getEu() {
        return eu;
    }

    public void setEu(boolean eu) {
        this.eu = eu;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


