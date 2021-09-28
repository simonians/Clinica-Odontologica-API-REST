package com.example.clinicaOdontologicaConORM.service.impl.login;


import com.example.clinicaOdontologicaConORM.persistence.entities.login.AppUser;
import com.example.clinicaOdontologicaConORM.persistence.repository.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return repository.findByEmail(email).orElseThrow((() -> new UsernameNotFoundException("User email not found")));
    }

    public AppUser crearUsuario(AppUser usuario){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(usuario.getPassword()); //Genera el password encriptado
        usuario.setPassword(hashedPassword);
        return repository.save(usuario);
    }
}
