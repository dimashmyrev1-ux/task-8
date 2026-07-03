import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class StudentStreamProcessor {

    private static final String TEXT_FILE = "students.txt";
    static int grade_limit = 3;

    public static void main(String[] args) {

        List<Student> students = readFromTextFile(TEXT_FILE);

        System.out.println("Загруженные студенты:");
        students.forEach(System.out::println);
        System.out.println();

        System.out.println("1) Студенты с оценкой выше " + grade_limit + ":");
        List<Student> filtered = filterByGradeAbove(students, grade_limit);
        filtered.forEach(System.out::println);
        System.out.println();


        System.out.println("2) Сортировка по имени:");
        sortByName(students).forEach(System.out::println);
        System.out.println();

        System.out.println("2) Сортировка по возрасту:");
        sortByAge(students).forEach(System.out::println);
        System.out.println();


        double average = averageGrade(students);
        System.out.println("3) Средний балл группы: " + average);
        System.out.println();


        System.out.println("4) Группировка студентов по оценке:");
        Map<Integer, List<Student>> byGrade = groupByGrade(students);
        byGrade.forEach((grade, group) -> {
            System.out.println("Оценка " + grade + ":");
            group.forEach(s -> System.out.println("  " + s));
        });
        System.out.println();


        String names = namesJoined(students);
        System.out.println("5) Имена всех студентов: " + names);
    }


    private static List<Student> filterByGradeAbove(List<Student> students, int limit) {
        return students.stream()
                .filter(s -> s.getGrade() > limit)
                .collect(Collectors.toList());
    }


    private static List<Student> sortByName(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }


    private static List<Student> sortByAge(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparingInt(Student::getAge))
                .collect(Collectors.toList());
    }


    private static double averageGrade(List<Student> students) {
        return students.stream()
                .mapToInt(Student::getGrade)
                .average()
                .orElse(0.0);
    }


    private static Map<Integer, List<Student>> groupByGrade(List<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(Student::getGrade));
    }


    private static String namesJoined(List<Student> students) {
        return students.stream()
                .map(Student::getName)
                .collect(Collectors.joining(", "));
    }


    private static List<Student> readFromTextFile(String fileName) {
        List<Student> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    result.add(Student.fromCsvLine(line));
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения текстового файла: " + e.getMessage());
        }
        return result;
    }
}
