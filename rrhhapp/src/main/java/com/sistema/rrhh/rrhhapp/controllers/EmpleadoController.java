package com.sistema.rrhh.rrhhapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.rrhh.rrhhapp.entitys.Empleado;
import com.sistema.rrhh.rrhhapp.exceptions.RecursoNoEncontradoException;
import com.sistema.rrhh.rrhhapp.services.IEmpleadoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/rrhh-app")
@CrossOrigin(origins = "http://localhost:3000")

public class EmpleadoController {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<Empleado> listarEmpleados() {
        List<Empleado> empleados = empleadoService.listarEmpleados();
       empleados.forEach(emp -> logger.info(empleados.toString()));
        return empleados;
    }

    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado) {
        logger.info("Agregando empleado: " + empleado);
        return empleadoService.agregarEmpleado(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> buscarEmpleado(@PathVariable Integer id) {
        Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el empleado con ID: " + id);
        }
    }
        @PutMapping("/empleados/{id}")
        public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleadoRecibido){
            Empleado empleadoExistente = empleadoService.buscarEmpleadoPorId(id);
            if (empleadoExistente == null) {
                throw new RecursoNoEncontradoException("No se encontro el empleado con ID: " + id);
            } else {
                empleadoExistente.setNombre(empleadoRecibido.getNombre());
                empleadoExistente.setDepartamento(empleadoRecibido.getDepartamento());
                empleadoExistente.setSueldo(empleadoRecibido.getSueldo());
                empleadoService.agregarEmpleado(empleadoExistente);
            }
            return ResponseEntity.ok(empleadoExistente);
        }

        @DeleteMapping("/empleados/{id}")
        public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Integer id){
            Empleado empleadoExistente = empleadoService.buscarEmpleadoPorId(id);
            if (empleadoExistente == null) {
                throw new RecursoNoEncontradoException("No se encontro el empleado con ID: " + id);
            } else {
                empleadoService.eliminarEmpleado(empleadoExistente);
            }
            Map<String, Boolean> response = new HashMap<>();
            response.put("eliminado",   Boolean.TRUE);
            return ResponseEntity.ok(response);
            }
        }