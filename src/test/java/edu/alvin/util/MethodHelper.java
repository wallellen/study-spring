package edu.alvin.util;

import java.lang.reflect.Method;

public final class MethodHelper {

    private MethodHelper() {
    }

    public static String methodName(Class<?> targetClass, Method method, Object[] args) {
        return methodName(targetClass, method.getName(), args);
    }

    public static String methodName(Class<?> targetClass, String method, Object[] args) {
        StringBuilder builder = new StringBuilder();
        builder.append(targetClass.getSimpleName());
        builder.append(".");
        builder.append(method);
        builder.append("(");
        for (Object arg : args) {
            if (arg != args[0]) {
                builder.append(",");
            }
            builder.append(arg.getClass().getSimpleName());
        }
        builder.append(")");
        return builder.toString();
    }
}
