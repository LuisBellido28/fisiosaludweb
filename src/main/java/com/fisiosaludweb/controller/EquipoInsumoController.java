package com.fisiosaludweb.controller;

import com.fisiosaludweb.entity.EquipoInsumo;
import com.fisiosaludweb.service.EquipoInsumoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/equipos")
public class EquipoInsumoController {
    private final EquipoInsumoService service;
    public EquipoInsumoController(EquipoInsumoService service) { this.service = service; }

    private static final List<String> TIPOS = List.of("EQUIPO","INSUMO");
    private static final List<String> ESTADOS = List.of("DISPONIBLE","MANTENIMIENTO");

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("equipos", service.listarActivos());
        return "equipos/lista";
    }

    @GetMapping("/nuevo")
    public String formNuevo(Model model) {
        model.addAttribute("equipo", new EquipoInsumo());
        model.addAttribute("tipos", TIPOS);
        model.addAttribute("estados", ESTADOS);
        model.addAttribute("titulo", "Nuevo Equipo/Insumo");
        return "equipos/form";
    }

    @GetMapping("/editar/{id}")
    public String formEditar(@PathVariable Long id, Model model) {
        model.addAttribute("equipo", service.findById(id));
        model.addAttribute("tipos", TIPOS);
        model.addAttribute("estados", ESTADOS);
        model.addAttribute("titulo", "Editar Equipo/Insumo");
        return "equipos/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute EquipoInsumo e, RedirectAttributes ra) {
        service.guardar(e);
        ra.addFlashAttribute("msg", "Equipo/Insumo guardado correctamente.");
        return "redirect:/equipos";
    }

    @GetMapping("/baja/{id}")
    public String darBaja(@PathVariable Long id, RedirectAttributes ra) {
        service.darBaja(id);
        ra.addFlashAttribute("msg", "Registro dado de baja.");
        return "redirect:/equipos";
    }
}
