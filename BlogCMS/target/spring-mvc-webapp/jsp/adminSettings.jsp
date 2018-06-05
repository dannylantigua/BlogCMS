<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
    </head>
    <body>

        <div class="container">

            <h1>
                Admin Settings
            </h1>



            <hr>


            <h2>Admins</h2>      
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>UserName</th>
                        <th>FirstName</th>
                        <th>LastName</th>
                        <th>Remove Account</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="currentEntity" items="${listOfEntities}">

                        <tr>
                            <td><c:out value="${currentEntity.userName}"/></td>
                            <td><c:out value="${currentEntity.firstName}"/></td>
                            <td><c:out value="${currentEntity.lastName}"/></td>
                            <td>
                                <c:if test="${pageContext.request.userPrincipal.name != currentEntity.userName}">
                                    <a href="deleteUserAsAdmin?userId=${currentEntity.recordId}"> delete </a>

                                </c:if>
                                <c:if test="${pageContext.request.userPrincipal.name == currentEntity.userName}">
                                    <b>Current User</b>
                                </c:if>
                            </td>
                            <td>
                                <%--<c:if test="${pageContext.request.userPrincipal.name != currentEntity.userName}">--%>
                                    <!--<a href="promoteUserRole?userId=${currentEntity.recordId}"> Promote Role To Admin </a>-->
                                <%--</c:if>--%>
                            </td>


                        </tr>


                    </c:forEach>


                </tbody>
            </table>

            <br>

            <hr>

            <h2>Users</h2> 

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>UserName</th>
                        <th>FirstName</th>
                        <th>LastName</th>
                        <th>Remove Account</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="currentUser" items="${users}">

                        <tr>
                            <td><c:out value="${currentUser.userName}"/></td>
                            <td><c:out value="${currentUser.firstName}"/></td>
                            <td><c:out value="${currentUser.lastName}"/></td>
                            <td>
                                <c:if test="${pageContext.request.userPrincipal.name != currentUser.userName}">
                                    <a href="deleteUserAsAdmin?userId=${currentUser.recordId}"> delete </a>

                                </c:if>
                                <c:if test="${pageContext.request.userPrincipal.name == currentUser.userName}">
                                    <b>Current User</b>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${pageContext.request.userPrincipal.name != currentUser.userName}">
                                    <a href="promoteUserRole?userId=${currentUser.recordId}"> Promote Role To Admin </a>
                                </c:if>
                            </td>


                        </tr>


                    </c:forEach>


                </tbody>
            </table>


            Company Settings
        </h1>

        <p>form and add logo for company goes here</p>






        <hr>
        
        <h1>Sign Up a User</h1>

        <form role="form" method="POST" action="createUser">
            <div class="form-group">
                <label for="email">Email address</label>
                <input type="email" class="form-control" id="email" aria-describedby="emailHelp" name="email" placeholder="Enter email">
            </div>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
            </div>
            <div class="form-group">
                <label for="first">First Name</label>
                <input type="text" class="form-control" id="first" name="firstname" placeholder="First Name" required>
            </div>
            <div class="form-group">
                <label for="last">Last Name</label>
                <input type="text" class="form-control" id="last" name="lastname" placeholder="Last Name" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
            </div>
            <!--                <div class="form-group">
                                <label for="retypePassword">Retype Password</label>
                                <input type="password" class="form-control" id="retypePassword" name="password-repeat" placeholder="Password" required>
                            </div>-->
            <div class="form-group">
                <label for="phone">Phone Number</label>
                <input type="tel" class="form-control" id="phone" name="phone" placeholder="Phone Number" required>
            </div>
            
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>

        <hr>

        <a class="btn btn-primary" href="${pageContext.request.contextPath}/dashboard"role="button">Back to dashboard</a>
    </div>


    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
