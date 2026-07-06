import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamAPI {

    public static List<Student> filterByGradeAbove(List<Student> students, int limit) {
        return students.stream()
                .filter(s -> s.getGrade() > limit)
                .collect(Collectors.toList());
    }

    public static List<Student> sortByName(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }

    public static List<Student> sortByAge(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparingInt(Student::getAge))
                .collect(Collectors.toList());
    }

    public static double averageGrade(List<Student> students) {
        return students.stream()
                .mapToInt(Student::getGrade)
                .average()
                .orElse(0.0);
    }

    public static Map<Integer, List<Student>> groupByGrade(List<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(Student::getGrade));
    }

    public static String namesJoined(List<Student> students) {
        return students.stream()
                .map(Student::getName)
                .collect(Collectors.joining(", "));
    }
}
