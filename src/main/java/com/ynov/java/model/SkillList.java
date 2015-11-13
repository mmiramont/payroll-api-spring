package com.ynov.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathieu on 13/11/2015.
 */
public class SkillList {
    public List<Skill> skills = new ArrayList<>();

    public SkillList(List<Skill> skills) {
        this.skills = skills;
    }

    public SkillList() {

    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void add(Skill skill) {
        skills.add(skill);
    }
}
