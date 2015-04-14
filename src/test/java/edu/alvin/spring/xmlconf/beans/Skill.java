package edu.alvin.spring.xmlconf.beans;

import java.io.Serializable;

@SuppressWarnings("all")
public class Skill implements Serializable {
    private String name;
    private Integer years;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }
}
