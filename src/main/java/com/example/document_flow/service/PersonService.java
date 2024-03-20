package com.example.document_flow.service;

import com.example.document_flow.entity.Department;
import com.example.document_flow.entity.Person;
import com.example.document_flow.repository.PersonRepository;
import com.example.document_flow.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person update(Person person) {
        Person dbPerson = personRepository.findById(CurrentUser.getInstance().getUser().getId()).get();
        dbPerson.setBirthdate(person.getBirthdate());
        dbPerson.setFirstname(person.getFirstname());
        dbPerson.setPosition(person.getPosition());
        dbPerson.setPatronymic(person.getPatronymic());
        dbPerson.setSecondname(person.getSecondname());

        dbPerson.setDepartment(new Department());

        CurrentUser.getInstance().getUser().setPerson(dbPerson);

        return personRepository.save(dbPerson);
    }


}
