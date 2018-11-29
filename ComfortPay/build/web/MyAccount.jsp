<%-- 
    Document   : MyAccount
    Created on : Nov 4, 2018, 4:23:15 AM
    Author     : crtiexx
--%>

<%@page import="comfortpay.model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <title>My Account</title>
    </head>
    <body>
        <jsp:include page="Components/Navbar.jsp"/>
        <div style="padding-top: 130px;">
            <div class="container">
                <div class="col-7 mx-auto text-center pd-5 mb-5" style="background-color: #53759A;color: #D5C4AD;padding: 50px;">
                    <h1>My Account</h1>
                    <img src="images/Home/User.png" height="200px;" class="mt-3">
                    <div class="text-center pt-5">

                        <h3>${account.fname} ${account.lname}</h3>
                        <br>
                        <button type="button" class="btn btn-warning" >STATUS : ${account.class1}</button>
                        <button type="button" class="btn btn-info">SCORE : ${account.score}</button>
                    </div>
                    <div class="col-12 mx-auto mt-3">
                        <%
                            Account account = (Account) session.getAttribute("account");
                            if (account.getClass1().equals("ADMIN")) {
                        %>
                        <a href="AddToStock"><button type="button" class="btn btn-light" style="width: 100%">ADD TO STOCK</button></a>
                        <% }%>
                        <a href="History"><button type="button" class="btn btn-light mt-1" style="width: 100% ">HISTORY</button></a>
                    </div>
                </div>

            </div>

        </div>
    </body>
</html>
