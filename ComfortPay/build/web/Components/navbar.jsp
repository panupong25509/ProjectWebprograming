<%-- 
    Document   : navbar
    Created on : Nov 2, 2018, 9:46:30 AM
    Author     : INT303
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <title>JSP Page</title>
    </head>
    <style>
        #navbar {
            display: block;
        }
        #navbar .menu {
            display: inline-block;
        }
    </style>
    <body>
        <div class='container navbar'>
            <ul class="nav menu">
                <li class="nav-item">
                    <a class="nav-link text-danger" href="Home.jsp">Logo.png</a>
                </li>
            </ul>
            <ul class="nav justify-content-end menu">
                <li class="nav-item">
                    <a class="nav-link text-danger" href="Login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-danger" href="#">Register</a>
                </li
            </ul>
        </div>  
    </body>
</html>
