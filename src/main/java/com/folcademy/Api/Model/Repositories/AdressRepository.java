package com.folcademy.Api.Model.Repositories;

import com.folcademy.Api.Model.Entities.Adress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "adressRepository")
public interface AdressRepository extends JpaRepository<Adress, Integer> {

    @Query(value = "FROM adress a "  +
            "WHERE (:calle IS NULL OR :calle = a.calle)" +
            "AND (:numero IS NULL OR :numero = a.numero)")
    Page<Adress> findWithFilters(String calle, String numero, Pageable pageable);

}
