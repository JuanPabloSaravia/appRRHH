import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { NumericFormat } from 'react-number-format';
import { Link } from 'react-router-dom';


export default function ListadoEmpleados() {

    const urlBase = "http://localhost:8080/rrhh-app/empleados";

    const [empleados, setEmpleados] = useState([]);
    useEffect(() => {
        cargarEmpleados();
    }, []);

    const cargarEmpleados = async () => {
        const resultado = await axios.get(urlBase);
        console.log("Resultado de los empleados");
        console.log(resultado.data);
        setEmpleados(resultado.data);
    }
  return (
    <div className='container'>
        <div className="container text-center" style={{ margin: "30px" }}>
            <h3>Sistema de RRHH</h3>
        </div>
      <table className="table table-striped table-hover align-middle">
        <thead className='table-primary'>
            <tr>
            <th scope="col">Id</th>
            <th scope="col">Empleado</th>
            <th scope="col">Departamento</th>
            <th scope="col">Sueldo</th>
            <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
            {empleados.map( (empleado, indice) => (
                <tr key={indice}>
                    <th scope="row">{empleado.idEmpleado}</th>
                    <td>{empleado.nombre}</td>
                    <td>{empleado.departamento}</td>
                    <td><NumericFormat value={empleado.sueldo} displayType={'text'} 
                    thousandSeparator={true} prefix={'$'} decimalScale={2} fixedDecimalScale={true}/>
                    </td>
                    <td>
                        <div className='text-end'>
                            <Link to={`/editar/${empleado.idEmpleado}`} className="btn btn-secondary btn-sm me-3">Editar</Link>
                        </div>
                    </td>
                    <td>
                        <div className='text-end'>
                            <Link to={`/eliminar/${empleado.idEmpleado}`} className="btn btn-danger btn-sm me-3">Eliminar</Link>
                        </div>
                    </td>
                </tr>
            ))}
        </tbody>
        </table>
    </div>
  )
}
