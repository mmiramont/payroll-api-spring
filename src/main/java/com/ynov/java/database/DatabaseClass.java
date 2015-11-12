package com.ynov.java.database;

import com.ynov.java.model.Employee;
import com.ynov.java.model.Project;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Mathieu on 12/11/2015.
 */
public class DatabaseClass {
    private static Map<Long, Employee> employeeMap = new ConcurrentHashMap<>();
    private static Map<Long, Project> projectMap = new ConcurrentHashMap<>();

    public static Map<Long, Employee> getMessages() {
        return employeeMap;
    }

    public static Map<Long, Project> getProjects() {
        return projectMap;
    }
}
