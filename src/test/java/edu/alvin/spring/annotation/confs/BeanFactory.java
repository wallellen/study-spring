package edu.alvin.spring.annotation.confs;

import edu.alvin.spring.annotation.beans.Work;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class BeanFactory {
    private static final AtomicInteger NO_CREATOR = new AtomicInteger(0);

    @Value("2014-12-01T12:00:00+08:00")
    private LocalDateTime defaultBeginTime;

    @Value("2014-12-10T12:00:00+08:00")
    private LocalDateTime defaultFinishTime;

    @Bean(autowire = Autowire.BY_NAME)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Work prototypeWork() {
        Work work = new Work();
        work.setNo(NO_CREATOR.incrementAndGet());
        work.setBegin(defaultBeginTime);
        work.setFinish(defaultFinishTime);
        work.setName("Alvin");
        return work;
    }

    @Bean(autowire = Autowire.BY_NAME, name = "singletonWork")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Work singletonWork() {
        Work work = new Work();
        work.setNo(NO_CREATOR.incrementAndGet());
        work.setBegin(defaultBeginTime);
        work.setFinish(defaultFinishTime);
        work.setName("Alvin");
        return work;
    }
}
