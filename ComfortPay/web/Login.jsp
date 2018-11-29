<%-- 
    Document   : Login.jsp
    Created on : Nov 4, 2018, 4:03:49 AM
    Author     : crtiexx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>Login Comfort Pay</title>
    </head>
    <body>
        <jsp:include page="Components/Navbar.jsp"/>
        <div class="col-6 mx-auto" style="padding-top: 130px;">
                <form class="modal-content animate" action="Login" style="background-color: #53759A;color: #D5C4AD;">
                    <div class="container" style="font-size: 18px;">
                        <h1 class="text-center">Login</h1>
                        <label for="uname">Username</label>
                        <input type="text" class="form-control" placeholder="Enter Username" name="username" required>
                        <!--<br>-->
                        <label for="psw">Password</label>
                        <input type="password" class="form-control" placeholder="Enter Password" name="password" required>
                        
                        <input type="hidden" name="path" value="<%= request.getRequestURL()%>">
                        <input type="hidden" name="productid" value="${product.productid}">
                        <br>
                        <button type="button submit" class="btn mb-3" style="font-size: 16px;width: 100%;background-color:#D5C4AD;color: #53759A"><b>Login</b></button>
                    </div>
                </form> 
            </div>
    </body>
</html>
