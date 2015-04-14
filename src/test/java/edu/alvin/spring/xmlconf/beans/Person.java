package edu.alvin.spring.xmlconf.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Person implements Serializable {
    private Integer id;
    private String name;
    private Character gender;
    private Integer age;
    private Map<String, Person> parents;
    private List<Skill> skills;

    public Person() {
    }

    public Person(Integer id, String name, Character gender, Integer age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public static Person of(Integer id, String name, Character gender, Integer age) {
        return new Person(id, name, gender, age);
    }

    @Override
    public String toString() {
        return String.format("{id:%d,name:%s,gender:%c,age:%d,parents:%s,skills:%s}",
                id, name, gender, age, parents, skills);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Map<String, Person> getParents() {
        return parents;
    }

    public void setParents(Map<String, Person> parents) {
        this.parents = parents;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
