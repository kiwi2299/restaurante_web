<%-- 
    Document   : listaEnvios
    Created on : 22/04/2021, 08:05:33 PM
    Author     : kiwir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Envios</title>
    </head>
    <body>

        <jsp:include page="${menu}"/> 
        <table class = "table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Calle</th>
                    <th>Número</th>
                    <th>Interior</th>
                    <th>Colonia</th>
                    <th>Código Postal</th>
                    <th>Municipio</th>
                    <th>Estado</th>
                    <th>Pais</th>
                    <th colspan="2">Acciones  </th>

                </tr>
            </thead>
            <tbody>
                <c:forEach
                    var="dto"
                    items="${listaEnvios}">


                    <tr>
                        <td>
                            <a 
                                class ='btn btn-primary btn-xs'
                                href="ProductoServlet?accion=ver&id= <c:out value="${dto.entidad.idEnvio}"/>">
                                <c:out value="${dto.entidad.idEnvio}"/>
                            </a>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.calle}"/>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.numeroCalle}"/>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.numeroInterior}"/>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.colonia}"/>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.codigoPostal}"/>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.municipio}"/>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.estado}"/>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.pais}"/>
                        </td>
                        <td>
                            <form action="editarEnvio">
                                <input type="hidden" value="${dto.entidad.idEnvio}" name="id"/>
                                <button type="submit" class="btn btn-primary">Editar</button>
                            </form>
                        </td>
                        <td>
                            <form action="eliminarEnvio">
                                <input type="hidden" value="${dto.entidad.idEnvio}" name="id"/>
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
                <form action="reporteEnvios">
                    <button type="submit" class="btn btn-success">Ver Reporte</button>
                </form>
                <form action="graficaEnvios">
                    <button type="submit" class="btn btn-primary">Ver Grafica</button>
                </form>
            </div>

            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
