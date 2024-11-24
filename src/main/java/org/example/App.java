package org.example;

import java.util.List;

public class App {
    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader();
        List<Person> people = csvReader.readPeople();

        people.forEach(System.out::println);
    }
}