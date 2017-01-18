package exceptionAndSocket;

import java.io.Serializable;

public class Teacher implements Serializable {
    private String name;
    private int age;
    private int salary;
    private Grade grade;


    public Teacher(String name, int age, int salary, Grade grade) {
        super();
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.grade = grade;
    }

    public String getName() {
        return name;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Teacher))
            return false;
        Teacher teacher = (Teacher) obj;
        return teacher.name == this.name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", grade=" + grade +
                '}';
    }
}
