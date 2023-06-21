package com.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Setter @Getter
@Table(name = "sensor")
public class Sensor implements Serializable { // One to many  through unique column 'name' instead of id

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty
    @Size(min = 3, max = 30, message = "Sensor names should be between 3 and 30 characters")
    private String name;


}
