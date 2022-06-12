package com.folcademy.Api.Services;

import com.folcademy.Api.Exceptions.ContactoExistenteException;
import com.folcademy.Api.Exceptions.ContactoInexistenteException;
import com.folcademy.Api.Model.Domain.AdressDTO;
import com.folcademy.Api.Model.Domain.PersonDTO;
import com.folcademy.Api.Model.Entities.Person;
import com.folcademy.Api.Model.Mappers.AdressMapper;
import com.folcademy.Api.Model.Mappers.PersonMapper;
import com.folcademy.Api.Model.Repositories.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "personService")
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final AdressMapper adressMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper, AdressMapper adressMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.adressMapper = adressMapper;
    }

    public List<PersonDTO> findAll() {
        return personRepository.findAll().stream()
                .map(personMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public PersonDTO getById(int id) {
        if (personRepository.existsById(id)) {
            return personMapper.entityToDto(
                    personRepository.getById(Integer.valueOf(id)));
        } else {
            throw new ContactoInexistenteException("No se encontro el contacto con ese ID");
        }
    }

    public PersonDTO savePerson(PersonDTO dto) {
        if (personRepository.findAll().stream()
                .anyMatch(person -> person.getNombre().equals(dto.getNombre()) || person.getCelular().equals(dto.getCelular()))) {
            throw new ContactoExistenteException("Ya existe un contacto con esa informacion en la base de datos");
        } else {
            return personMapper.entityToDto(
                    personRepository.save(
                            personMapper.dtoToEntity(dto)));
        }
    }

    public PersonDTO updatePerson(PersonDTO dto) {
        Optional<Person> person = personRepository.findById(dto.getId());
        if (Objects.nonNull(person)  && person.isPresent()) {
            return personMapper.entityToDto(
                    personRepository.save(
                            personMapper.dtoToEntity(dto)));
        } else {
            throw new ContactoInexistenteException("No existe el contacto a actualizar");
        }
    }

    public String delete(int id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return "Registro eliminado";
        } else {
            throw new ContactoInexistenteException("No existe el contacto a eliminar");
        }
    }

    public Page<PersonDTO> findAllPaginated(Integer pageNumber, Integer pageSize) {
        return personRepository
                .findAll(PageRequest.of(pageNumber, pageSize))
                .map(personMapper::entityToDto);
    }

    public List<PersonDTO> findWithFilters(String nombre, String celular) {
        return personRepository.findWithFilters(nombre, celular)
                .stream()
                .map(
                        personMapper::entityToDto
                ).collect(Collectors.toList());
    }

    public List<AdressDTO> getAdressByIdContact(int id) {
        Optional<Person> person = personRepository.findById(id);
        if (Objects.nonNull(person) && person.isPresent()) {
            return person.get().getAdress()
                    .stream()
                    .map(adressMapper::entityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new ContactoInexistenteException("No se encontro el contacto con ese ID");
        }
    }
}
