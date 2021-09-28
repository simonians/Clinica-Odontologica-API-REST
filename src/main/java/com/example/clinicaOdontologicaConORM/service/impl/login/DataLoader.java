package com.example.clinicaOdontologicaConORM.service.impl.login;


import com.example.clinicaOdontologicaConORM.persistence.entities.login.AppRoles;
import com.example.clinicaOdontologicaConORM.persistence.entities.login.AppUser;
import com.example.clinicaOdontologicaConORM.persistence.repository.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("password"); //Genera el password encriptado
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("password2");
        userRepository.save(new AppUser("Diego", "diego", "diego@digital.com", hashedPassword, AppRoles.ADMIN));
        userRepository.save(new AppUser("Paula", "paula", "paula@digital.com", hashedPassword2, AppRoles.USER));
    }
}
