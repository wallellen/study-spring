package edu.alvin.spring.person.services;

import edu.alvin.spring.person.models.Person;
import edu.alvin.spring.person.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Resource
    private PersonRepository personRepository;

    public Optional<Person> find(Integer id) {
        return personRepository.find(id);
    }

    public List<Person> findAll(int start, int count) {
        return personRepository.findAll(start, count);
    }

    public void save(Person person) {
        personRepository.save(person);
    }
}
