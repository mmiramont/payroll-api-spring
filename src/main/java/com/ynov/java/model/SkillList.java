package com.ynov.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathieu on 13/11/2015.
 * Classe utilisée pour renvoyer les liste des skills dans un objet "skills"
 */
public class SkillList {
    public List<Skill> skills = new ArrayList<>();

    public SkillList(List<Skill> skills) {
        this.skills = skills;
    }
}
