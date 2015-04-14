package edu.alvin.spring.xmlconf.advices;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

import static edu.alvin.util.MethodHelper.methodName;

@SuppressWarnings("all")
public class InterceptorAdvice implements
        MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice, MethodInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(InterceptorAdvice.class);

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        logger.info(methodName(target.getClass(), method, args) + "[BEFORE]");
    }

    @Override
    public void afterReturning(Object returnValue,
                               Method method,
                               Object[] args,
                               Object target) throws Throwable {
        logger.info(methodName(target.getClass(), method, args)
                + "[INTERCEPTOR-RETURNING, VAL="
                + returnValue
                + "]");
    }

    public void afterThrowing(Method method, Object[] args, Object target, NullPointerException ex) {
        logger.info(methodName(target.getClass(), method, args)
                + "[INTERCEPTOR-THROWING, EX=" + ex + "]");
    }

    @Override
    public Object invoke(MethodInvocation in) throws Throwable {
        String methodName = methodName(
                in.getThis().getClass(),
                in.getMethod(),
                in.getArguments());

        logger.info(methodName + "[INTERCEPTOR-AROUND BEFORE]");
        Object result = null;
        try {
            result = in.getMethod().invoke(in.getThis(), in.getArguments());
            logger.info(methodName + "[INTERCEPTOR-AROUND RETURNING, VAL=" + result + "]");
        } catch (Exception e) {
            logger.error(methodName + "[INTERCEPTOR-AROUND THROWING, EX=" + e + "]");
        }
        return result;
    }
}
