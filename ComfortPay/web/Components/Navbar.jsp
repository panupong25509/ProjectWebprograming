<%-- 
    Document   : Navbar
    Created on : Nov 3, 2018, 10:39:15 PM
    Author     : crtiexx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css" integrity="sha384-/rXc/GQVaYpyDdyxK+ecHPVYJSN9bmVFBvjA/9eOB+pb3F2w2N6fc5qB9Ew5yIns" crossorigin="anonymous">
        <title>JSP Page</title>
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    </head>
    <style>
        /*nav*/
        .nav1 {
            height: 30px;
            background-color: #F5F5F5;
            line-height: 30px;
            vertical-align: middle;
        }
        .nav2 {
            background-color: transparent;
            height: 100px;
            line-height: 100px;
            vertical-align: middle;
            transition: 1s;
            width: 100vw;
        }
        .logo {
            height: 100px;
            transition: 1s;
        }
        .scrollNav {
            background-color: white;
            box-shadow: 0 15px 20px rgba(0, 0, 0, 0.3);
            height: 60px;
            line-height: 60px;
        }
        .logoScroll {
            height: 60px;
        }
        .nav1 a,.nav2 a {
            padding: 0;
        }
        .nav1 a {
            color: #4A4A4A;
            font-size: 12px;
        }
        .navbars {
            position: fixed;
            /*width: 100vw;*/
            z-index: 2;
        }
        /*popup*/
        /* Full-width input fields */
        input[type=text], input[type=password] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        /* Set a style for all buttons */
        button {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            opacity: 0.8;
        }

        /* Extra styles for the cancel button */
        .cancelbtn {
            width: auto;
            padding: 10px 18px;
            background-color: #f44336;
        }

        /* Center the image and position the close button */


        form .container {
            padding: 16px;
        }

        span.psw {
            float: right;
            padding-top: 16px;
        }

        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 2; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
            padding-top: 60px;
            overflow: hidden;
        }

        /* Modal Content/Box */
        .modal-content {
            background-color: #fefefe;
            margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
            border: 1px solid #888;
            width: 80%; /* Could be more or less, depending on screen size */
        }

        /* The Close Button (x) */
        .close {
            position: relative;
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
        @keyframes animatezoom {
            from {transform: scale(0)} 
            to {transform: scale(1)}
        }
    </style>
    <body>
        <div class="navbars">
            <nav class="nav1 navbar-expand-lg">
                <div class="container navbar-collapse">
                    <ul class="navbar-nav mr-auto">
                        <% if (session.getAttribute("account") == null) { %>
                        <li class="nav-item ">
                            <a class="nav-link" onclick="Login()" style="cursor: pointer;"><i class="fas fa-user"></i> LOG IN</a>
                        </li>
                        <% } else {%>
                        <li class="nav-item d-inline-block">
                            <a class="nav-link" href="MyAccount">${profile.fname} ${profile.lname}</a>
                        </li>
                        <li class="nav-item d-inline-block">
                            <a class="nav-link" href="Logout?path=<%= request.getRequestURL()%>">Logout</a>
                        </li>
                        <% }%>
                    </ul>
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link mr-3" href="#"><i class="fas fa-star"></i> WISH LIST </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"><i class="fas fa-shopping-cart"></i> SHOPPING CART (0) </a>
                        </li>
                    </ul>
                </div>
            </nav>
            <nav class="nav2 navbar-expand-lg">
                <div class="container navbar-collapse">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link"><img src="images/Home/logo.png" class="logo"></a>
                        </li>
                    </ul>
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link">CHAMPION</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link pl-5">NIKE</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link pl-5">ADIDAS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link pl-5">FILA</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        <div id="login" class="modal">
            <div class="col-8 mx-auto">
                <form class="modal-content animate" action="LoginPopup">
                    <div class="imgcontainer">
                        <span onclick="Close()" class="close" title="Close Modal">&times;</span>
                    </div>
                    <div class="container">
                        <label for="uname"><b>Username</b></label>
                        <input type="text" placeholder="Enter Username" name="username" required>

                        <label for="psw"><b>Password</b></label>
                        <input type="password" placeholder="Enter Password" name="password" required>
                        <input type="hidden" name="path" value=<%= request.getRequestURL()%>>
                        <button type="submit">Login</button>
                    </div>
                </form> 
            </div>

        </div>
        <script>
            //Scrolling Effect
            $(window).on('scroll', function () {
                if ($(window).scrollTop()) {
                    $('.nav2').addClass('scrollNav');
                    $('.logo').addClass('logoScroll');
                } else {
                    $('.nav2').removeClass('scrollNav');
                    $('.logo').removeClass('logoScroll');
                }
            });
            //Login
            function Login() {
                document.getElementById('login').style.display = 'block';
            }
            function Close() {
                document.getElementById('login').style.display = 'none';
            }
            // Get the modal
            var modal = document.getElementById('login');

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        </script>
    </body>
</html>
