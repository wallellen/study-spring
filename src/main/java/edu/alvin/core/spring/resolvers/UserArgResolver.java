package edu.alvin.core.spring.resolvers;

import org.springframework.core.MethodParameter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.lang.annotation.Annotation;

public abstract class UserArgResolver<T> implements HandlerMethodArgumentResolver {
    private Class<T> parameterType;

    protected UserArgResolver(Class<T> parameterType) {
        this.parameterType = parameterType;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        for (Annotation a : parameter.getParameterAnnotations()) {
            if (a.annotationType() == UserArg.class) {
                return parameter.getParameterType().isAssignableFrom(parameterType);
            }
        }
        return false;
    }
}
