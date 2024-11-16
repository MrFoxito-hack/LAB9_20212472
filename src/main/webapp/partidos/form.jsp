<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>



<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />
    <title>Crear Partido</title>
</head>





<body>

<jsp:include page="/includes/navbar.jsp"/>
<div class="container">

    <h1>Crear Partido</h1>

    <form method="post" action="<%= request.getContextPath() %>/PartidoServlet?action=guardar">
        <!-- Jornada -->
        <div class="form-group">
            <label for="numeroJornada">Jornada</label>
            <input type="number" name="numeroJornada" id="numeroJornada" class="form-control" required>
        </div>

        <!-- Fecha -->
        <div class="form-group">
            <label for="fecha">Fecha</label>
            <input type="date" name="fecha" id="fecha" class="form-control" required>
        </div>

        <!-- Selección local -->
        <div class="form-group">
            <label for="seleccionLocal">Selección Local</label>
            <select name="seleccionLocal" id="seleccionLocal" class="form-control" required>
                <c:forEach var="seleccion" items="${selecciones}">
                    <option value="${seleccion.idSeleccion}">${seleccion.nombre}</option>
                </c:forEach>
            </select>
        </div>

        <!-- elección visitante -->
        <div class="form-group">
            <label for="seleccionVisitante">Selección Visitante</label>
            <select name="seleccionVisitante" id="seleccionVisitante" class="form-control" required>
                <c:forEach var="seleccion" items="${selecciones}">
                    <option value="${seleccion.idSeleccion}">${seleccion.nombre}</option>
                </c:forEach>
            </select>
        </div>

        <!-- arbitro -->
        <div class="form-group">
            <label for="arbitro">Árbitro</label>
            <select name="arbitro" id="arbitro" class="form-control" required>
                <c:forEach var="arbitro" items="${arbitros}">
                    <option value="${arbitro.idArbitro}">${arbitro.nombre}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Crear Partido</button>
        <a href="<%=request.getContextPath()%>/PartidoServlet" class="btn btn-danger">Cancelar</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" crossorigin="anonymous"></script>
</body>
</html>
