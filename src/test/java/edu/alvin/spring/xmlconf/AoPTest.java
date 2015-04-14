package edu.alvin.spring.xmlconf;

import edu.alvin.spring.xmlconf.beans.AspectTarget;
import edu.alvin.spring.xmlconf.beans.InterceptorTarget;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "file:src/test/java/edu/alvin/spring/xmlconf/confs/context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AoPTest {

    @Autowired
    private ApplicationContext ac;

    @Test
    public void test_doBefore() {
        InterceptorTarget target = ac.getBean(InterceptorTarget.class);
        target.interceptorBefore("test", 0);
    }

    @Test
    public void test_afterReturning() {
        InterceptorTarget target = ac.getBean(InterceptorTarget.class);
        target.interceptorAfterReturning("test", 0);
    }

    @Test
    public void test_doThrows() {
        InterceptorTarget target = ac.getBean(InterceptorTarget.class);
        target.interceptorThrows("test", 0);
    }

    @Test
    public void test_doAround() {
        InterceptorTarget target = ac.getBean(InterceptorTarget.class);
        target.interceptorAround("test", 0);
    }

    @Test
    public void test_doAroundThrows() throws Exception {
        InterceptorTarget target = ac.getBean(InterceptorTarget.class);
        target.interceptorAroundThrows("test", 0);
    }

    @Test
    public void test_aspectInvoke() throws Exception {
        AspectTarget target = ac.getBean(AspectTarget.class);
        target.aspectInvoke("test", 0);
    }

    @Test(expected = NullPointerException.class)
    public void test_aspectThrows() throws Exception {
        AspectTarget target = ac.getBean(AspectTarget.class);
        target.aspectThrows("test", 0);
    }
}