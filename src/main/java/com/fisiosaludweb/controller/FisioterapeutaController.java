package com.fisiosaludweb.controller;

import com.fisiosaludweb.entity.Fisioterapeuta;
import com.fisiosaludweb.service.FisioterapeutaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/fisioterapeutas")
public class FisioterapeutaController {
    private final FisioterapeutaService service;
    public FisioterapeutaController(FisioterapeutaService service) { this.service = service; }

    private static final List<String> ESPECIALIDADES = List.of(
        "Terapia Física","Terapia Respiratoria","Terapia Neurológica","Terapia Deportiva");
    private static final List<String> TURNOS = List.of("MAÑANA","TARDE","AMBOS");

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("fisioterapeutas", service.listarActivos());
        return "fisioterapeutas/lista";
    }

    @GetMapping("/nuevo")
    public String formNuevo(Model model) {
        model.addAttribute("fisioterapeuta", new Fisioterapeuta());
        model.addAttribute("especialidades", ESPECIALIDADES);
        model.addAttribute("turnos", TURNOS);
        model.addAttribute("titulo", "Nuevo Fisioterapeuta");
        return "fisioterapeutas/form";
    }

    @GetMapping("/editar/{id}")
    public String formEditar(@PathVariable Long id, Model model) {
        model.addAttribute("fisioterapeuta", service.findById(id));
        model.addAttribute("especialidades", ESPECIALIDADES);
        model.addAttribute("turnos", TURNOS);
        model.addAttribute("titulo", "Editar Fisioterapeuta");
        return "fisioterapeutas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Fisioterapeuta f, RedirectAttributes ra) {
        service.guardar(f);
        ra.addFlashAttribute("msg", "Fisioterapeuta guardado correctamente.");
        return "redirect:/fisioterapeutas";
    }

    @GetMapping("/baja/{id}")
    public String darBaja(@PathVariable Long id, RedirectAttributes ra) {
        service.darBaja(id);
        ra.addFlashAttribute("msg", "Fisioterapeuta dado de baja.");
        return "redirect:/fisioterapeutas";
    }
}
