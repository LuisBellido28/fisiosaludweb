package com.fisiosaludweb.repository;
import com.fisiosaludweb.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByEstadoOrderByFechaHoraAsc(String estado);
    List<Reserva> findByPacienteIdOrderByFechaHoraDesc(Long pacienteId);
    @Query("SELECT r FROM Reserva r WHERE FUNCTION('DATE', r.fechaHora) = :fecha ORDER BY r.fechaHora ASC")
    List<Reserva> findByFecha(LocalDate fecha);
}
