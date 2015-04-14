package edu.alvin.spring.annotation;

import edu.alvin.spring.annotation.beans.TestService;
import edu.alvin.spring.annotation.beans.Work;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = "file:src/test/java/edu/alvin/spring/annotation/confs/context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IoCTest {
    private final Logger logger = LoggerFactory.getLogger(IoCTest.class);

    @Autowired
    @Qualifier("prototypeWork")
    private Work work1, work2;

    @Autowired
    @Qualifier("singletonWork")
    private Work work3, work4;

    @Autowired
    private TestService service;

    @Test
    public void test_beanFactoryPrototype() {
        assertThat(work1, not(work2));
        assertThat(work2.getNo(), is(work1.getNo() + 1));

        logger.info("work1 is {}", work1);
        logger.info("work2 is {}", work2);
    }

    @Test
    public void test_beanFactorySingleton() {
        assertThat(work3, is(work4));
        logger.info("work3 is {}", work3);
        logger.info("work4 is {}", work4);
    }

    @Test
    public void test_autowired() {
        Optional<Work> workOptional = service.findWork();
        assertThat(workOptional.isPresent(), is(true));
        assertThat(workOptional.get(), is(work3));
        logger.info("result is {}", workOptional.get());
    }
}