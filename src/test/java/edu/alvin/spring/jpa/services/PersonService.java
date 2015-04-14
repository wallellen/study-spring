package edu.alvin.spring.jpa.services;

import edu.alvin.spring.jpa.entities.TestPerson;
import edu.alvin.spring.jpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<TestPerson> find(Integer id) {
        return personRepository.find(id);
    }

    @Transactional
    public void save(TestPerson person) {
        personRepository.save(person);
    }

    @Transactional
    public void saveOrUpdate(TestPerson person) {
        personRepository.saveOrUpdate(person);
    }

    @Transactional
    public void delete(TestPerson person) {
        personRepository.delete(person);
    }

    public List<TestPerson> findByName(String name) {
        return personRepository.findByName(name);
    }

    public List<TestPerson> findAll(int pageIndex, int count) {
        return personRepository.findAll((pageIndex - 1) * count, count);
    }

    public Long count() {
        return personRepository.count();
    }
}
