package com.fisiosaludweb.repository;
import com.fisiosaludweb.entity.EquipoInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EquipoInsumoRepository extends JpaRepository<EquipoInsumo, Long> {
    List<EquipoInsumo> findByActivoTrue();
    List<EquipoInsumo> findByActivoTrueAndCantidadGreaterThan(int cantidad);
}
