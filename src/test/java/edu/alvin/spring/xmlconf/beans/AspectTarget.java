package edu.alvin.spring.xmlconf.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class AspectTarget {
    private final Logger logger = LoggerFactory.getLogger(AspectTarget.class);

    public String aspectInvoke(String str, Integer num) {
        logger.debug("'aspectInvoke' method is running");
        return "test return value";
    }

    public String aspectThrows(String str, Integer num) {
        throw new NullPointerException();
    }
}
