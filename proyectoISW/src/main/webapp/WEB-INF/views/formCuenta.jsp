<%-- 
    Document   : formCuenta
    Created on : 22/04/2021, 11:48:51 PM
    Author     : kiwir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cuentas</title>
    </head>
    <body>
        <jsp:include page="${menu}"/>
        
        <div class="card-body">
                    <form action="guardarCuenta" 
                          method="post"
                          name="formCuenta"
                          id="formCuenta">

                        <div class="form-group row">
                            <input type="hidden" value="${dto.entidad.idCuenta}" name="id" />
                            <!--<input type="hidden" value="0" name="envio" />-->
                            <label for="nombreCliente" class="col-sm-2 col-form-label">Nombre del Cliente</label>
                            <div class="col-sm-6">
                                <input type="text"
                                       name="nombreCliente"
                                       id="nombreCliente"
                                       maxlength="50"
                                       required="required"
                                       value="${dto.entidad.nombreCliente}"
                                       placeholder="Nombre"
                                       class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="propina" class="col-sm-2 col-form-label">Propina</label>
                            <div class="col-sm-6">
                                <input type="number"
                                       name="propina"
                                       id="propina"
                                       maxlength="10"
                                       required="required"
                                       value="${dto.entidad.propina}"
                                       placeholder="10.0"
                                       class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="total" class="col-sm-2 col-form-label">Total</label>
                            <div class="col-sm-6">
                                <input type="text"
                                       name="total"
                                       id="total"
                                       maxlength="5"
                                       value="${dto.entidad.total}"
                                       placeholder="100.0"
                                       class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="fecha" class="col-sm-2 col-form-label">Fecha</label>
                            <div class="col-sm-6">
                                <input type="date"
                                       name="fecha"
                                       id="fecha"
                                       required="required"
                                       value="${dto.entidad.fecha}"
                                       class="form-control"/>
                            </div>
                        </div>
                        
                       <div class ="from-group row ">
                            <label for="envio" class ="col-sm-2 col-form-label">Envio</label>
                            <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example"
                                    name="envio"
                                    id="envio">
                                <c:choose>
                                <c:when test="${dto == null}">
                                    <option selected>Sin envio</option>
                                </c:when>
                                <c:otherwise>
                                    <option selected value="${dto.entidad.idEnvio.idEnvio}">${dto.entidad.idEnvio.calle}</option>
                                </c:otherwise>
                                </c:choose>
                                <c:forEach items="${lista}"
                                           var="envio">

                                    <option value="${envio.entidad.idEnvio}" > ${envio.entidad.calle}</option>
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
