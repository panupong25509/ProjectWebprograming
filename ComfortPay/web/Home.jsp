<%-- 
    Document   : Home.jsp
    Created on : Nov 3, 2018, 10:38:46 PM
    Author     : crtiexx
--%>

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
            <img src="images/Home/bgFila.jpg" width="100%">
        </div>
        <div id="sec2"></div>
    </body>
</html>
