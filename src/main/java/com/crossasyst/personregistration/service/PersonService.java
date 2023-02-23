package com.crossasyst.personregistration.service;

import com.crossasyst.personregistration.entity.PersonEntity;
import com.crossasyst.personregistration.mapper.PersonMapper;
import com.crossasyst.personregistration.model.Person;
import com.crossasyst.personregistration.repository.PersonRepository;
import com.crossasyst.personregistration.response.PersonResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class PersonService {

    public final PersonMapper personMapper;

    public final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonMapper personMapper, PersonRepository personRepository) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
    }

    public PersonResponse createPerson(Person person) {
        log.info("Adding Person Details");
        PersonEntity personEntity = personMapper.modelToEntity((person));
        personRepository.save(personEntity);
        log.info("Patient Details Saved Successfully.");

        PersonResponse personResponse = new PersonResponse();
        personResponse.setId(personEntity.getPersonId());
        log.info("Your Person Id is {}", personResponse.getId());
        return personResponse;
    }

   /* public Person getPersonById(Long personId) {
        log.info("Searching Details");

        Optional<PersonEntity> personEntityOptional = personRepository.findById(personId);
        Person person = new Person();
        if (personEntityOptional.isPresent()) {
            person = personMapper.entityToModel(personEntityOptional.get());
            log.info("Found Person");
        } else {
            log.info("Patient Details Not Fond");
        }
        return person;
    }*/

    public Person getPersonById(Long personId) {
        log.info("Searching Details");
        Optional<PersonEntity> personEntityOptional = personRepository.findById(personId);
        Person person = new Person();
        if (personEntityOptional.isPresent()) {
            person = personMapper.entityToModel(personEntityOptional.get());
            log.info("Found Person");
        } else {
            log.info("Details not found");
        }
        return person;
    }
}
