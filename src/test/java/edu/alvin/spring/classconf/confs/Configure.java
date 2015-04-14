package edu.alvin.spring.classconf.confs;

import edu.alvin.core.spring.convs.LocalDateConverter;
import edu.alvin.core.spring.convs.LocalDateTimeConverter;
import edu.alvin.spring.classconf.beans.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Configuration
@ComponentScan(basePackages = {"edu.alvin.spring.classconf"})
@EnableAspectJAutoProxy
public class Configure {

    @Bean
    public ConversionService conversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        Set<Converter<?, ?>> converters = new HashSet<>();
        converters.add(new LocalDateTimeConverter());
        converters.add(new LocalDateConverter());
        bean.setConverters(converters);
        bean.afterPropertiesSet();
        return bean.getObject();
    }

    @Bean
    public Person personProvider(@Value("1981-03-17") LocalDate birthday) {
        Person person = new Person();
        person.setName("Alvin");
        person.setGender("M");
        person.setBirthday(birthday);
        return person;
    }
}
