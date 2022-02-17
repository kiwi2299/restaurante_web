<%-- 
    Document   : menuMesero
    Created on : 22/04/2021, 07:44:04 PM
    Author     : kiwir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <a class="navbar-brand" href="#">
                <img src="resources/imagenes/restaurant.png" width="30" height="30" class="d-inline-block align-top" alt="" loading="lazy">
                Mi restaurante Web
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    
                    <a class="nav-link <c:choose><c:when test="${page == 'home'}">
                              active" href="#"
                        </c:when>
                        <c:otherwise>
                            " href="${pageContext.request.contextPath}/"
                        </c:otherwise> </c:choose>>Home</a>
                    
                        <a class="nav-link< c:choose><c:when test="${page == 'listaPlatillos'}">
                              active" href="#"
                        </c:when>
                        <c:otherwise>
                            " href="${pageContext.request.contextPath}/listaPlatillos"
                        </c:otherwise> </c:choose>>Lista Platillos</a>
                        
                        <a class="nav-link< c:choose><c:when test="${page == 'listaEnvios'}">
                              active" href="#"
                        </c:when>
                        <c:otherwise>
                            " href="${pageContext.request.contextPath}/listaEnvios"
                        </c:otherwise> </c:choose>>Lista Envios</a>
                        
                        <a class="nav-link< c:choose><c:when test="${page == 'registrarEnvio'}">
                              active" href="#"
                        </c:when>
                        <c:otherwise>
                            " href="${pageContext.request.contextPath}/registrarEnvio"
                        </c:otherwise> </c:choose>>Registrar Envio</a>
                        
                        <a class="nav-link< c:choose><c:when test="${page == 'listaPedidos'}">
                              active" href="#"
                        </c:when>
                        <c:otherwise>
                            " href="${pageContext.request.contextPath}/listaPedidos"
                        </c:otherwise> </c:choose>>Lista Pedidos</a>
                        
                        <a class="nav-link< c:choose><c:when test="${page == 'registrarPedido'}">
                              active" href="#"
                        </c:when>
                        <c:otherwise>
                            " href="${pageContext.request.contextPath}/registrarPedido"
                        </c:otherwise> </c:choose>>Registrar Pedido</a>
                        
                        <a class="nav-link< c:choose><c:when test="${page == 'listaCuentas'}">
                              active" href="#"
                        </c:when>
                        <c:otherwise>
                            " href="${pageContext.request.contextPath}/listaCuentas"
                        </c:otherwise> </c:choose>>Lista Cuentas</a>
                        
                        <a class="nav-link< c:choose><c:when test="${page == 'registrarCuenta'}">
                              active" href="#"
                        </c:when>
                        <c:otherwise>
                            " href="${pageContext.request.contextPath}/registrarCuenta"
                        </c:otherwise> </c:choose>>Registrar Cuenta</a>
                        <a class="nav-link" href="${pageContext.request.contentType}/cerrarSesion"> Cerrar sesion</a>
                </div>
            </div>
        </nav>
    </body>
</html>
