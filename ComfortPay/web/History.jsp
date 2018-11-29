<%-- 
    Document   : History.jsp
    Created on : Nov 29, 2018, 11:45:01 PM
    Author     : Joknoi
--%>

<%@page import="comfortpay.model.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="Components/Navbar.jsp"/>
        <div style="padding-top: 180px;">
            <div class="container">
                <div class="col-12 row m-0">
                    <div class="col-8 p-5" style="background-color: #53759A;color: #D5C4AD;">
                        <h1 class="text-center">History</h1>
                        <c:forEach items="${orders}" var="order">
                            <a href="Bill?ordercode=${order.ordercode}">
                                <button type="button" class="btn btn-warning m-1" style="width: 100%;">Order Code : ${order.ordercode}</button>
                            </a>
                        </c:forEach>  
                    </div>
                    <div class="col-4">
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

        </div>
    </body>
</html>
