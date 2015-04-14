package edu.alvin.spring.conf;

import edu.alvin.core.freemarker.funcs.I18nFunction;
import edu.alvin.core.freemarker.models.AssetModel;
import edu.alvin.core.freemarker.models.FlashMessageModel;
import edu.alvin.core.spring.resolvers.FlashScopeResolver;
import edu.alvin.core.spring.resolvers.I18nResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableAspectJAutoProxy
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {"edu.alvin"})
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {
    public static final String CONTENT_TYPE = "text/html;charset=UTF-8";
    public static final String ASSETS_PATH = "/assets/";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(ASSETS_PATH + "**").addResourceLocations(ASSETS_PATH);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(webApplicationContext.getBean(I18nResolver.class));
        argumentResolvers.add(webApplicationContext.getBean(FlashScopeResolver.class));
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("/resources/messages");
        return messageSource;
    }

    @Bean
    public ViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setOrder(0);
        resolver.setSuffix(".ftl.html");
        resolver.setContentType(CONTENT_TYPE);

        resolver.setExposeContextBeansAsAttributes(false);
        resolver.setExposeSessionAttributes(false);
        resolver.setExposeRequestAttributes(false);
        resolver.setExposeSpringMacroHelpers(true);
        return resolver;
    }

    @Bean
    public ViewResolver jspResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setOrder(1);
        resolver.setPrefix("/WEB-INF/views");
        resolver.setSuffix(".jsp");
        resolver.setContentType(CONTENT_TYPE);
        return resolver;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("default");
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        // Set the location where all template files is, relative to the webapp directory
        configurer.setTemplateLoaderPath("/WEB-INF/views/");
        // Set the charset of template output
        configurer.setDefaultEncoding("UTF-8");

        Map<String, Object> fmVars = new HashMap<>();
        AssetModel assetModel = webApplicationContext.getBean(AssetModel.class);
        assetModel.setAssetsPath(ASSETS_PATH);
        fmVars.put("asset", assetModel);
        fmVars.put("flash", webApplicationContext.getBean(FlashMessageModel.class));
        fmVars.put("i18n", webApplicationContext.getBean(I18nFunction.class));
        configurer.setFreemarkerVariables(fmVars);

        // Set other settings from properties
        Properties properties = new Properties();
        properties.setProperty("default_encoding", "UTF-8");
        properties.setProperty("locale", "zh_CN");
        properties.setProperty("number_format", "#.###");
        properties.setProperty("boolean_format", "true,false");
        properties.setProperty("date_format", "yyyy-M-d");
        properties.setProperty("time_format", "H:m");
        properties.setProperty("datetime_format", "yyyy-M-d H:m");
        properties.setProperty("time_zone", "GMT+8");
        properties.setProperty("template_update_delay", "0");
        properties.setProperty("classic_compatible", "true");
//      properties.setProperty("template_exception_handler", "ignore");
        configurer.setFreemarkerSettings(properties);
        return configurer;
    }
}
