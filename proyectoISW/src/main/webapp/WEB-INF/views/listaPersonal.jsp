<%-- 
    Document   : listaPersonal
    Created on : 12 apr 2021, 15:28:24
    Author     : metal
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Puestos</title>
    </head>
    <body>

        <jsp:include page="${menu}"/> 
        <table class = "table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre Usuario</th>
                    <th>Nombre</th>
                    <th>Apellido Paterno</th>
                    <th>Apellido Materno</th>
                    <th>Fecha Nacimiento </th>
                    <th>Puesto  </th>
                    <th colspan="2">Acciones  </th>

                </tr>
            </thead>
            <tbody>
                <c:forEach
                    var="dto"
                    items="${listaPersonal}">


                    <tr>
                        <td>
                            <a 
                                class ='btn btn-primary btn-xs'
                                href="ProductoServlet?accion=ver&id= <c:out value="${dto.entidad.idPuesto}"/>">
                                <c:out value="${dto.entidad.idPersonal}"/>
                            </a>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.acceso.nombreUsuario}"/>
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
                            <c:out value="${dto.entidad.fechaNacimiento}"/>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.idPuesto.nombrePuesto}"/>
                        </td>
                        <td>
                            <form action="editarPersonal">
                                <input type="hidden" value="${dto.entidad.idPersonal}" name="id"/>
                                <button type="submit" class="btn btn-primary">Editar</button>
                            </form>
                        </td>
                        <td>
                            <form action="eliminarPersonal">
                                <input type="hidden" value="${dto.entidad.idPersonal}" name="id"/>
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
                <form action="reportePersonal">
                    <button type="submit" class="btn btn-success">Ver Reporte</button>
                </form>
                <form action="graficaPersonal">
                    <button type="submit" class="btn btn-primary">Ver Grafica</button>
                </form>
            </div>

            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
