package com.example.clinicaOdontologicaConORM.controllers.impl.login;

import com.example.clinicaOdontologicaConORM.persistence.entities.login.AppUser;
import com.example.clinicaOdontologicaConORM.service.impl.login.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    AppUserService service;

    @GetMapping("/")
    public String home(){
        return "<h1> Welcome </h1>";
    }

    @GetMapping("/user")
    public String user(){
        return "<h1> Welcome user </h1>";
    }

    @GetMapping("/admin")
    public String admin(){
        return "<h1> Welcome admin </h1>";
    }

    @PostMapping("/usuarios/crear")
    public ResponseEntity<?> crearEnBDD(@RequestBody AppUser user) {
        ResponseEntity<?> respuesta = ResponseEntity.badRequest().body(user);
        AppUser usuario = service.crearUsuario(user);
        if (usuario != null){
            respuesta = org.springframework.http.ResponseEntity.ok(usuario);
        }
        return respuesta;
    }
}
