package edu.alvin.spring.annotation.advices;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static edu.alvin.util.MethodHelper.methodName;

@Aspect
@Component
public class AspectAdvice {
    private static final Logger logger = LoggerFactory.getLogger(AspectAdvice.class);

    @Pointcut("execution(* edu.alvin.spring.annotation.beans.AspectTarget.*(..))")
    public void pointCut() {
    }

    @Before("AspectAdvice.pointCut()")
    public void before(JoinPoint jp) {
        String methodName = methodName(
                jp.getTarget().getClass(),
                jp.getSignature().getName(),
                jp.getArgs());
        logger.info(methodName + "[ASPECT-BEFORE]");
    }

    @After("AspectAdvice.pointCut()")
    public void after(JoinPoint jp) {
        String methodName = methodName(
                jp.getTarget().getClass(),
                jp.getSignature().getName(),
                jp.getArgs());
        logger.info(methodName + "[ASPECT-RETURN]");
    }

    @AfterThrowing(value = "AspectAdvice.pointCut()", throwing = "ex")
    public void except(JoinPoint jp, NullPointerException ex) {
        String methodName = methodName(
                jp.getTarget().getClass(),
                jp.getSignature().getName(),
                jp.getArgs());
        logger.info(methodName + "[ASPECT-THROWS, EX=" + ex + "]");
    }

    @Around("AspectAdvice.pointCut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        String methodName = methodName(
                jp.getTarget().getClass(),
                jp.getSignature().getName(),
                jp.getArgs());
        logger.info(methodName + "[ASPECT-AROUND BEFORE]");

        long time = System.currentTimeMillis();
        try {
            Object result = jp.proceed(jp.getArgs());
            logger.info(methodName + "[ASPECT-AROUND RETURN VAL=" + result + "]");
            return result;
        } finally {
            logger.info(methodName + "[ASPECT-AROUND FINISH, INTERVAL=" + (System.currentTimeMillis() - time) + "]");
        }
    }

    @Around("@annotation(edu.alvin.spring.annotation.advices.WithLog)")
    public Object aroundWithAnnotation(ProceedingJoinPoint jp) throws Throwable {
        String methodName = methodName(
                jp.getTarget().getClass(),
                jp.getSignature().getName(),
                jp.getArgs());
        logger.info(methodName + "[ASPECT-AROUND BEFORE]");

        long time = System.currentTimeMillis();
        try {
            Object result = jp.proceed(jp.getArgs());
            logger.info(methodName + "[ASPECT-AROUND RETURN VAL=" + result + "]");
            return result;
        } finally {
            logger.info(methodName + "[ASPECT-AROUND FINISH, INTERVAL=" + (System.currentTimeMillis() - time) + "]");
        }
    }
}
