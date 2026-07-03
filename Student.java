import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {

    private String name;
    private int age;
    private int grade;

    public Student(String name, int age, int grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    //--------------- Геттеры-----------------------
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getGrade() {
        return grade;
    }

    //--------------- Сеттеры-----------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    //--------------------------------------------------

    public String toString() {
        return "Student{" + "name='" + name + "', age=" + age + ", grade=" + grade + '}';
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && grade == student.grade && Objects.equals(name, student.name);
    }


    public int hashCode() {
        return Objects.hash(name, age, grade);
    }


    public String toCsvLine() {
        return name + ";" + age + ";" + grade;
    }

    public static Student fromCsvLine(String line) {
        String[] parts = line.split(";");
        String name = parts[0];
        int age = Integer.parseInt(parts[1]);
        int grade = Integer.parseInt(parts[2]);
        return new Student(name, age, grade);
    }
}
