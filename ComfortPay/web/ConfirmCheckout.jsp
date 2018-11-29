<%-- 
    Document   : ComfirmCheckout
    Created on : Nov 29, 2018, 10:15:28 PM
    Author     : Joknoi
--%>

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
        <div style="padding-top: 150px;">
            <div class="col-10 mx-auto mb-5" style="background-color: #53759A;color: #D5C4AD;padding: 50px;">

                <h1 class="text-center mb-5">Payment</h1>
                <div class="col-12 row m-0">
                    <div class="col-6 text-center">
                        <h3>Address</h3>
                        <c:forEach items="${address}" var="address">
                            <div class="btn btn-warning">${address.address} ${address.distict} ${address.province} ${address.postcode}</div>
                        </c:forEach>
                    </div>
                    <div class="col-6 text-center">
                        <div class="bg-warning p-5 text-dark" style="border-radius: 8px;">
                        <h3>New Address</h3>
                        <div>
                            <p>Address :<input class="form-control" type="text" placeholder="Enter Address" name="address" required></p>
                        </div>
                        <div>
                            <p>District :<input class="form-control" type="text" placeholder="Enter District" name="district" required></p>
                        </div>
                        <div>
                            <p>Province :<input class="form-control" type="text" placeholder="Enter Province" name="province" required></p>
                        </div>
                        <div>
                            <p>Postcode :<input class="form-control" type="text" placeholder="Enter Postcode" name="postcode" required></p>
                        </div>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="col-8 mx-auto">
                        <p>Card Type
                            <select class="ml-5 btn dropdown-toggle">
                                <option>VISA</option>
                                <option>MASTER CARD</option>
                            </select>
                        </p>
                        <p>Card Number<input class="form-control" type="text"></p>
                        <p>Exp<input class="form-control" type="date"></p>
                        <p>CVV/CVV2<input class="form-control" type="text"></p>
                        <a href="Confirm"><button type="button" class="btn btn-success" style="width: 100%;">Confirm</button></a>
                    </div>
                    
                
                
            </div>
        </div>

    </body>
</html>