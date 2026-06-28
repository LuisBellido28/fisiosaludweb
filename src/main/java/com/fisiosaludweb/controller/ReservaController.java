package com.fisiosaludweb.controller;

import com.fisiosaludweb.entity.Reserva;
import com.fisiosaludweb.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaController {
    private final ReservaService reservaService;
    private final PacienteService pacienteService;
    private final FisioterapeutaService fisioService;

    public ReservaController(ReservaService r, PacienteService p, FisioterapeutaService f) {
        this.reservaService = r; this.pacienteService = p; this.fisioService = f;
    }

    private static final List<String> TERAPIAS = List.of(
        "Terapia Física","Terapia Respiratoria","Terapia Neurológica","Terapia Deportiva",
        "Electroterapia","Masoterapia","Hidroterapia");

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("reservas", reservaService.listarPendientes());
        return "reservas/lista";
    }

    @GetMapping("/todas")
    public String todas(Model model) {
        model.addAttribute("reservas", reservaService.listarTodas());
        return "reservas/lista";
    }

    @GetMapping("/disponibilidad")
    public String disponibilidad(@RequestParam(required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
                                 Model model) {
        LocalDate f = fecha != null ? fecha : LocalDate.now();
        model.addAttribute("fecha", f);
        model.addAttribute("reservas", reservaService.buscarPorFecha(f));
        model.addAttribute("fisioterapeutas", fisioService.listarActivos());
        return "reservas/disponibilidad";
    }

    @GetMapping("/nueva")
    public String formNueva(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("pacientes", pacienteService.listarActivos());
        model.addAttribute("fisioterapeutas", fisioService.listarActivos());
        model.addAttribute("terapias", TERAPIAS);
        return "reservas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Reserva reserva,
                          @RequestParam Long pacienteId,
                          @RequestParam Long fisioterapeutaId,
                          RedirectAttributes ra) {
        reserva.setPaciente(pacienteService.findById(pacienteId));
        reserva.setFisioterapeuta(fisioService.findById(fisioterapeutaId));
        reservaService.guardar(reserva);
        ra.addFlashAttribute("msg", "Reserva registrada correctamente.");
        return "redirect:/reservas";
    }

    @GetMapping("/cancelar/{id}")
    public String cancelar(@PathVariable Long id, RedirectAttributes ra) {
        reservaService.cancelar(id);
        ra.addFlashAttribute("msg", "Reserva cancelada.");
        return "redirect:/reservas";
    }
}
