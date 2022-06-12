package com.folcademy.Api.Model.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name ="person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person", columnDefinition = "INT UNSIGNED")
    public Integer id;

    @Column(name = "nombre", columnDefinition = "VARCHAR(45)")
    public String nombre;

    @Column(name = "email", columnDefinition = "VARCHAR(45)")
    public String email;

    @Column(name = "celular", columnDefinition = "VARCHAR(15)")
    public String celular;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Adress> adress = new ArrayList<>();
}
