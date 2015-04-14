package edu.alvin.core.spring.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class FlashScope {
    public static final String FLASH_SUCCESS = "__FLASH_SUCCESS";
    public static final String FLASH_ERROR = "__FLASH_ERROR";

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private I18n i18n;

    public void success(String key, Object...args) {
        FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
        flashMap.put(FLASH_SUCCESS, i18n.get(key, args));
    }

    public void error(String key, Object...args) {
        FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
        flashMap.put(FLASH_ERROR, i18n.get(key, args));
    }
}
