package com.fisiosaludweb.repository;
import com.fisiosaludweb.entity.Fisioterapeuta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface FisioterapeutaRepository extends JpaRepository<Fisioterapeuta, Long> {
    List<Fisioterapeuta> findByActivoTrue();
}
