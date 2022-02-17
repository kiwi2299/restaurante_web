

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
                    <form action="guardarPuesto" 
                          method="post"
                          name="formPuesto"
                          id="formPuesto">

                        <div class="form-group row">
                            <input type="hidden" value="${dto.entidad.idPuesto}" name="id" />
                            <label for="nombrePuesto" class="col-sm-2 col-form-label">Puesto</label>
                            <div class="col-sm-6">
                                <input type="text"
                                       name="nombrePuesto"
                                       id="nombrePuesto"
                                       maxlength="50"
                                       required="required"
                                       value="${dto.entidad.nombrePuesto}"
                                       placeholder="Nombre del puesto"
                                       class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="descripcionPuesto" class="col-sm-2 col-form-label">Descripcion</label>
                            <div class="col-sm-6">
                                <textarea
                                    class ="form-control"
                                    id ="descripcionPuesto"
                                    name="descripcionPuesto"
                                    rows ="4"
                                    required="required"
                                    maxlength="255"
                                    >${dto.entidad.descripcionPuesto}</textarea>
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
