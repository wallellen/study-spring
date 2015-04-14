package edu.alvin.spring.xmlconf.advices;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static edu.alvin.util.MethodHelper.methodName;

@SuppressWarnings("all")
public class AspectAdvice {
    private static final Logger logger = LoggerFactory.getLogger(AspectAdvice.class);

    public void before(JoinPoint jp) {
        String methodName = methodName(
                jp.getTarget().getClass(),
                jp.getSignature().getName(),
                jp.getArgs());
        logger.info(methodName + "[ASPECT-BEFORE]");
    }

    public void after(JoinPoint jp) {
        String methodName = methodName(
                jp.getTarget().getClass(),
                jp.getSignature().getName(),
                jp.getArgs());
        logger.info(methodName + "[ASPECT-RETURN]");
    }

    public void except(JoinPoint jp, NullPointerException ex) {
        String methodName = methodName(
                jp.getTarget().getClass(),
                jp.getSignature().getName(),
                jp.getArgs());
        logger.info(methodName + "[ASPECT-THROWS, EX=" + ex + "]");
    }

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
}
