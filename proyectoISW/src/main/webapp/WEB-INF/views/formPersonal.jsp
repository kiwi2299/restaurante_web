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
        <title>Login</title>
    </head>
    <body>
        <jsp:include page="${menu}"/>

        <div class="card-body">
            <form action="guardarPersonal" 
                  method="post"
                  name="formPersonal"
                  id="formPuesto">

                <div class="form-group row">
                    <input type="hidden" value="${dto.entidad.idPersonal}" name="idPersonal"/>
                    <input type="hidden" value="${dto.entidad.acceso.idAcceso}" name="idAcceso"/>
                    <label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
                    <div class="col-sm-6">
                        <input type="text"
                               name="nombre"
                               id="nombrePuesto"
                               maxlength="50"
                               required="required"
                               value="${dto.entidad.nombre}"
                               placeholder="Nombre"
                               class="form-control"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="paterno" class="col-sm-2 col-form-label">Apellido Paterno</label>
                    <div class="col-sm-6">
                        <input type="text"
                               name="paterno"
                               id="paterno"
                               maxlength="50"
                               required="required"
                               value="${dto.entidad.paterno}"
                               placeholder="Apellido Paterno"
                               class="form-control"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="materno" class="col-sm-2 col-form-label">Apellio Materno</label>
                    <div class="col-sm-6">
                        <input type="text"
                               name="materno"
                               id="materno"
                               maxlength="50"
                               required="required"
                               value="${dto.entidad.materno}"
                               placeholder="Apellido Materno"
                               class="form-control"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="fechaNacimiento" class="col-sm-2 col-form-label">Fecha de nacimiento</label>
                    <div class="col-sm-6">
                        <input type="date"
                               name="fechaNacimiento"
                               id="fechaNacimiento"
                               required="required"
                               value="${dto.entidad.fechaNacimiento}"
                               max ="${fecha}"
                               class="form-control"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="nombreUsuraio" class="col-sm-2 col-form-label">Nombre Usuario </label>
                    <div class="col-sm-6">
                        <input type="text"
                               name="nombreUsuario"
                               id="nombreUsuario"
                               maxlength="50"
                               required="required"
                               value="${dto.entidad.acceso.nombreUsuario}"
                               placeholder="Nombre de Usuario"
                               class="form-control"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password" class="col-sm-2 col-form-label">Contraseña</label>
                    <div class="col-sm-6">
                        <input type="password"
                               name="password"
                               id="password"
                               maxlength="50"
                               required="required"
                               value="${dto.entidad.acceso.clavePersonal}"
                               placeholder="Contraseña"
                               class="form-control"/>
                    </div>
                </div>

                <div class ="from-group row ">
                    <labe for="puesto" class ="col-sm-2 col-form-label">Puesto</labe>
                    <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example"
                            name="puesto"
                            id="puesto">
                        <c:choose>
                        <c:when test="${dto == null}">
                            <option selected>Selecciona una opcion</option>
                        </c:when>
                        <c:otherwise>
                            <option selected value="${dto.entidad.idPuesto.idPuesto}">${dto.entidad.idPuesto.nombrePuesto}</option>
                        </c:otherwise>
                        </c:choose>
                        <c:forEach items="${lista}"
                                   var="puesto">

                            <option value="${puesto.entidad.idPuesto}" > ${puesto.entidad.nombrePuesto}</option>
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
