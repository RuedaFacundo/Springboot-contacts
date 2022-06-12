package com.folcademy.Api.Model.Mappers;

import com.folcademy.Api.Model.Domain.PersonDTO;
import com.folcademy.Api.Model.Entities.Adress;
import com.folcademy.Api.Model.Entities.Person;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component(value = "personMapper")
public class PersonMapper {

    private final AdressMapper adressMapper;

    public PersonMapper(AdressMapper adressMapper) {
        this.adressMapper = adressMapper;
    }

    public PersonDTO entityToDto(Person entity){
        return Optional
                .ofNullable(entity)
                .map(
                        ent -> new PersonDTO(
                                ent.getId(),
                                ent.getNombre(),
                                ent.getCelular(),
                                ent.getAdress()
                                        .stream()
                                        .map(adressMapper::entityToDto)
                                        .collect(Collectors.toList())
                        )
                )
                .orElse(new PersonDTO());
    }

    public Person dtoToEntity(PersonDTO dto){
        Person entity = new Person();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setCelular(dto.getCelular());
        entity.setAdress(
                dto.getDirecciones()
                        .stream().map(
                                ent -> new Adress(
                                        ent.getIdDireccion(),
                                        ent.getCalle(),
                                        ent.getNumero(),
                                        entity
                                )
                        ).collect(Collectors.toList()));
        return entity;
    }

}
