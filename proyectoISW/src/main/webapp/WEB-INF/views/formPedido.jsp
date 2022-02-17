<%-- 
    Document   : formPedido
    Created on : 23/04/2021, 12:35:19 PM
    Author     : kiwir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedidos</title>
    </head>
    <body>
        <jsp:include page="${menu}"/>

        <div class="card-body">
               
            
            <form action="guardarPedido" 
                  method="post"
                  name="formPedido"
                  id="formPedido">
                <div class ="from-group row ">
                 <c:forEach items="${listaPlatillosSeleccionados}"
                           var="lista"
                           varStatus="index">
                     <input type="hidden" name ="platillos" value="${lista.idPlatillo}"/>
                     <label for="prod_${index.index}" class ="col-sm-2 col-form-label">Producto</label>
                     <input type="text"  value="${lista.nombrePlatillo}"id="prod_${index.index}" readonly="true"/>

                </c:forEach>
                </div>
                <div class ="from-group row ">
                     <label for="platilloSeleccionado" class ="col-sm-2 col-form-label">Producto</label>
                     <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example"
                            name="platilloSeleccionado"
                            id="platilloSeleccionado">
                     <c:forEach items="${listaPlatillos}"
                                var="lista">
                         <option value="${lista.entidad.idPlatillo}">${lista.entidad.nombrePlatillo}</option>
                     </c:forEach>
                     </select> 
                </div>
                     <div class="form-group row">
                    <div class="col-sm-6">
                        
                        <input type="submit"
                               class="btn btn-primary"
                               name="accion"
                               value="Agregar Producto"/>
                    </div>
                </div>
                <div class="form-group row">
                    <input type="hidden" value="${dto.entidad.idPedido}" name="id" />
                    <input type="hidden" value="${mesero.idPersonal}" name="idMesero" />
                    <label for="nombreMesero" class="col-sm-2 col-form-label">Nombre del Mesero</label>
                    <div class="col-sm-6">
                        ${mesero.nombre}
                    </div>
                </div>

                <div class="form-group row">
                    <label for="numeroMesa" class="col-sm-2 col-form-label">Numero de Mesa</label>
                    <div class="col-sm-6">
                        <input type="number"
                               name="numeroMesa"
                               id="numeroMesa"
                               maxlength="10"
                               required="required"
                               value="${dto.entidad.numeroMesa}"
                               placeholder="1"
                               class="form-control"/>
                    </div>
                </div>

                <div class ="from-group row ">
                    <labe for="cuenta" class ="col-sm-2 col-form-label">Cuenta</labe>
                    <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example"
                            name="cuenta"
                            id="cuenta">
                        <c:choose>
                            <c:when test="${dto == null}">
                                <option selected>Selecciona una opcion</option>
                            </c:when>
                            <c:otherwise>
                                <option selected value="${dto.entidad.idCuenta.idCuenta}">${dto.entidad.idCuenta.nombreCliente}</option>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach items="${lista}"
                                   var="cuenta">

                            <option value="${cuenta.entidad.idCuenta}" > ${cuenta.entidad.nombreCliente}</option>
                        </c:forEach>
                    </select>

                </div>
                <c:choose>
                    <c:when test="${listaPlatillosSeleccionados != null}">
                <div class="form-group row">
                    <div class="col-sm-6">
                        <input type="submit"
                               class="btn btn-primary"
                               name="accion"
                               value="Registrar"/>
                    </div>
                </div>
                    </c:when>
                </c:choose>
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
