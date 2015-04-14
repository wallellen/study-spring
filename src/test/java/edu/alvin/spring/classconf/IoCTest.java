package edu.alvin.spring.classconf;

import edu.alvin.spring.classconf.beans.Person;
import edu.alvin.spring.classconf.beans.TestService;
import edu.alvin.spring.classconf.confs.Configure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@ContextConfiguration(classes = {Configure.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class IoCTest {
    private final Logger logger = LoggerFactory.getLogger(IoCTest.class);

    @Autowired
    private TestService service;

    @Autowired
    private Person exceptPerson;

    @Test
    public void test_inject() {
        Person person = service.findPerson();
        assertThat(person, is(exceptPerson));
        logger.debug("person is {}", person);
    }
}
