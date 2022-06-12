package com.folcademy.Api.Model.Repositories;

import com.folcademy.Api.Model.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "personRepository")
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query(value = "FROM person p "  +
            "WHERE (:nombre IS NULL OR :nombre = p.nombre)" +
            "AND (:celular IS NULL OR :celular = p.celular)")
    List<Person> findWithFilters(String nombre, String celular);

}
