package com.fisiosaludweb.repository;
import com.fisiosaludweb.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByActivoTrue();
    List<Paciente> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCaseOrDniContaining(
        String nombre, String apellido, String dni);
}
