package edu.alvin.core.spring.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Component
public class I18n {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private WebApplicationContext webApplicationContext;

    public String get(String key, Object... args) {
        return webApplicationContext.getMessage(key, args, key, locale());
    }

    public Locale locale() {
        return RequestContextUtils.getLocale(request);
    }
}
