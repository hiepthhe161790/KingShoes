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
            // Gọi hàm setPage(1) sau khi trang được tải
            window.onload = function () {
                setPage(1);
            };
        </script>
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
            var page = 1; // Biến page ban đầu là 1

            function setPage(pageNumber) {
                page = pageNumber; // Cập nhật giá trị của biến 'page' với số trang được nhấp vào
                displayAccounts(); // Gọi hàm hiển thị danh sách tài khoản với điều kiện mới
            }

            function displayAccounts() {
                var accounts = document.querySelectorAll("tbody tr"); // Lấy danh sách tất cả các tài khoản

                accounts.forEach(function (account, index) {
                    if ((page - 1) * 5 <= index && index < page * 5 && index < ${listA.size()}) {
                        account.style.display = "table-row"; // Hiển thị tài khoản nếu thỏa mãn điều kiện
                    } else {
                        account.style.display = "none"; // Ẩn tài khoản nếu không thỏa mãn điều kiện
                    }
                });
            }
        </script>
        <script>
            var currentPage = 1; // Biến currentPage ban đầu là 1

            function nextPage() {
                currentPage++; // Tăng giá trị của currentPage lên 1
                setPage(currentPage); // Gọi hàm setPage với giá trị trang mới
            }
            function previousPage() {
                if (currentPage > 1) {
                    currentPage--; // Giảm giá trị của currentPage đi 1
                    setPage(currentPage); // Gọi hàm setPage với giá trị trang mới
                }
            }
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
                                <a href="#addAccountModal" class="btn btn-secondary" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New User</span></a>
                                <a href="export" class="btn bAllAccounttn-secondary"><i class="material-icons">&#xE24D;</i> <span>Export to Excel</span></a>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Sell</th>
                                <th>Admin</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listA}" var="o" varStatus="loop">
                                <c:set var="page" value="${((loop.index / 5) + 1)}" />
                                <c:if test="${(page-1)*5 <= loop.index && loop.index < page*5 && loop.index < listA.size()}">
                                    <tr>
                                        <td>${o.id}</td>
                                        <td><a href="#"><img src="/examples/images/avatar/1.jpg" class="avatar" alt="Avatar">${o.user}</a></td>
                                        <td>${o.pass}</td>

                                        <td>
                                            <span class="status text-success">
                                                <c:if test="${o.isSell eq 1}">True</c:if>
                                                <c:if test="${o.isSell eq 0}">False</c:if>
                                                </span>
                                            <td>
                                                <span class="status text-success">
                                                <c:if test="${o.isAdmin eq 1}">True</c:if>
                                                <c:if test="${o.isAdmin eq 0}">False</c:if>
                                                </span>
                                            </td>
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
                                            <a href="loadAccount?aid=${o.id}" class="settings" title="Settings" data-toggle="tooltip"><i class="material-icons">&#xE8B8;</i></a>
                                            <a href=deleteAccount?aid=${o.id} class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE5C9;</i></a>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="clearfix">
                        <div class="hint-text">Showing <b>5</b> out of <b>${listA.size()}</b> entries</div>
                        <ul class="pagination">
                            <li class="page-item"><a href="#" class="page-link" onclick="previousPage()">Previous</a></li>
                            <li class="page-item active"><a href="#" class="page-link" onclick="setPage(1)">1</a></li>
                            <li class="page-item"><a href="#" class="page-link" onclick="setPage(2)">2</a></li>
                            <li class="page-item"><a href="#" class="page-link" onclick="setPage(3)">3</a></li>
                            <li class="page-item"><a href="#" class="page-link" onclick="setPage(4)">4</a></li>
                            <li class="page-item"><a href="#" class="page-link" onclick="setPage(5)">5</a></li>
                            <li class="page-item"><a href="#" class="page-link" onclick="setPage(6)">6</a></li>
                            <li class="page-item"><a href="#" class="page-link" onclick="setPage(7)">7</a></li>
                            <li class="page-item"><a href="#" class="page-link" onclick="nextPage()">Next</a></li>

                        </ul>
                    </div>
                </div>
            </div>
            <button type="button" class="btn btn-primary" onclick="location.href = 'home'">Back to home</button>
        </div>

        <div id="addAccountModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="AddAccount" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Account</h4>
                             <button type="button" class="btn btn-primary" onclick="location.href='ManagerAccount'">x</button>	
                            <!--<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="window.location.href = 'manager'">&times;</button>-->
                            <!--                                   <a href="ManagerAccount"><button class="close" data-dismiss="modal" aria-hidden="true" class="btn btn-primary">x</button>-->
                        </div>
                        <div class="modal-body">					                              
                            <div class="form-group">
                                <label>User</label>
                                <input name="user" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Pass</label>
                                <input name="pass" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Seller</label>
                                <input name="isSell" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Adminer</label>
                                <input name="isSell" type="text" class="form-control" required>
                            </div>


                        </div>
                        <div class="modal-footer">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>

    <!--
    -->        <script src="js/manager.js" type="text/javascript"></script>
</body>
</html>
