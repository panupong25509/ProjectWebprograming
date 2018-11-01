<%-- 
    Document   : navbar
    Created on : Nov 2, 2018, 12:19:18 AM
    Author     : crtiexx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <style>
        #navbar {
            display: block;
        }
        #navbar .menu {
            display: inline-block;
        }
        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
            padding-top: 60px;
        }

        /* Modal Content/Box */
        .modal-content {
            background-color: #fefefe;
            margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
            padding: 20px;
            border: 1px solid #888;
            width: 80%; /* Could be more or less, depending on screen size */
        }

        /* The Close Button (x) */
        .close {
            position: absolute;
            right: 25px;
            top: 0;
            color: #000;
            font-size: 35px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: red;
            cursor: pointer;
        }

        /* Add Zoom Animation */
        .animate {
            -webkit-animation: animatezoom 0.6s;
            animation: animatezoom 0.6s
        }

        @-webkit-keyframes animatezoom {
            from {-webkit-transform: scale(0)} 
            to {-webkit-transform: scale(1)}
        }

        @keyframes animatezoom {
            from {transform: scale(0)} 
            to {transform: scale(1)}
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
                    <a class="nav-link text-danger" onclick="Login()" style="cursor: pointer">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-danger" href="#">Register</a>
                </li
            </ul>
        </div>
        <div id="Login" class="modal">
            <form class="modal-content animate" action="Login">
                <h1>Login</h1>
                <div>
                    <p onclick="Close()" class="close" title="Close">&times;</p>
                </div>
                <div>
                    <table>
                        <tr>
                            <td>Username</td>
                            <td><input type="text" required></td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type="text" required></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Login"></td>
                        </tr>
                    </table>
                </div>
                <!--<button type="button" onclick="Close()">Cancel</button>-->
            </form>
        </div>
    </body>

    <script>
        var modal = document.getElementById('Login');
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
        function Login() {
            document.getElementById('Login').style.display = 'block'
        }
        function Close() {
            document.getElementById('Login').style.display = 'none'
        }
    </script>
</html>
