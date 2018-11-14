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
            background-color: green;
            height: 200vh;
        }
    </style>
    <body>
        <jsp:include page="Components/Navbar.jsp"/>
        <div id="section1">
            <img src="images/Home/TrackPantsWideFeature_lead_o0not9.jpg" width="100%">
        </div>
        <div id="sec2">
            <c:forEach items="${pdc}" var = "pdc" >
                <div>
                    <p>${pdc.productname}</p>
                    <p>${pdc.price}</p>
                    
                </div>
                
            </c:forEach>
            <c:forEach items="${pds}" var = "pds">
                <div>
                    <p>${pds.productname}</p>
                    <p>${pds.price}</p>
                    
                </div>
                
            </c:forEach>
            
        </div>
    </body>
</html>
