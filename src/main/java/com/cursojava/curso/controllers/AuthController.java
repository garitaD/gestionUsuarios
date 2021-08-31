package com.cursojava.curso.controllers;


import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Clase para gestionar el inicio de sesion ytodo lo que tenga que ver con la autenticación
@RestController
public class AuthController {

    @Autowired  //esta anotacion hace que clase usarioDaoImp cree un obj y lo guarde dentro de esa variable
    private UsuarioDao usuarioDao;

    @Autowired  //esta anotacion hace que clase JWTUtil cree un obj y lo guarde dentro de esa variable
    private JWTUtil jwtUtil;


    @RequestMapping(value = "api/login", method = RequestMethod.POST)//POST ya que está enviando la información internamente
    public String login(@RequestBody Usuario usuario){ //convierte el JSON que recibe a un usuario automaticamente
        Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if (usuarioLogueado != null){
            //Crear el JWT
            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
            return tokenJwt;
        }
        return "Fail";
    }
}
