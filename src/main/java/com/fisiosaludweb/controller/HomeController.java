package com.fisiosaludweb.controller;

import com.fisiosaludweb.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final PacienteService pacienteService;
    private final FisioterapeutaService fisioService;
    private final ReservaService reservaService;
    private final SesionTerapiaService sesionService;

    public HomeController(PacienteService p, FisioterapeutaService f,
                          ReservaService r, SesionTerapiaService s) {
        this.pacienteService = p; this.fisioService = f;
        this.reservaService = r; this.sesionService = s;
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("totalPacientes", pacienteService.listarActivos().size());
        model.addAttribute("totalFisios", fisioService.listarActivos().size());
        model.addAttribute("reservasPendientes", reservaService.listarPendientes().size());
        model.addAttribute("totalSesiones", sesionService.listarTodas().size());
        return "dashboard";
    }
}
