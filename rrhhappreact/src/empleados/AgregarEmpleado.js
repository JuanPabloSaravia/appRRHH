import React from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function AgregarEmpleado() {
    let navegacion = useNavigate();

    const [empleado, setEmpleado] = React.useState({
        nombre: '',
        departamento: '',
        sueldo: ''
    });
    const{nombre, departamento, sueldo} = empleado;
    const onInputChange = (e) => {
        //spread operator ... para expandir los atributos
        setEmpleado({...empleado, [e.target.name]: e.target.value})
    };
    const onSubmit = async (e) => {

        

        e.preventDefault();
        const urlBase = "http://localhost:8080/rrhh-app/empleados";
        await axios.post(urlBase, empleado);

        //redireccionar a la pagina principal
        navegacion('/');

    }
  return (
    <div className='container'>
        <div className="container text-center" style={{ margin: "30px" }}>
            <h3>Agregar Empleado</h3>
        </div>
        <div className="container" style={{ maxWidth: "600px" }}>
            <form onSubmit={(e) => onSubmit(e)}>
                <div className="mb-3">
                    <label htmlForm="nombre" className="form-label">Nombre</label>
                    <input type="text" className="form-control" id="nombre" 
                    name="nombre" required={true} value={nombre} onChange={(e) => onInputChange(e)}/>
                </div>
                <div className="mb-3">
                    <label htmlForm="departamento" className="form-label">Departamento</label>
                    <input type="text" className="form-control" id="departamento" 
                    name="departamento" required={true} value={departamento} onChange={(e) => onInputChange(e)}/>  
                </div>
                <div className="mb-3">
                    <label htmlForm="sueldo" className="form-label">Sueldo</label>
                    <input type="number" step="any" className="form-control" id="sueldo"
                     name="sueldo" value={sueldo} onChange={(e) => onInputChange(e)}/>  
                </div>
                <div className='text-center'>
                    <button type="submit" className="btn btn-primary btn-sm me-3">Agregar</button>
                    <a href='/' className="btn btn-danger btn-sm">Regresar</a>
                </div>
            </form>
        </div>
    </div>
  )
}
