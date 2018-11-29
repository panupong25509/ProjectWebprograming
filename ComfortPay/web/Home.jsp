<%-- 
    Document   : Home.jsp
    Created on : Nov 3, 2018, 10:38:46 PM
    Author     : crtiexx
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comfort Pay</title>
    </head>
    <style>
        body {
            margin: 0;
        }
        #section1 {
            min-height: 100vh;
            padding-top: 30px;
        }
        #sec2 {
            background-color: white;
            height: 200vh;
        }
    </style>
    <body>
        <jsp:include page="Components/Navbar.jsp"/>
        <div id="section1">
            <img src="images/Home/TrackPantsWideFeature_lead_o0not9.jpg" width="100%">
        </div>
        <div id="sec2" class="pt-5">
            <div class="container">
                <div class="col-12">
                    <div class="row">
                        <c:forEach items="${products}" var = "product" >
                            <a href="Product?productid=${product.productid}" class="col-4 p-3 text-center">
                                <div >
                                    <button style="background-color: #ECEEF0; border: none">
                                        <div style="background-color: transparent;cursor: pointer">
                                            <img src="images/PictureWebPro/adidasMencCloth/${product.productcode}.jpg" width="100%">
                                            <p>${product.productname}</p>
                                            <p>${product.price} BAHT</p>
                                        </div>
                                    </button>
                                </div>
                            </a>
                        </c:forEach>
                    </div>
                </div>               
            </div>
        </div>
    </body>
</html>
