<%-- 
    Document   : formEnvio
    Created on : 22/04/2021, 08:21:38 PM
    Author     : kiwir
--%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Envios</title>
    </head>
    <body>
        <jsp:include page="${menu}"/>
        
        <div class="card-body">
                    <form action="guardarEnvio" 
                          method="post"
                          name="formEnvio"
                          id="formEnvio">

                        <div class="form-group row">
                            <input type="hidden" value="${dto.entidad.idEnvio}" name="id" />
                            <label for="calle" class="col-sm-2 col-form-label">Calle</label>
                            <div class="col-sm-6">
                                <input type="text"
                                       name="calle"
                                       id="calle"
                                       maxlength="50"
                                       required="required"
                                       value="${dto.entidad.calle}"
                                       placeholder="Calle"
                                       class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="numeroCalle" class="col-sm-2 col-form-label">Número</label>
                            <div class="col-sm-6">
                                <input type="text"
                                       name="numeroCalle"
                                       id="numeroCalle"
                                       maxlength="5"
                                       required="required"
                                       value="${dto.entidad.calle}"
                                       placeholder="1"
                                       class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="numeroInterior" class="col-sm-2 col-form-label">Número Interior</label>
                            <div class="col-sm-6">
                                <input type="text"
                                       name="numeroInterior"
                                       id="numeroInterior"
                                       maxlength="5"
                                       value="${dto.entidad.numeroInterior}"
                                       placeholder="A"
                                       class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="colonia" class="col-sm-2 col-form-label">Colonia</label>
                            <div class="col-sm-6">
                                <input type="text"
                                       name="colonia"
                                       id="colonia"
                                       maxlength="32"
                                       required="required"
                                       value="${dto.entidad.colonia}"
                                       placeholder="Colonia"
                                       class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="codigoPostal" class="col-sm-2 col-form-label">Código Postal</label>
                            <div class="col-sm-6">
                                <input type="text"
                                       name="codigoPostal"
                                       id="codigoPostal"
                                       maxlength="6"
                                       required="required"
                                       value="${dto.entidad.codigoPostal}"
                                       placeholder="20100"
                                       class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="municipio" class="col-sm-2 col-form-label">Municipio</label>
                            <div class="col-sm-6">
                                <input type="text"
                                       name="municipio"
                                       id="municipio"
                                       maxlength="30"
                                       required="required"
                                       value="${dto.entidad.municipio}"
                                       placeholder="Municipio"
                                       class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="estado" class="col-sm-2 col-form-label">Estado</label>
                            <div class="col-sm-6">
                                <input type="text"
                                       name="estado"
                                       id="estado"
                                       maxlength="20"
                                       required="required"
                                       value="${dto.entidad.estado}"
                                       placeholder="Estado"
                                       class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="pais" class="col-sm-2 col-form-label">Pais</label>
                            <div class="col-sm-6">
                                <input type="text"
                                       name="pais"
                                       id="pais"
                                       maxlength="30"
                                       required="required"
                                       value="${dto.entidad.pais}"
                                       placeholder="Pais"
                                       class="form-control"/>
                            </div>
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

