package br.com.sucram.person.controller;

import br.com.sucram.person.dto.request.PersonDTO;
import br.com.sucram.person.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")

public class PersonController {

    private PersonService personService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.save(personDTO);
    }


    @PutMapping("/{id}")
    public PersonDTO updatePerson(@PathVariable Long  id, @RequestBody @Valid PersonDTO personDTO){
        return personService.updateById(id, personDTO);
    }

    @GetMapping
    public List<PersonDTO> listAll() {
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable  Long id) {
        return personService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable  Long id) {
        personService.delete(id);
    }



}
