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
        <title>JSP Page</title>
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    </head>
    <style>
        /*nav*/
        #nav1 {
            height: 40px;
            background-color: black;
            line-height: 40px;
            vertical-align: middle;
        }
        .nav2 {
            background-color: transparent;
            height: 60px;
            line-height: 60px;
            vertical-align: middle;
            transition: 1s;
        }
        .scrollNav {
            background-color: white;
            box-shadow: 0 15px 20px rgba(0, 0, 0, 0.3);
        }
        #nav1 a,#nav2 a {
            padding: 0;
        }
        nav {
            position: fixed;
            width: 100vw;
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
        <nav>
            <div id="nav1" class="d-block">
                <div class="container">
                    <ul class="nav d-inline-block">
                        <li class="nav-item">
                            <a class="nav-link text-white" onclick="Login()" style="cursor: pointer;">Login</a>
                        </li>
                    </ul>
                    <ul class="nav d-inline-block float-right">
                        <li class="nav-item">
                            <a class="nav-link text-white" href="#">SHOPPING CART (0) </a>
                        </li>
                    </ul>  
                </div>
            </div>
            <div id="nav2" class="d-block nav2">
                <div class="container">
                    <ul class="nav d-inline-block">
                        <li class="nav-item">
                            <a class="nav-link text-white" href="#">COMFORTPAY</a>
                        </li>
                    </ul>
                    <ul class="nav d-inline-block float-right">
                        <li class="nav-item">
                            <a class="nav-link text-white" href="#">FAVORITE</a>
                        </li>
                    </ul>  
                </div>
            </div>
        </nav>
        <div id="login" class="modal">
            <div class="col-8 mx-auto">
                <form class="modal-content animate" action="Check">
                    <div class="imgcontainer">
                        <span onclick="Close()" class="close" title="Close Modal">&times;</span>
                    </div>
                    <div class="container">
                        <label for="uname"><b>Username</b></label>
                        <input type="text" placeholder="Enter Username" name="username" required>
                        
                        <label for="psw"><b>Password</b></label>
                        <input type="password" placeholder="Enter Password" name="password" required>
                        <input type="hidden" name="path" value="http://localhost:8080/ComfortPay/Home.jsp">
                        <button type="submit">Login</button>
                    </div>
                </form> 
            </div>
            
        </div>
        <script>
            //Scrolling Effect
            $(window).on('scroll', function () {
                if ($(window).scrollTop()) {
                    $('#nav2').addClass('scrollNav');
                    $('#nav2 a').addClass('text-body');
                } else {
                    $('#nav2').removeClass('scrollNav');
                    $('#nav2 a').removeClass('text-body');
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
