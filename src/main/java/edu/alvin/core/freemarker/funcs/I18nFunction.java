package edu.alvin.core.freemarker.funcs;

import edu.alvin.core.spring.resolvers.I18n;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class I18nFunction implements TemplateMethodModelEx {

    @Autowired
    private I18n i18n;

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        SimpleScalar arg0 = (SimpleScalar) arguments.get(0);
        if (arguments.size() > 1) {
            Object[] args = new Object[arguments.size() - 1];
            for (int i = 1; i < arguments.size(); i++) {
                args[i - 1] = ((SimpleScalar) arguments.get(i)).getAsString();
            }
            return i18n.get(arg0.getAsString(), args);
        } else {
            return i18n.get(arg0.getAsString());
        }
    }
}
