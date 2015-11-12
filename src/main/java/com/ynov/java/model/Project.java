package com.ynov.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathieu on 12/11/2015.
 */
public class Project {

    private int id;
    private String name;
    private List<Skill> requiredSkills = new ArrayList<Skill>();
    private List<Employee> team = new ArrayList<Employee>();
    // Pour rajouter un employée à un projet via son Id (JSON)
    private int employeeId;

    public Project() {
    }

    public Project(String name, List<Skill> requiredSkills) {
        this.name = name;
        this.requiredSkills = requiredSkills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getTeam() {
        return team;
    }

    public void setTeam(List<Employee> team) {
        this.team = team;
    }

    public List<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void addEmployeeToTeam(Employee employee){
        this.team.add(employee);
    }
}
