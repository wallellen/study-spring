package edu.alvin.spring.classconf.beans;

import java.time.LocalDate;

public class Person {
    private String name;
    private String gender;
    private LocalDate birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "{name=" + name + ",gender=" + gender + ",birthday=" + birthday + "}";
    }
}
