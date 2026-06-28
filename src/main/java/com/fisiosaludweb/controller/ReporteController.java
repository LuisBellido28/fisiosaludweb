package com.fisiosaludweb.controller;

import com.fisiosaludweb.entity.SesionTerapia;
import com.fisiosaludweb.service.SesionTerapiaService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/reportes")
public class ReporteController {
    private final SesionTerapiaService sesionService;
    public ReporteController(SesionTerapiaService sesionService) { this.sesionService = sesionService; }

    @GetMapping
    public String index() { return "reportes/index"; }

    @GetMapping("/sesiones")
    public ResponseEntity<byte[]> reporteSesiones(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        try {
            List<SesionTerapia> sesiones = sesionService.porPeriodo(inicio, fin);
            List<Map<String, Object>> data = new ArrayList<>();
            for (SesionTerapia s : sesiones) {
                Map<String, Object> row = new HashMap<>();
                row.put("fecha", s.getFechaSesion().toString());
                row.put("paciente", s.getReserva().getPaciente().getNombreCompleto());
                row.put("fisioterapeuta", s.getReserva().getFisioterapeuta().getNombreCompleto());
                row.put("terapia", s.getReserva().getTipoTerapia());
                row.put("monto", s.getMontoCobrado());
                row.put("observaciones", s.getObservaciones());
                data.add(row);
            }
            double total = sesiones.stream().mapToDouble(SesionTerapia::getMontoCobrado).sum();

            InputStream is = new ClassPathResource("reports/reporte_sesiones.jrxml").getInputStream();
            JasperReport jr = JasperCompileManager.compileReport(is);
            Map<String, Object> params = new HashMap<>();
            params.put("TITULO", "Reporte de Sesiones por Período");
            params.put("FECHA_INICIO", inicio.toString());
            params.put("FECHA_FIN", fin.toString());
            params.put("TOTAL", total);

            JasperPrint jp = JasperFillManager.fillReport(jr, params, new JRBeanCollectionDataSource(data));
            byte[] pdf = JasperExportManager.exportReportToPdf(jp);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.attachment().filename("sesiones.pdf").build());
            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/ingresos")
    public ResponseEntity<byte[]> reporteIngresos(
            @RequestParam int anio,
            @RequestParam int mes) {
        try {
            LocalDate inicio = LocalDate.of(anio, mes, 1);
            LocalDate fin = inicio.withDayOfMonth(inicio.lengthOfMonth());
            List<SesionTerapia> sesiones = sesionService.porPeriodo(inicio, fin);

            Map<String, Double> porFisio = new LinkedHashMap<>();
            Map<String, Integer> contFisio = new LinkedHashMap<>();
            for (SesionTerapia s : sesiones) {
                String fisio = s.getReserva().getFisioterapeuta().getNombreCompleto();
                porFisio.merge(fisio, s.getMontoCobrado(), Double::sum);
                contFisio.merge(fisio, 1, Integer::sum);
            }

            List<Map<String, Object>> data = new ArrayList<>();
            porFisio.forEach((fisio, total) -> {
                Map<String, Object> row = new HashMap<>();
                row.put("fisioterapeuta", fisio);
                row.put("numSesiones", contFisio.get(fisio));
                row.put("totalIngresado", total);
                data.add(row);
            });

            InputStream is = new ClassPathResource("reports/reporte_ingresos.jrxml").getInputStream();
            JasperReport jr = JasperCompileManager.compileReport(is);
            Map<String, Object> params = new HashMap<>();
            params.put("TITULO", "Reporte de Ingresos Mensuales");
            params.put("MES_ANIO", mes + "/" + anio);
            params.put("TOTAL_GENERAL", sesiones.stream().mapToDouble(SesionTerapia::getMontoCobrado).sum());

            JasperPrint jp = JasperFillManager.fillReport(jr, params, new JRBeanCollectionDataSource(data));
            byte[] pdf = JasperExportManager.exportReportToPdf(jp);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.attachment().filename("ingresos.pdf").build());
            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
