import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StudentToFile {

    private static final String TEXT_FILE = "students.txt";
    private static final String BINARY_FILE = "students.dat";

    public static void main(String[] args) {


        List<Student> students = new ArrayList<>();
        students.add(new Student("Иван Петров", 20, 4));
        students.add(new Student("Анна Смирнова", 21, 5));
        students.add(new Student("Олег Кузнецов", 19, 3));

        System.out.println("Текстовый файл");
        writeToTextFile(students, TEXT_FILE);
        List<Student> fromText = readFromTextFile(TEXT_FILE);
        fromText.forEach(System.out::println);

        System.out.println();
        System.out.println("Бинарный файл");
        writeToBinaryFile(students, BINARY_FILE);
        List<Student> fromBinary = readFromBinaryFile(BINARY_FILE);
        fromBinary.forEach(System.out::println);
    }



    private static void writeToTextFile(List<Student> students, String fileName) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student s : students) {
                writer.write(s.toCsvLine());
                writer.newLine();
            }
            System.out.println("Данные успешно записаны в " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка записи в текстовый файл: " + e.getMessage());
        }
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



    private static void writeToBinaryFile(List<Student> students, String fileName) {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            out.writeObject(students);
            System.out.println("Данные успешно записаны в " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка записи в бинарный файл: " + e.getMessage());
        }
    }

    private static List<Student> readFromBinaryFile(String fileName) {
        try (ObjectInputStream in =
                     new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            return (List<Student>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка чтения бинарного файла: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
