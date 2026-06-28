package com.fisiosaludweb.controller;

import com.fisiosaludweb.entity.*;
import com.fisiosaludweb.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/sesiones")
public class SesionController {
    private final SesionTerapiaService sesionService;
    private final ReservaService reservaService;
    private final EquipoInsumoService equipoService;
    private final PacienteService pacienteService;

    public SesionController(SesionTerapiaService s, ReservaService r,
                             EquipoInsumoService e, PacienteService p) {
        this.sesionService = s; this.reservaService = r;
        this.equipoService = e; this.pacienteService = p;
    }

    @GetMapping("/registrar/{reservaId}")
    public String formRegistrar(@PathVariable Long reservaId, Model model) {
        Reserva reserva = reservaService.findById(reservaId);
        model.addAttribute("reserva", reserva);
        model.addAttribute("sesion", new SesionTerapia());
        model.addAttribute("equipos", equipoService.listarDisponibles());
        return "sesiones/registrar";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam Long reservaId,
                          @RequestParam String observaciones,
                          @RequestParam String evolucion,
                          @RequestParam double montoCobrado,
                          @RequestParam(required = false) List<Long> equipoIds,
                          @RequestParam(required = false) List<Integer> cantidades,
                          RedirectAttributes ra) {
        Reserva reserva = reservaService.findById(reservaId);
        reserva.setEstado("EN_ATENCION");
        reservaService.guardar(reserva);

        SesionTerapia sesion = new SesionTerapia();
        sesion.setReserva(reserva);
        sesion.setObservaciones(observaciones);
        sesion.setEvolucion(evolucion);
        sesion.setMontoCobrado(montoCobrado);

        try {
            sesionService.registrarSesion(sesion,
                equipoIds != null ? equipoIds : List.of(),
                cantidades != null ? cantidades : List.of());
            ra.addFlashAttribute("msg", "Sesión registrada exitosamente.");
        } catch (RuntimeException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
            return "redirect:/sesiones/registrar/" + reservaId;
        }
        return "redirect:/reservas";
    }

    @GetMapping("/historial")
    public String historial(@RequestParam(required = false) Long pacienteId, Model model) {
        model.addAttribute("pacientes", pacienteService.listarActivos());
        if (pacienteId != null) {
            model.addAttribute("sesiones", sesionService.historialPorPaciente(pacienteId));
            model.addAttribute("pacienteSeleccionado", pacienteService.findById(pacienteId));
        }
        model.addAttribute("pacienteId", pacienteId);
        return "sesiones/historial";
    }
}
