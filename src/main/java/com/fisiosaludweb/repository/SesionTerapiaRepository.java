package com.fisiosaludweb.repository;
import com.fisiosaludweb.entity.SesionTerapia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
public interface SesionTerapiaRepository extends JpaRepository<SesionTerapia, Long> {
    List<SesionTerapia> findByReservaPacienteIdOrderByFechaSesionDesc(Long pacienteId);
    List<SesionTerapia> findByFechaSesionBetweenOrderByFechaSesionAsc(LocalDate inicio, LocalDate fin);
}
