package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CsvReader {
    private static final String CSV_FILE = "data.csv"; // Файл должен быть в src/main/resources
    private static final char SEPARATOR = ';';

    public List<Person> readPeople() {
        List<Person> people = new ArrayList<>();
        Map<String, Department> departmentMap = new HashMap<>();

        try (InputStream in = getClass().getClassLoader().getResourceAsStream(CSV_FILE);
             CSVReader reader = new CSVReader(new InputStreamReader(in), SEPARATOR)) {

            List<String[]> lines = reader.readAll();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (String[] line : lines) {
                int id = Integer.parseInt(line[0]);
                String name = line[1];
                String gender = line[2];
                String departmentName = line[3];
                double salary = Double.parseDouble(line[4]);
                LocalDate birthDate = LocalDate.parse(line[5], formatter);

                // Создаем или получаем существующее подразделение
                Department department = departmentMap.computeIfAbsent(departmentName, Department::new);

                // Создаем объект Person
                Person person = new Person(id, name, gender, department, salary, birthDate);
                people.add(person);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return people;
    }
}
