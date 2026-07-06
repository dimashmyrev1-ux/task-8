import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    private static final String TEXT_FILE = "students.txt";
    private static final String BINARY_FILE = "students.dat";
    private static final int GRADE_LIMIT = 3;

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();
        students.add(new Student("Иван Петров", 20, 4));
        students.add(new Student("Анна Смирнова", 21, 5));
        students.add(new Student("Олег Кузнецов", 19, 3));


        System.out.println("Текстовый файл");
        StudentFile.writeToTextFile(students, TEXT_FILE);
        List<Student> fromText = StudentFile.readFromTextFile(TEXT_FILE);
        fromText.forEach(System.out::println);
        System.out.println();


        System.out.println("Бинарный файл");
        StudentFile.writeToBinaryFile(students, BINARY_FILE);
        List<Student> fromBinary = StudentFile.readFromBinaryFile(BINARY_FILE);
        fromBinary.forEach(System.out::println);
        System.out.println();


        System.out.println("Загруженные студенты:");
        fromText.forEach(System.out::println);
        System.out.println();

        System.out.println("1) Студенты с оценкой выше " + GRADE_LIMIT + ":");
        StreamAPI.filterByGradeAbove(fromText, GRADE_LIMIT).forEach(System.out::println);
        System.out.println();

        System.out.println("2) Сортировка по имени:");
        StreamAPI.sortByName(fromText).forEach(System.out::println);
        System.out.println();

        System.out.println("2) Сортировка по возрасту:");
        StreamAPI.sortByAge(fromText).forEach(System.out::println);
        System.out.println();

        double average = StreamAPI.averageGrade(fromText);
        System.out.println("3) Средний балл группы: " + average);
        System.out.println();

        System.out.println("4) Группировка студентов по оценке:");
        Map<Integer, List<Student>> byGrade = StreamAPI.groupByGrade(fromText);
        byGrade.forEach((grade, group) -> {
            System.out.println("Оценка " + grade + ":");
            group.forEach(s -> System.out.println("  " + s));
        });
        System.out.println();

        String names = StreamAPI.namesJoined(fromText);
        System.out.println("5) Имена всех студентов: " + names);
    }
}
