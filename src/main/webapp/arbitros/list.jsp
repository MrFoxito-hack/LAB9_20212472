<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>



<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />
    <title>Lista de Árbitros</title>
</head>




<body>

<jsp:include page="/includes/navbar.jsp"/>


<div class='container'>
    <div class="row mb-5 mt-4">
        <div class="col-lg-6">
            <h1 class=''>Lista de Árbitros</h1>
        </div>
        <div class="col-lg-6 my-auto text-lg-right">
            <a href="<%= request.getContextPath() %>/ArbitroServlet?action=crear" class="btn btn-primary">Crear Árbitro</a>
        </div>
    </div>

    <!-- Formulario de búsqueda -->
    <form method="post" action="<%= request.getContextPath() %>/ArbitroServlet?action=buscar" class="row mb-4">
        <div class="col-lg-3">
            <select name="tipo" class="form-control">
                <option value="nombre" <%= "nombre".equals(request.getParameter("tipo")) ? "selected" : "" %>>Nombre</option>
                <option value="pais" <%= "pais".equals(request.getParameter("tipo")) ? "selected" : "" %>>País</option>
            </select>
        </div>
        <div class="col-lg-5">
            <input type="text" class="form-control" name="buscar" value="<%= request.getParameter("buscar") != null ? request.getParameter("buscar") : "" %>">
        </div>
        <div class="col-lg-2">
            <button type="submit" class="btn btn-primary">Buscar</button>
        </div>
        <div class="col-lg-2">
            <a href="<%= request.getContextPath() %>/ArbitroServlet" class="btn btn-danger">Limpiar Búsqueda</a>
        </div>
    </form>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <!-- Tabla de arbitros -->
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Nombre</th>
            <th>País</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty arbitros}">
                <c:forEach var="arbitro" items="${arbitros}">
                    <tr>
                        <td>${arbitro.idArbitro}</td>
                        <td>${arbitro.nombre}</td>
                        <td>${arbitro.pais}</td>
                        <td>



                            <!-- Form para borrar el arbitrooo -->
                            <form method="post" action="<%= request.getContextPath() %>/ArbitroServlet?action=borrar">
                                <input type="hidden" name="id" value="${arbitro.idArbitro}">
                                <button type="submit" class="btn btn-danger">Borrar</button>
                            </form>



                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="4" class="text-center">No hay arbtrios disponibles</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
</body>
</html>
