<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />
    <title>LAB 9</title>
</head>



<body>
<jsp:include page="/includes/navbar.jsp"/>
<div class='container'>


    <div class="row mb-5 mt-4">
        <div class="col-lg-6">
            <h1 class=''>Lista de Partidos</h1>
        </div>
        <div class="col-lg-6 my-auto text-lg-right">
            <a href="<%= request.getContextPath() %>/PartidoServlet?action=crear" class="btn btn-primary">Crear Partido</a>
        </div>
    </div>


    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Jornada</th>
            <th>Fecha</th>
            <th>Selección Local</th>
            <th>Selección Visitante</th>
            <th>Estadio a jugar</th>
            <th>Árbitro</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty partidos}">
                <c:forEach var="partido" items="${partidos}">
                    <tr>
                        <td>${partido.idPartido}</td>
                        <td>${partido.numeroJornada}</td>
                        <td>${partido.fecha}</td>
                        <td>${partido.seleccionLocal.nombre}</td>
                        <td>${partido.seleccionVisitante.nombre}</td>
                        <td>${partido.seleccionLocal.estadio.nombre}</td>
                        <td>${partido.arbitro.nombre}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="7" class="text-center text-danger">No hay partidos en la basse de datos</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>


    </table>
</div>



<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" crossorigin="anonymous"></script>
</body>
</html>
