package com.cursojava.curso.dao;


import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository //hace regerencia a la conexion con la base de datos
@Transactional //da la funcionalidad a la clase de poder armar las consultas de sql a la base de datos


//Clase que implementa la interface
public class UsuarioDaoImp implements UsuarioDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Usuario> getUsuarios() {
        String query ="FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class,id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        //para guardar en la base de datos
        entityManager.merge(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        //String query = "FROM Usuario WHERE email = :email AND password == :password";
        String query = " FROM Usuario  WHERE email = :email";

        List<Usuario> lista =entityManager.createQuery(query)
                            .setParameter("email", usuario.getEmail())
                            .getResultList();

        if (lista.isEmpty()){
            return null;
        }
        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
         if (argon2.verify(passwordHashed, usuario.getPassword())){//comprara la contraseña ingresada con la encriptada
             return lista.get(0);
         }
         return null;
    }


}
