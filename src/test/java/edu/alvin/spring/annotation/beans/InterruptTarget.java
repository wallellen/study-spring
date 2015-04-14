package edu.alvin.spring.annotation.beans;

import edu.alvin.spring.annotation.advices.WithLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@SuppressWarnings("all")
@Component
public class InterruptTarget {
    private final Logger logger = LoggerFactory.getLogger(InterruptTarget.class);

    @WithLog
    public String aspectInvoke(String str, Integer num) {
        logger.debug("aspectInvoke method is running");
        return "test return value";
    }
}
