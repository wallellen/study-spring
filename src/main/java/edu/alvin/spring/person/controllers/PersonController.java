package edu.alvin.spring.person.controllers;

import edu.alvin.core.hibernate.validators.Birthday;
import edu.alvin.core.hibernate.validators.Telephone;
import edu.alvin.core.spring.controllers.ActionSupport;
import edu.alvin.core.spring.resolvers.FlashScope;
import edu.alvin.core.spring.resolvers.UserArg;
import edu.alvin.spring.person.models.Person;
import edu.alvin.spring.person.services.PersonService;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
public class PersonController extends ActionSupport {

    @Resource
    private PersonService personService;

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("persons", personService.findAll(0, 100));
        return "/persons/index";
    }

    @ModelAttribute("person")
    public Person newPerson() {
        return new Person();
    }

    @RequestMapping(value = "/persons/add", method = RequestMethod.GET)
    public String add() {
        return "/persons/add";
    }

    @RequestMapping(value = "/persons/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model) {
        Person person = checkEntity(personService.find(id));
        model.addAttribute("person", person);
        return "/persons/edit";
    }

    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public String create(@Valid PersonForm person,
                         BindingResult errors,
                         Model model,
                         @UserArg FlashScope flashScope) {
        if (errors.hasFieldErrors()) {
            model.addAttribute("person", person);
            return "/persons/add";
        }
        personService.save(person.toModel());
        flashScope.success("persons.add.success");
        return "redirect:/persons/index";
    }

    @RequestMapping(value = "/persons", method = RequestMethod.PUT)
    public String update() {
        return "redirect:/persons/index";
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id) {
        return "redirect:/persons/index";
    }

    public static class PersonForm {
        private Integer id;
        @NotBlank
        private String name;
        @Pattern(regexp = "^M$|^F$")
        private String gender;
        @Birthday
        private String birthday;
        @Telephone
        private String telephone;
        @Email
        private String email;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
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

        public Person toModel() {
            final DateTimeFormatter pattern = DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneOffset.UTC);

            Person person = new Person();
            person.setName(name);
            person.setGender(gender);
            person.setTelephone(telephone);
            person.setEmail(email);
            person.setBirthday(LocalDateTime.parse(birthday, pattern));
            return person;
        }
    }
}
