package com.ynov.java.service;

import com.ynov.java.database.DatabaseClass;
import com.ynov.java.model.Employee;
import com.ynov.java.model.Project;

import java.util.Map;

/**
 * Created by Mathieu on 12/11/2015.
 */
public class ProjectService {
    private static Map<Long, Project> employeeMap = DatabaseClass.getProjects();

}
