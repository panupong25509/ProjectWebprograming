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
        #sec1 {
            background-color: black;
            height: 100vh;
        }
        #sec2 {
            background-color: green;
            height: 200vh;
        }
    </style>
    <body>
        <jsp:include page="Components/Navbar.jsp"/>
        <img src="bg.png" width="100%">
        <div id="sec2"></div>
    </body>
</html>
