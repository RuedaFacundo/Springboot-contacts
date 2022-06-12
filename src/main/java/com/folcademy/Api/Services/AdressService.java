package com.folcademy.Api.Services;

import com.folcademy.Api.Model.Domain.AdressDTO;
import com.folcademy.Api.Model.Mappers.AdressMapper;
import com.folcademy.Api.Model.Repositories.AdressRepository;
import com.folcademy.Api.Model.Repositories.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service(value = "adressService")
public class AdressService {

    private final AdressRepository adressRepository;
    private final PersonRepository personRepository;
    private final AdressMapper adressMapper;

    public AdressService(AdressRepository adressRepository, PersonRepository personRepository, AdressMapper adressMapper) {
        this.adressRepository = adressRepository;
        this.personRepository = personRepository;
        this.adressMapper = adressMapper;
    }

    public Page<AdressDTO> findWithFiltersAndPaginated(Integer pageNumber, Integer pageSize, String calle, String numero) {
        return adressRepository.findWithFilters(calle, numero, PageRequest.of(pageNumber, pageSize))
                .map(adressMapper::entityToDto);
    }
}
