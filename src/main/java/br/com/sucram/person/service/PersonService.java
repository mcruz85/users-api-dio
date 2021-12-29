package br.com.sucram.person.service;

import br.com.sucram.person.dto.request.PersonDTO;
import br.com.sucram.person.entity.Person;
import br.com.sucram.person.exception.PersonNotFoundException;
import br.com.sucram.person.mapper.PersonMapper;
import br.com.sucram.person.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;


    public PersonDTO save(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return personMapper.toDTO(savedPerson);
    }


    public List<PersonDTO> listAll() {
        return personRepository.findAll()
                .stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) {
        Person person = verifiIfExists(id);
        return personMapper.toDTO(person);

    }

    public void delete(Long id) {
        Person person = verifiIfExists(id);
        personRepository.delete(person);
    }

    private Person verifiIfExists(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    public PersonDTO updateById(Long id, PersonDTO personDTO) {
        verifiIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        return  personMapper.toDTO(personRepository.save(personToUpdate));
    }
}
