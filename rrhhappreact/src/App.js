import { BrowserRouter, Route, Routes } from 'react-router-dom';
import ListadoEmpleados from './empleados/ListadoEmpleados';
import NavBar from './plantilla/NavBar';
import AgregarEmpleado from './empleados/AgregarEmpleado';
import EditarEmpleado from './empleados/EditarEmpleado';
import EliminarEmpleado from './empleados/EliminarEmpleado';


function App() {
  return (
    <div className='container'>
      <BrowserRouter>
        <NavBar />
        <Routes>
          <Route exact path = '/' element = {<ListadoEmpleados />} />
          <Route exact path = '/agregar' element = {<AgregarEmpleado />} />
          <Route exact path = '/editar/:id' element = {<EditarEmpleado />} />
          <Route exact path = '/eliminar/:id' element = {<EliminarEmpleado />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
