package edu.alvin.spring.classconf.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository {
    private Person person;

    @Autowired
    public TestRepository(Person person) {
        this.person = person;
    }

    public Person findPerson() {
        return person;
    }
}
