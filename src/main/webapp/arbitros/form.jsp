<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />
    <title>Crear Árbitro</title>
</head>
<body>
<jsp:include page="/includes/navbar.jsp"/>
<div class="container">
    <h1>Crear Árbitro</h1>

    <form method="post" action="<%= request.getContextPath() %>/ArbitroServlet?action=guardar">
        <!-- Nombre -->
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input type="text" name="nombre" id="nombre" class="form-control" value="${param.nombre}" required>
        </div>

        <!-- País -->
        <div class="form-group">
            <label for="pais">País</label>
            <select name="pais" id="pais" class="form-control" required>
                <c:forEach var="pais" items="${paises}">
                    <option value="${pais}">${pais}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Guardar</button>
        <a href="<%=request.getContextPath() %>/ArbitroServlet?action=lista" class="btn btn-danger">Cancelar</a>
    </form>

    <c:if test="${not empty error}">
        <div class="alert alert-danger mt-3">${error}</div>
    </c:if>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" crossorigin="anonymous"></script>
</body>
</html>
