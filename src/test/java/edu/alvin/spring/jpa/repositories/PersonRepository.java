package edu.alvin.spring.jpa.repositories;

import edu.alvin.spring.jpa.entities.TestPerson;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager em;

    public Optional<TestPerson> find(Integer id) {
        TestPerson person = em.find(TestPerson.class, id);
        return person == null ? Optional.empty() : Optional.of(person);
    }

    public void save(TestPerson person) {
        em.persist(person);
    }

    public void saveOrUpdate(TestPerson person) {
        em.merge(person);
    }

    public void delete(TestPerson person) {
        em.remove(person);
    }

    public List<TestPerson> findByName(String name) {
        return em.createQuery("from TestPerson where name=:name", TestPerson.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<TestPerson> findAll(int start, int count) {
        return em.createQuery("from TestPerson", TestPerson.class)
                .setFirstResult(start)
                .setMaxResults(count)
                .getResultList();
    }

    public Long count() {
        return (Long) em.createQuery("select count(1) from Person").getSingleResult();
    }
}


