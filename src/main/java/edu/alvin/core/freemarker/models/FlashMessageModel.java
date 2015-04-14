package edu.alvin.core.freemarker.models;

import edu.alvin.core.spring.resolvers.FlashScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@Component
public class FlashMessageModel {
    @Autowired
    private HttpServletRequest request;

    public Object getSuccess() {
        return getFlashMap().get(FlashScope.FLASH_SUCCESS);
    }

    public Object getError() {
        return getFlashMap().get(FlashScope.FLASH_ERROR);
    }

    private Map<String, ?> getFlashMap() {
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        return flashMap == null ? Collections.emptyMap() : flashMap;
    }
}
