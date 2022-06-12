package com.folcademy.Api.Model.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name ="adress")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT UNSIGNED")
    public Integer id;

    @Column(name = "calle", columnDefinition = "VARCHAR(45)")
    public String calle;

    @Column(name = "numero", columnDefinition = "VARCHAR(15)")
    public String numero;

    @ManyToOne
    @JoinColumn(name = "id_person")
    public Person person;

}
