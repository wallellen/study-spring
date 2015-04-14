package edu.alvin.spring.person.repositories;

import edu.alvin.core.jpa.RepositorySupport;
import edu.alvin.spring.person.models.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository extends RepositorySupport<Person> {
    public List<Person> findAll(int start, int count) {
        return list("from Person", query->{
            query.setFirstResult(start);
            query.setMaxResults(count);
        });
    }
}
