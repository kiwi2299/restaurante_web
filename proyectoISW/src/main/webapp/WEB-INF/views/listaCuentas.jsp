<%-- 
    Document   : listaCuentas
    Created on : 22/04/2021, 11:39:47 PM
    Author     : kiwir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Cuentas</title>
    </head>
    <body>

        <jsp:include page="${menu}"/> 
        <table class = "table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre del Cliente</th>
                    <th>Envio</th>
                    <th>Propina</th>
                    <th>Total</th>
                    <th>Fecha</th>
                    
                    <th colspan="2">Acciones  </th>

                </tr>
            </thead>
            <tbody>
                <c:forEach
                    var="dto"
                    items="${listaCuentas}">


                    <tr>
                        <td>
                            <a 
                                class ='btn btn-primary btn-xs'
                                href="ProductoServlet?accion=ver&id= <c:out value="${dto.entidad.idCuenta}"/>">
                                <c:out value="${dto.entidad.idCuenta}"/>
                            </a>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.nombreCliente}"/>
                        </td>
                        <td>
                            
                           
                            
                                <c:choose>
                                    <c:when test="${dto.entidad.idEnvio.idEnvio == null}">
                                        <form action="registrarEnvio">
                                            <button type="submit" class="btn btn-success">Registrar Envio</button></form>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${dto.entidad.idEnvio.calle}"/>
                                    </c:otherwise>
                                </c:choose>
                                
                            
                       
                        </td>
                        <td>
                            <c:out value="${dto.entidad.propina}"/>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.total}"/>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.fecha}"/>
                        </td>
                        
                        <td>
                            <form action="editarCuenta">
                                <input type="hidden" value="${dto.entidad.idCuenta}" name="id"/>
                                <button type="submit" class="btn btn-primary">Editar</button>
                            </form>
                        </td>
                        <td>
                            <form action="eliminarCuenta">
                                <input type="hidden" value="${dto.entidad.idCuenta}" name="id"/>
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
                <form action="reporteCuentas">
                    <button type="submit" class="btn btn-success">Ver Reporte</button>
                </form>
                <form action="graficaCuentas">
                    <button type="submit" class="btn btn-primary">Ver Grafica</button>
                </form>
            </div>

            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
