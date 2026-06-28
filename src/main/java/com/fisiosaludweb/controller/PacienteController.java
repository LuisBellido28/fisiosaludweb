package com.fisiosaludweb.controller;

import com.fisiosaludweb.entity.Paciente;
import com.fisiosaludweb.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService service;
    public PacienteController(PacienteService service) { this.service = service; }

    @GetMapping
    public String lista(@RequestParam(required = false) String q, Model model) {
        model.addAttribute("pacientes", q != null && !q.isBlank()
            ? service.buscar(q) : service.listarActivos());
        model.addAttribute("q", q);
        return "pacientes/lista";
    }

    @GetMapping("/nuevo")
    public String formNuevo(Model model) {
        model.addAttribute("paciente", new Paciente());
        model.addAttribute("titulo", "Nuevo Paciente");
        return "pacientes/form";
    }

    @GetMapping("/editar/{id}")
    public String formEditar(@PathVariable Long id, Model model) {
        model.addAttribute("paciente", service.findById(id));
        model.addAttribute("titulo", "Editar Paciente");
        return "pacientes/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Paciente paciente, RedirectAttributes ra) {
        service.guardar(paciente);
        ra.addFlashAttribute("msg", "Paciente guardado correctamente.");
        return "redirect:/pacientes";
    }

    @GetMapping("/baja/{id}")
    public String darBaja(@PathVariable Long id, RedirectAttributes ra) {
        service.darBaja(id);
        ra.addFlashAttribute("msg", "Paciente dado de baja.");
        return "redirect:/pacientes";
    }
}
