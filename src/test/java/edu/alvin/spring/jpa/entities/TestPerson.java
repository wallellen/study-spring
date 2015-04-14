package edu.alvin.spring.jpa.entities;

import edu.alvin.core.spring.convs.LocalDateTimeConverter;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "person")
public class TestPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String gender;
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime birthday;
    private String telephone;
    private String email;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAge() {
        return birthday.until(LocalDateTime.now(ZoneOffset.UTC), ChronoUnit.YEARS);
    }

    @Override
    public String toString() {
        return "{id=" + id + ",name=" + name + ",gender=" + gender + ",birthday=" +
                birthday + ",telephone=" + telephone + ",email=" + email + "}";
    }

    @Component
    public static class Builder extends edu.alvin.util.Builder<TestPerson> {
        private String name = "Alvin";
        private String gender = "M";
        private LocalDateTime birthday = LocalDateTime.of(1981, 3, 17, 0, 0, 0);
        private String telephone = "13999999999";
        private String email = "alvin@test.com";

        @Override
        public TestPerson build() {
            TestPerson person = new TestPerson();
            person.setName(name);
            person.setGender(gender);
            person.setBirthday(birthday);
            person.setTelephone(telephone);
            person.setEmail(email);
            return person;
        }

        @Override
        @Transactional
        public TestPerson create() {
            TestPerson person = build();
            getEntityManager().persist(person);
            return person;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder birthday(LocalDateTime birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }
    }
}
