package com.fisiosaludweb.config;

import com.fisiosaludweb.entity.*;
import com.fisiosaludweb.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RolRepository rolRepo;
    private final UsuarioRepository userRepo;
    private final PasswordEncoder encoder;
    private final EspecialidadRepository espRepo;

    public DataInitializer(RolRepository rolRepo, UsuarioRepository userRepo,
                           PasswordEncoder encoder, EspecialidadRepository espRepo) {
        this.rolRepo = rolRepo;
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.espRepo = espRepo;
    }

    @Override
    public void run(String... args) {
        if (rolRepo.count() == 0) {
            Rol admin = rolRepo.save(new Rol(null, "ROLE_ADMIN"));
            Rol fisio = rolRepo.save(new Rol(null, "ROLE_FISIOTERAPEUTA"));
            Rol recep = rolRepo.save(new Rol(null, "ROLE_RECEPCIONISTA"));

            userRepo.save(new Usuario(null, "admin", encoder.encode("admin123"),
                    "Administrador", true, Set.of(admin)));
            userRepo.save(new Usuario(null, "fisio1", encoder.encode("fisio123"),
                    "Carlos Ríos", true, Set.of(fisio)));
            userRepo.save(new Usuario(null, "recep1", encoder.encode("recep123"),
                    "Ana Torres", true, Set.of(recep)));
        }
        if (espRepo.count() == 0) {
            espRepo.save(new Especialidad(null, "Terapia Física"));
            espRepo.save(new Especialidad(null, "Terapia Respiratoria"));
            espRepo.save(new Especialidad(null, "Terapia Neurológica"));
            espRepo.save(new Especialidad(null, "Terapia Deportiva"));
        }
    }
}
