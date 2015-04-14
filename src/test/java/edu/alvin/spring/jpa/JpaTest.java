package edu.alvin.spring.jpa;

import edu.alvin.spring.jpa.confs.Configure;
import edu.alvin.spring.jpa.entities.TestPerson;
import edu.alvin.spring.jpa.services.PersonService;
import edu.alvin.util.JpaTestSupport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@ContextConfiguration(classes = {Configure.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaTest extends JpaTestSupport {
    private final Logger logger = LoggerFactory.getLogger(JpaTest.class);

    @Autowired
    private PersonService personService;

    @Before
    public void setUp() {
        clearDb("person");
    }

    @Test
    public void test_findPersonById() {
        TestPerson expected = withBuilder(TestPerson.Builder.class).create();
        Optional<TestPerson> optional = personService.find(expected.getId());
        assertThat(optional.isPresent(), is(true));

        TestPerson actual = optional.get();
        assertThat(expected.getId(), is(actual.getId()));
        logger.info("entity is {}", actual);
    }

    @Test
    public void test_findPersonByName() {
        TestPerson expected = withBuilder(TestPerson.Builder.class).create();
        List<TestPerson> persons = personService.findByName(expected.getName());
        assertThat(persons.size(), is(1));

        TestPerson actual = persons.get(0);
        assertThat(expected.getId(), is(actual.getId()));
        logger.info("entity is {}", actual);
    }

    @Test
    public void test_listPersons() {
        final int pageSize = 4;
        final long totalCount = 10;

        for (int i = 0; i < totalCount; i++) {
            withBuilder(TestPerson.Builder.class).create();
        }

        long count = personService.count();
        assertThat(count, is(totalCount));

        for (int i = 0; i < count / pageSize + 1; i++) {
            List<TestPerson> persons = personService.findAll(i + 1, pageSize);
            if (i < count / pageSize) {
                assertThat(persons.size(), is(pageSize));
            } else {
                assertThat((long)persons.size(), is(count % pageSize));
            }
        }
    }
}
