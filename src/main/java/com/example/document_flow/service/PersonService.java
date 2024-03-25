package com.example.document_flow.service;

import com.example.document_flow.entity.Person;
import com.example.document_flow.repository.DepartmentRepository;
import com.example.document_flow.repository.PersonRepository;
import com.example.document_flow.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Person update(Person person) {
        Person dbPerson = personRepository.findById(CurrentUser.getInstance().getUser().getId()).get();
        dbPerson.setBirthdate(person.getBirthdate());
        dbPerson.setFirstname(person.getFirstname());
        dbPerson.setPosition(person.getPosition());
        dbPerson.setPatronymic(person.getPatronymic());
        dbPerson.setSecondname(person.getSecondname());

        CurrentUser.getInstance().getUser().setPerson(dbPerson);

        return personRepository.save(dbPerson);
    }


    public Person changeDepartment(Long id) {
        CurrentUser.getInstance().getUser().getPerson().setDepartment(departmentRepository.findById(id).get());
        return personRepository.save(CurrentUser.getInstance().getUser().getPerson());
    }
}
