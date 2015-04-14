package edu.alvin.spring.xmlconf;

import edu.alvin.spring.xmlconf.beans.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = "file:src/test/java/edu/alvin/spring/xmlconf/confs/context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IoCTest {
    private final Logger logger = LoggerFactory.getLogger(IoCTest.class);

    @Test
    public void test_withContextConfigFile() {
        ApplicationContext ac = new FileSystemXmlApplicationContext(
                "file:src/test/java/edu/alvin/spring/xmlconf/confs/context.xml");

        Person person = ac.getBean("person", Person.class);
        assertThat(person, is(notNullValue()));
        logger.debug("Get user instance {}", person);

        assertThat(person.getParents(), is(notNullValue()));
        assertThat(person.getParents().size(), greaterThan(0));
        logger.debug("User parent {}", person.getParents());

        assertThat(person.getSkills(), is(notNullValue()));
        assertThat(person.getSkills().size(), greaterThan(0));
        logger.debug("User skills {}", person.getSkills());
    }

    @Autowired
    private ApplicationContext ac;

    @Test
    public void test_withInjectedContext() {
        Person person = ac.getBean("person", Person.class);
        assertThat(person, is(notNullValue()));
        logger.debug("Get user instance {}", person);

        assertThat(person.getParents(), is(notNullValue()));
        assertThat(person.getParents().size(), greaterThan(0));
        logger.debug("User parent {}", person.getParents());

        assertThat(person.getSkills(), is(notNullValue()));
        assertThat(person.getSkills().size(), greaterThan(0));
        logger.debug("User skills {}", person.getSkills());
    }
}