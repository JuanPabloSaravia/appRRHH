package com.sistema.rrhh.rrhhapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistema.rrhh.rrhhapp.entitys.Empleado;

@Service
public interface IEmpleadoService {

    public List<Empleado> listarEmpleados();

    public Empleado buscarEmpleadoPorId(Integer idEmpleado);

    public Empleado guardarEmpleado(Empleado empleado);

    public void eliminarEmpleado(Empleado empleado);

}
