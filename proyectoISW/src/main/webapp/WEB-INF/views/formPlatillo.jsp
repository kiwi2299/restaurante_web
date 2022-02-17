<%-- 
    Document   : formPersonal
    Created on : 12 apr 2021, 13:16:40
    Author     : metal
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Platillo</title>
    </head>
    <body>
        <jsp:include page="${menu}"/>

        <div class="card-body">
            <form action="guardarPlatillo" 
                  method="post"
                  name="formPlatillo"
                  id="formPlatillo">

                <div class="form-group row">
                    <input type="hidden" value="${dto.entidad.idPlatillo}" name="idPlatillo"/>
                    <label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
                    <div class="col-sm-6">
                        <input type="text"
                               name="nombre"
                               id="nombrePlatillo"
                               maxlength="50"
                               required="required"
                               value="${dto.entidad.nombrePlatillo}"
                               placeholder="Nombre"
                               class="form-control"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="descripcionPlatillo" class="col-sm-2 col-form-label">Descripcion</label>
                    <div class="col-sm-6">
                        <textarea
                            class ="form-control"
                            id ="descripcionPlatillo"
                            name="descripcion"
                            rows ="4"
                            required="required"
                            maxlength="255"
                            >${dto.entidad.descripcionPlatillo}</textarea>
                    </div>
                </div>              

                <div class="form-group row">
                    <label for="precioPlatillo" class="col-sm-2 col-form-label">Precio</label>
                    <div class="col-sm-6">
                        <input type="number"
                               name="precio"
                               id="precioPlatillo"
                               required="required"
                               value="${dto.entidad.precioPlatillo}"
                               placeholder="100.00"
                               class="form-control"/>
                    </div>
                </div>



                <div class ="from-group row ">
                    <label for="categoria" class ="col-sm-2 col-form-label">Categoria</label>
                    <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example"
                            name="categoria"
                            id="categoria">
                        <c:choose>
                            <c:when test="${dto == null}">
                                <option selected>Selecciona una opcion</option>
                            </c:when>
                            <c:otherwise>
                                <option selected value="${dto.entidad.idCategoria.idCategoria}">${dto.entidad.idCategoria.nombreCategoria}</option>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach items="${lista}"
                                   var="categoria">

                            <option value="${categoria.entidad.idCategoria}" > ${categoria.entidad.nombreCategoria}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group row">
                    <div class="col-sm-6">
                        <input type="submit"
                               class="btn btn-primary"
                               value="Registrar"/>
                    </div>
                </div>

            </form>
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
        </div>



        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    </body>
</html>
