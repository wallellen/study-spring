package edu.alvin.spring.xmlconf.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class InterceptorTarget {
    private final Logger logger = LoggerFactory.getLogger(InterceptorTarget.class);

    public String interceptorBefore(String str, Integer num) {
        logger.debug("'doBefore' method is running");
        return "test return value";
    }

    public String interceptorAfterReturning(String str, Integer num) {
        logger.debug("'doAfterReturning' method is running");
        return "test return value";
    }

    public String interceptorThrows(String str, Integer num) {
        logger.debug("'doThrows' method is running");
        throw new NullPointerException("Test exception");
    }

    public String interceptorAround(String str, Integer num) {
        logger.debug("'doAround' method is running");
        return "test return value";
    }

    public String interceptorAroundThrows(String str, Integer num) throws Exception {
        logger.debug("doAroundThrows method is running");
        throw new Exception("test exception");
    }
}
