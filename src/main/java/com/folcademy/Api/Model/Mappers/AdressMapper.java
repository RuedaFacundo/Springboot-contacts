package com.folcademy.Api.Model.Mappers;

import com.folcademy.Api.Model.Domain.AdressDTO;
import com.folcademy.Api.Model.Entities.Adress;
import com.folcademy.Api.Model.Entities.Person;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component(value = "adressMapper")
public class AdressMapper {

    public AdressDTO entityToDto(Adress entity){
        return Optional
                .ofNullable(entity)
                .map(
                        ent -> new AdressDTO(
                                ent.getId(),
                                ent.getCalle(),
                                ent.getNumero()
                        )
                )
                .orElse(new AdressDTO());
    }

    public Adress dtoToEntity(AdressDTO dto, Person person){
        Adress entity = new Adress();
        entity.setId(dto.getIdDireccion());
        entity.setCalle(dto.getCalle());
        entity.setNumero(dto.getNumero());
        entity.setPerson(person);
        return entity;
    }

}
