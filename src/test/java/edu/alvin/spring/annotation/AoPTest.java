package edu.alvin.spring.annotation;

import edu.alvin.spring.annotation.beans.AspectTarget;
import edu.alvin.spring.annotation.beans.InterruptTarget;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "file:src/test/java/edu/alvin/spring/annotation/confs/context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AoPTest {

    @Autowired
    private AspectTarget aspectTarget;

    @Autowired
    private InterruptTarget interruptTarget;

    @Test
    public void test_aspectInvoke() throws Exception {
        aspectTarget.aspectInvoke("test", 0);
    }

    @Test(expected = NullPointerException.class)
    public void test_aspectThrows() throws Exception {
        aspectTarget.aspectThrows("test", 0);
    }

    @Test
    public void test_interrupt() {
        interruptTarget.aspectInvoke("test", 0);
    }
}