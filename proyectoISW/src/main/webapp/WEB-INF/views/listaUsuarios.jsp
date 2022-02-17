<%-- 
    Document   : index
    Created on : 30 apr 2021, 14:38:46
    Author     : metal
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Usuarios</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <a class="navbar-brand" href="#">
                <img src="resources/imagenes/icon.svg" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">
                Bootstrap
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link active" href="#">Home <span class="sr-only">(current)</span></a>
                    <a class="nav-link" href="#">Features</a>
                    <a class="nav-link" href="#">Pricing</a>
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                </div>
            </div>
        </nav>
        
        <table class = "table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre Usuario</th>
                    <th>Email</th>
                    <th>Nombre</th>
                    <th>Apellido Paterno</th>
                    <th>Apellido Materno</th>
                    <th>Telefono</th>
                    <th>Tipo</th>
                    
                </tr>
            </thead>
            <tbody>
                <c:forEach
                    var="dto"
                    items="${listaUsuarios}">


                    <tr>
                        <td>
                            <a 
                                class ='btn btn-primary btn-xs'
                                href="ProductoServlet?accion=ver&id=<c:out value="${dto.entidad.idUsuario}"/>">
                                <c:out value="${dto.entidad.idUsuario}"/>
                            </a>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.nombreUsuario}"/>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.email}"/>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.nombre}"/>
                        </td>
                        <td>
                        <c:out value="${dto.entidad.paterno}"/>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.materno}"/>
                        </td>
                        
                        <td>
                            <c:out value="${dto.entidad.telefono}"/>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.tipo}"/>
                        </td>
                        
                    </tr>
                </c:forEach>
            </tbody>



        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
