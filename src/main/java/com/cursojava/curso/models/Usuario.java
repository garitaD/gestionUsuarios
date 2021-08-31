package com.cursojava.curso.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//Anotaciones para indicar la tabla que tiene que utilizar
@Entity
@Table(name ="usuarios")

public class Usuario {

    @Id //con esto se indica que es la clave primaria
    @Getter @Setter @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "apellido")
    private String apellido;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "telefono")
    private String telefono;

    @Getter @Setter @Column(name = "pass")
    private String password;


}
