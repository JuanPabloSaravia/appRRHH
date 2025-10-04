package com.sistema.rrhh.rrhhapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.rrhh.rrhhapp.entitys.Empleado;
import com.sistema.rrhh.rrhhapp.services.IEmpleadoService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/rrhh-app")
@CrossOrigin(origins = "http://localhost:5173")

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
}
