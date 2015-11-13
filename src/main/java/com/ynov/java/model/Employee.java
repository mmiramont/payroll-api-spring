package com.ynov.java.model;

import java.util.List;

/**
 * Created by Mathieu on 12/11/2015.
 * Classe Employee de l'application
 * Gère tous les Employee créées
 */
public class Employee {
    // Variables
    private int id;
    private String name;
    private List<Skill> skills;
    private long salary;
    public static int SMIC = 1140;

    // Constructeurs
    public Employee() {
    }

    public Employee(String name, List<Skill> skills, long salary) {
        this.name = name;
        this.skills = skills;
        this.salary = salary;
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

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void addCompetence(Skill skill){
        this.skills.add(skill);
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
}
