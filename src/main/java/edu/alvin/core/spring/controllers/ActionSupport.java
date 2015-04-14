package edu.alvin.core.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ActionSupport {

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpSession session;

    protected ServletContext servletContext() {
        return webApplicationContext.getServletContext();
    }

    protected <T> T checkEntity(Optional<T> optional) {
        if (!optional.isPresent()) {
            throw new HttpMessageNotReadableException("Entity not found");
        }
        return optional.get();
    }
}
