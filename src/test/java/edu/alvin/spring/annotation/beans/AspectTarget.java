package edu.alvin.spring.annotation.beans;

import org.springframework.stereotype.Component;

@SuppressWarnings("ALL")
@Component
public class AspectTarget {

    public String aspectInvoke(String str, Integer num) {
        System.out.println("'aspectInvoke' method is running");
        return "test return value";
    }

    public String aspectThrows(String str, Integer num) {
        throw new NullPointerException();
    }
}
