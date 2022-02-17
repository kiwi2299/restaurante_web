<%-- 
    Document   : listaPedidos
    Created on : 23/04/2021, 12:33:16 PM
    Author     : kiwir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Pedidos</title>
    </head>
    <body>

        <jsp:include page="${menu}"/> 
        <table class = "table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>ID Cuenta</th>
                    <th>ID Mesero</th>
                    <th>Numero de Mesa</th>
                    <th>Platillos</th>
                    
                    <th colspan="2">Acciones  </th>

                </tr>
            </thead>
            <tbody>
                <c:forEach
                    var="dto"
                    items="${listaPedidos}">


                    <tr>
                        <td>
                            <a 
                                class ='btn btn-primary btn-xs'
                                href="ProductoServlet?accion=ver&id= <c:out value="${dto.entidad.idPedido}"/>">
                                <c:out value="${dto.entidad.idPedido}"/>
                            </a>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.idCuenta.idCuenta}"/>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.idPersonal.idPersonal}"/>
                        </td>
                        <td>
                            <c:out value="${dto.entidad.numeroMesa}"/>
                        </td>
                        <td>
                            <c:forEach items="${dto.entidad.listaPlatillosPedido}" var="platillo">
                                     
                                    <c:out value="${platillo.nombrePlatillo}"/>
                                    
                                
                            </c:forEach>

                            
                        </td>
                        <td>
                            <c:out value="${dto.entidad.idCuenta.fecha}"/>
                        </td>
                        
                        <td>
                            <form action="editarPedido">
                                <input type="hidden" value="${dto.entidad.idPedido}" name="id"/>
                                <button type="submit" class="btn btn-primary">Editar</button>
                            </form>
                        </td>
                        <td>
                            <form action="eliminarPedido">
                                <input type="hidden" value="${dto.entidad.idPedido}" name="id"/>
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
                <form action="reportePedidos">
                    <button type="submit" class="btn btn-success">Ver Reporte</button>
                </form>
                <form action="graficaPedidos">
                    <button type="submit" class="btn btn-primary">Ver Grafica</button>
                </form>
            </div>

            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>

