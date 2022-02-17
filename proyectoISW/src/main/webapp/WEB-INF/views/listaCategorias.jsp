<%-- 
    Document   : listaPlatillos
    Created on : 22 apr 2021, 16:41:36
    Author     : metal
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Platillos</title>
    </head>
    <body>

        <jsp:include page="${menu}"/> 
        <table class = "table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Descripcion</th>
                    <th colspan="2">Acciones  </th>

                </tr>
            </thead>
            <tbody>
                <c:forEach
                    var="dto"
                    items="${listaCategorias}">


                    <tr>
                        <td>
                            <a 
                                class ='btn btn-primary btn-xs'
                                href="ProductoServlet?accion=ver&id= <c:out value="${dto.entidad.idCategoria}"/>">
                                <c:out value="${dto.entidad.idCategoria}"/>
                            </a>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.nombreCategoria}"/>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.descripcionCategoria}"/>
                        </td>
                        <td>
                            <form action="editarCategoria">
                                <input type="hidden" value="${dto.entidad.idCategoria}" name="id"/>
                                <button type="submit" class="btn btn-primary">Editar</button>
                            </form>
                        </td>
                        <td>
                        
                            <form action="eliminarCategoria">
                                <input type="hidden" value="${dto.entidad.idCategoria}" name="id"/>
                                <button type="submit" class="btn btn-danger">Eliminar</button>
                            </form>

                        </td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
            <c:choose>
                <c:when
                    test="${msg != null}">
                    <c:choose>
                        <c:when
                            test="${msgType == 1}">
                            <div class="alert alert-success" role ="alert">
                                ${msg}
                            </div>   
                        </c:when>
                        <c:otherwise>
                            <div class="alert alert-danger" role ="alert">
                                ${msg}
                            </div>   
                        </c:otherwise>
                    </c:choose>
                </c:when>

            </c:choose>   
            <div >
                <form action="reporteCategoria">
                    <button type="submit" class="btn btn-success">Ver Reporte</button>
                </form>
                <form action="graficaCategoria">
                    <button type="submit" class="btn btn-primary">Ver Grafica</button>
                </form>
            </div>

            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
