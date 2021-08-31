package com.cursojava.curso.dao;


/* Una interface viene a ser como un archivo donde indicamos qué funciones debería tener una clase,
dicha clase que implenta dicha interfaz está obligada a utilizar esas funciones */

import com.cursojava.curso.models.Usuario;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UsuarioDao {
    //Se declaran funciones obligatorias

    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
