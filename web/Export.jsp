<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Bootstrap User Management Data Table</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link href="css/ManageAccount.css" rel="stylesheet" type="text/css"/>
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
    </head>
    <body>
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-5">
                                <h2>User <b>Management</b></h2>
                            </div>
                            <div class="col-sm-7">
                               <button type="button" class="btn btn-primary" onclick="location.href='ManagerAccount'">x</button>						
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Usename</th>						
                                <th>Password</th>
                                <th>Sell</th>
                                <th>Admin</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                         <tbody>
                          <c:forEach items="${listA}" var="o">
    <tr>
        <td>${o.id}</td>
        <td><a href="#"><img src="/examples/images/avatar/1.jpg" class="avatar" alt="Avatar">  ${o.user}</a></td>
        <td>${o.pass}</td>  
         <td>
            <span class="status text-success">
                <c:if test="${o.isAdmin eq 1}">True</c:if>
                <c:if test="${o.isAdmin eq 0}">False</c:if>
            </span>
        </td>
        <td>
            <span class="status text-success">
                <c:if test="${o.isSell eq 1}">True</c:if>
                <c:if test="${o.isSell eq 0}">False</c:if>
            </span>
        </td>
        
        <td>
            <c:choose>
                <c:when test="${o.isAdmin eq 1}">
                    <span class="status text-success">&bull;</span>
                </c:when>
                <c:when test="${o.isSell eq 1 and o.isAdmin eq 0}">
                    <span class="status text-warning">&bull;</span>
                </c:when>
                <c:otherwise>
                    <span class="status text-danger">&bull;</span>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${o.isAdmin eq 1}">Admin</c:when>
                <c:when test="${o.isSell eq 1 and o.isAdmin eq 0}">Sell</c:when>
                <c:otherwise>User</c:otherwise>
            </c:choose>
        </td>
        <td>
             
        </td>
    </tr>
</c:forEach>
                        </tbody>
                    </table>
                    <div class="clearfix">
                        <div class="hint-text">Showing <b>${listA.size()}</b> out of <b>${listA.size()}</b> entries</div>                      
                    </div>
                </div>
            </div>
                                           
        </div>     
    </body>
</html>