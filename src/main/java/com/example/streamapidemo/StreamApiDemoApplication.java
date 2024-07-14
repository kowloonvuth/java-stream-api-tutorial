package com.example.streamapidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class StreamApiDemoApplication {

    static List<Employee> employees = new ArrayList<>();
    static {
        employees.add(
                new Employee("Prak", "Lungdy", 1000.0, List.of("Project 1", "Project 2")));
        employees.add(
                new Employee("Chan", "Danith", 4000.0, List.of("Project 1", "Project 3")));
        employees.add(
                new Employee("Soma", "Reach", 300.0, List.of("Project 3", "Project 4")));
        employees.add(
                new Employee("Han", "Yuth", 600.0, List.of("Project 4", "Project 5")));
    }

    public static void main(String[] args) {
        //SpringApplication.run(StreamApiDemoApplication.class, args);
        // foreach
//        employees.stream()
//                .forEach(employee -> System.out.println(employee));
        // map
        //collect

        Set<Employee> increasedSala =
                employees.stream()
                        .map(employee -> new Employee(
                                employee.getFirstName(),
                                employee.getLastName(),
                                employee.getSalary() * 1.10,
                                employee.getProjects()
                        ))
                        .collect(Collectors.toSet());
        System.out.println(increasedSala);

//        filter
//        findFirst

        List<Employee> filterEmployee =
                employees.stream()
                        .filter(employee -> employee.getSalary() > 600.0)
                        .map(employee -> new Employee(
                                employee.getFirstName(),
                                employee.getLastName(),
                                employee.getSalary() * 1.10,
                                employee.getProjects()
                        ))
                        .collect(Collectors.toList());

        System.out.println(filterEmployee);

        Employee firstEmployee =
                employees.stream()
                        .filter(employee -> employee.getSalary() > 1000.0)
                        .map(employee -> new Employee(
                                employee.getFirstName(),
                                employee.getLastName(),
                                employee.getSalary() * 1.10,
                                employee.getProjects()
                        ))
                        .findFirst()
                        .orElse(null);
        System.out.println(firstEmployee);

        // flatMap

        String projects = employees
                .stream()
                .map(employee -> employee.getProjects())
                .flatMap(strings -> strings.stream())
                .collect(Collectors.joining(","));
        System.out.println(projects);


        //short circuit operations
        List<Employee> shortCircuit =
                employees
                        .stream()
                        .skip(1)
                        .limit(2)
                        .collect(Collectors.toList());
        System.out.println(shortCircuit);

        //Finite Data
        Stream.generate(Math::random)
                .limit(4)
                .forEach(value -> System.out.println(value));

        //sorting
        List<Employee> sortedEmployees =
                employees
                        .stream()
                        .sorted(((o1, o2) -> o1.getFirstName()
                                .compareToIgnoreCase(o2.getFirstName())))
                        .collect(Collectors.toList());
        System.out.println(sortedEmployees);

        //min and max
        Employee MaxSala =
        employees
                .stream()
                .max((employee1, employee2) -> employee1.getSalary() > employee2.getSalary() ? 1: -1)
                .get();

        System.out.println(MaxSala.getSalary());

        //reduce
        Double totalSal =
                employees
                        .stream()
                        .map(employee -> employee.getSalary())
                        .reduce(0.0, Double::sum);
        System.out.println(totalSal);
    }

}
