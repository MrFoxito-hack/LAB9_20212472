package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;
import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Dao.DaoPartidos;
import com.example.lab9_base.Dao.DaoSelecciones;
import com.example.lab9_base.Dao.DaoArbitros;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet(name = "PartidoServlet", urlPatterns = {"/PartidoServlet"})
public class PartidoServlet extends HttpServlet {

    private final DaoPartidos daoPartidos = new DaoPartidos();
    private final DaoSelecciones daoSelecciones = new DaoSelecciones();
    private final DaoArbitros daoArbitros = new DaoArbitros();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        RequestDispatcher view;

        switch (action) {
            case "guardar":
                try {
                    // Obtener los datos del formulario
                    int seleccionLocalId = Integer.parseInt(request.getParameter("seleccionLocal"));
                    int seleccionVisitanteId = Integer.parseInt(request.getParameter("seleccionVisitante"));
                    int arbitroId = Integer.parseInt(request.getParameter("arbitro"));
                    String fechaStr = request.getParameter("fecha");
                    Date fechaSql = Date.valueOf(fechaStr); // Conversión de String a java.sql.Date
                    int numeroJornada = Integer.parseInt(request.getParameter("numeroJornada"));

                    // Validación: Una selección no puede jugar contra sí misma
                    if (seleccionLocalId == seleccionVisitanteId) {
                        request.setAttribute("error", "Una selección no puede jugar contra sí misma.");
                        ArrayList<Seleccion> selecciones = daoSelecciones.listarSelecciones();
                        ArrayList<Arbitro> arbitros = daoArbitros.listarArbitros();
                        request.setAttribute("selecciones", selecciones);
                        request.setAttribute("arbitros", arbitros);
                        view = request.getRequestDispatcher("/partidos/form.jsp");
                        view.forward(request, response);
                        return;
                    }

                    // Crear y guardar el partido
                    Partido partido = new Partido();
                    partido.setSeleccionLocal(new Seleccion(seleccionLocalId));
                    partido.setSeleccionVisitante(new Seleccion(seleccionVisitanteId));
                    partido.setArbitro(new Arbitro(arbitroId));
                    partido.setFecha(fechaSql); // Usar java.sql.Date
                    partido.setNumeroJornada(numeroJornada);

                    daoPartidos.crearPartido(partido);
                    response.sendRedirect("PartidoServlet?action=lista");  // Redirige al método GET para obtener la lista
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    request.setAttribute("error", "Datos inválidos. Por favor, verifica los campos ingresados.");
                    view = request.getRequestDispatcher("/partidos/form.jsp");
                    view.forward(request, response);
                }
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;

        switch (action) {
            case "lista":
                // Obtener la lista de partidos
                ArrayList<Partido> partidos = daoPartidos.listaDePartidos();
                request.setAttribute("partidos", partidos);

                // Redirigir a la vista que lista los partidos
                view = request.getRequestDispatcher("/index.jsp");
                view.forward(request, response);
                break;

            case "crear":
                // Obtener las selecciones y los árbitros para pasar a la vista
                ArrayList<Seleccion> selecciones = daoSelecciones.listarSelecciones();
                ArrayList<Arbitro> arbitros = daoArbitros.listarArbitros();

                // Pasar estos datos a la vista para el formulario
                request.setAttribute("selecciones", selecciones);
                request.setAttribute("arbitros", arbitros);

                // Redirigir a la vista del formulario de crear partido
                view = request.getRequestDispatcher("/partidos/form.jsp");
                view.forward(request, response);
                break;
        }
    }
}
