package edu.alvin.spring.annotation.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TestRepository {
    private Work work;

    @Autowired
    public TestRepository(@Qualifier("singletonWork") Work work) {
        this.work = work;
    }

    public Optional<Work> findWork() {
        return Optional.of(work);
    }
}
