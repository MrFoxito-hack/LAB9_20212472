package com.example.lab9_base.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Seleccion;
import com.example.lab9_base.Dao.DaoArbitros;
import com.example.lab9_base.Dao.DaoSelecciones;

@WebServlet(name = "ArbitroServlet", urlPatterns = {"/ArbitroServlet"})
public class ArbitroServlet extends HttpServlet {

    private final DaoArbitros daoArbitros = new DaoArbitros();
    private final DaoSelecciones daoSelecciones = new DaoSelecciones();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        RequestDispatcher view;

        switch (action) {
            case "guardar":
                String nombreArbitro = request.getParameter("nombre");
                String pais = request.getParameter("pais");

                if (nombreArbitro == null || nombreArbitro.isEmpty() || pais == null || pais.isEmpty()) {
                    request.setAttribute("error", "Todos los campos son obligatorios.");
                    view = request.getRequestDispatcher("/arbitros/form.jsp");
                    view.forward(request, response);
                    return;
                }

                // Verificar si el árbitro ya existe por nombre
                ArrayList<Arbitro> arbitrosExistentes = daoArbitros.busquedaNombre(nombreArbitro);
                if (!arbitrosExistentes.isEmpty()) {
                    request.setAttribute("error", "Ya existe un árbitro con ese nombre.");
                    view = request.getRequestDispatcher("/arbitros/form.jsp");
                    view.forward(request, response);
                    return;
                }

                // Crear y guardar el árbitro
                Arbitro arbitro = new Arbitro();
                arbitro.setNombre(nombreArbitro);
                arbitro.setPais(pais);
                daoArbitros.crearArbitro(arbitro);

                response.sendRedirect("ArbitroServlet?action=lista");
                break;

            case "borrar":
                try {
                    // Verifica que el parámetro 'id' esté presente
                    String idParam = request.getParameter("id");
                    if (idParam != null && !idParam.isEmpty()) {
                        int id = Integer.parseInt(idParam);  // Asegura la conversión a entero
                        daoArbitros.borrarArbitro(id);  // Eliminar el árbitro
                        // Después de la eliminación, redirige a la lista de árbitros
                        response.sendRedirect("ArbitroServlet?action=lista");
                    } else {
                        // Si no hay ID válido, muestra el error
                        request.setAttribute("error", "ID del árbitro no proporcionado o inválido.");
                        view = request.getRequestDispatcher("/arbitros/list.jsp");
                        view.forward(request, response);
                    }
                } catch (NumberFormatException e) {
                    // Si hay un error en el formato del ID
                    request.setAttribute("error", "El ID del árbitro es inválido.");
                    view = request.getRequestDispatcher("/arbitros/list.jsp");
                    view.forward(request, response);
                }
                break;


            case "buscar":
                // Obtener el tipo de búsqueda y el valor
                String tipo = request.getParameter("tipo");
                String buscar = request.getParameter("buscar");
                ArrayList<Arbitro> arbitros = new ArrayList<>();

                if ("nombre".equals(tipo)) {
                    arbitros = daoArbitros.busquedaNombre(buscar);  // Buscar por nombre
                } else if ("pais".equals(tipo)) {
                    arbitros = daoArbitros.busquedaPais(buscar);  // Buscar por país
                }

                request.setAttribute("arbitros", arbitros);
                view = request.getRequestDispatcher("/arbitros/list.jsp");
                view.forward(request, response);
                break;

            default:
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;

        switch (action) {
            case "lista":
                // Listar todos los árbitros
                ArrayList<Arbitro> arbitros = daoArbitros.listarArbitros();
                request.setAttribute("arbitros", arbitros);

                // Cargar países desde la base de datos
                ArrayList<String> paises = daoArbitros.listarPaises();  // Método que devuelve los países
                request.setAttribute("paises", paises);

                view = request.getRequestDispatcher("/arbitros/list.jsp");
                view.forward(request, response);
                break;

            case "crear":
                // Obtener las selecciones para el formulario
                ArrayList<Seleccion> selecciones = daoSelecciones.listarSelecciones();
                request.setAttribute("selecciones", selecciones);

                // Obtener la lista de países para el formulario
                ArrayList<String> paisesLista = daoArbitros.listarPaises();  // Cargar los países desde la base de datos
                request.setAttribute("paises", paisesLista);
                view = request.getRequestDispatcher("/arbitros/form.jsp");
                view.forward(request, response);
                break;

            default:
                break;
        }
    }
}
