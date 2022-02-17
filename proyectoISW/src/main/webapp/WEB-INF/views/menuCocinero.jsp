<%-- 
    Document   : menuCocinero
    Created on : 22 apr 2021, 16:29:11
    Author     : metal
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
                Mi Restaurante Web
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
                        
                        
                        <a class="nav-link< c:choose><c:when test="${page == 'listaCategorias'}">
                              active" href="#"
                        </c:when>
                        <c:otherwise>
                            " href="${pageContext.request.contextPath}/listaCategorias"
                        </c:otherwise> </c:choose>>Lista Categorias</a>
                        
                        
                        
                        <a class="nav-link< c:choose><c:when test="${page == 'registrarPlatillo'}">
                              active" href="#"
                        </c:when>
                        <c:otherwise>
                            " href="${pageContext.request.contextPath}/registrarPlatillo"
                        </c:otherwise> </c:choose>>Registrar Platillo</a>
                        
                        <a class="nav-link< c:choose><c:when test="${page == 'registrarCategoria'}">
                              active" href="#"
                        </c:when>
                        <c:otherwise>
                            " href="${pageContext.request.contextPath}/registrarCategoria"
                        </c:otherwise> </c:choose>>Registrar Categoria</a>
                        <a class="nav-link" href="${pageContext.request.contentType}/cerrarSesion"> Cerrar sesion</a>

                </div>
            </div>
        </nav>
    </body>
</html>
