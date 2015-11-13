package com.ynov.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathieu on 12/11/2015.
 * Classe Project de l'application
 * Gère tous les Project créées
 */
public class Project {

    // Variables
    private int id;
    private String name;
    private List<Skill> requiredSkills = new ArrayList<Skill>();
    private List<Employee> team = new ArrayList<Employee>();

    // Pour rajouter un employée à un projet via son Id (JSON)
    private int employeeId;

    // Constructeur
    public Project() {
    }

    public Project(String name, List<Skill> requiredSkills) {
        this.name = name;
        this.requiredSkills = requiredSkills;
    }

    // Getters & Setters
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
        this.setTeam(team);
    }

    public List<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public SkillList getSkillList(){
        return new SkillList(requiredSkills);
    }

    public void setRequiredSkills(List<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Ajoute l'Employee passé en paramètre au projet
     * @param employee L'Employee à ajouter au projet
     */
    public void addEmployeeToTeam(Employee employee){
        this.team.add(employee);
    }
}
