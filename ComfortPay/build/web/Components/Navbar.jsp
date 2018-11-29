<%-- 
    Document   : test
    Created on : Nov 15, 2018, 11:27:12 PM
    Author     : Joknoi
--%>

<%@page import="comfortpay.model.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css" integrity="sha384-/rXc/GQVaYpyDdyxK+ecHPVYJSN9bmVFBvjA/9eOB+pb3F2w2N6fc5qB9Ew5yIns" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    </head>
    <style>
        /* Navbar */
        .navbarLogo {
            background-color: transparent;
            color: #968783;
            font-size: 20px;
            transition: 1s;
            /*height: 80px;*/
        }
        .navbarLogoSlide {
            background-color: white;
            color: #968783;
            font-size: 20px;
            /*height: 60px;*/
        }
        .Logo {
            height: 80px;
            transition: 1s;
        }
        .LogoSlide {
            height: 60px;
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
            animation: animatezoom 0.6s
        }  
        @keyframes animatezoom {
            from {transform: scale(0)} 
            to {transform: scale(1)}
        }
    </style>
    <body>
        <div style="position: fixed;z-index: 2;width: 100vw;">
            <div style="background-color: #53759A;font-size: 12px;height: 35px;">
                <div class="container">
                    <div class="col-12 row m-0 navbar-collapse navbar-expand-lg">
                        <ul class="list-inline m-0 mr-auto p-0">
                            <% if (session.getAttribute("account") == null) { %>
                            <li class="list-inline-item">
                                <a class="nav-link" onclick="Login()" style="cursor: pointer;color: #D5C4AD;"><i class="fas fa-user"></i> LOGIN</a>
                            </li>
                            <li class="list-inline-item">
                                <a class="nav-link" href="Register" style="color: #D5C4AD;"><i class="fas fa-user"></i> REGISTER</a>
                            </li>
                            <% } else {%>
                            <li class="list-inline-item">
                                <a class="nav-link" href="MyAccount" style="color: #D5C4AD;">${account.fname} ${account.lname}</a>
                            </li>
                            <li class="list-inline-item">
                                <a class="nav-link" href="Logout?path=<%= request.getRequestURL()%>" style="color: #D5C4AD;">LOGOUT</a>
                            </li>
                            <% }%>
                        </ul> 
                        <ul class="list-inline m-0">
                            <li class="list-inline-item">
                                <div>
                                    <form action="Search" class="form-inline">
                                        <input class="form-control" type="text" name="search" required style="background-color: transparent;border: none;border-bottom: 1px solid #D5C4AD;color: #D5C4AD;font-size: 12px">
                                        <a href="#" >
                                            <button type="submit" style="height: 35px;background-color: transparent;border: none;cursor: pointer">
                                                <i class="fas fa-search ml-1" style="color: #D5C4AD;"></i>
                                            </button>
                                        </a>
                                    </form> 
                                </div>
                            </li>
                            <li class="list-inline-item">
                                <a class="nav-link"style="color: #D5C4AD;"><i class="fas fa-star"></i> WISH LIST </a>
                            </li>
                            <li class="list-inline-item">
                                <a class="nav-link" href="/ComfortPay/Cart"style="color: #D5C4AD;">
                                    <% Cart cart = (Cart) session.getAttribute("cart"); %>
                                    <% if (cart!=null && cart.getTotalQuantity() != 0) { %>
                                    <i class="fas fa-shopping-cart"></i> SHOPPING CART (${cart.totalQuantity}) 
                                    <% } else { %>
                                    <i class="fas fa-shopping-cart"></i> SHOPPING CART (0) 
                                    <% }%>
                                </a>
                            </li>
                        </ul> 
                    </div>
                </div>
            </div>
            <!--2-->
            <div class="navbarLogo">
                <div class="container">
                    <div class="col-12 row m-0 navbar-expand-lg navbar-collapse">
                        <ul class="list-inline m-0 mr-auto p-0">
                            <li class="list-inline-item">
                                <a class="nav-link" href="/ComfortPay/Home"><img src="images/Home/logo.png" class="Logo"></a>
                            </li>
                        </ul> 
                        <ul class="list-inline m-0">
                            <li class="list-inline-item">
                                <a class="nav-link" href="Search?search=champion"style="color: #53759A;">CHAMPION</a>
                            </li>
                            <li class="list-inline-item">
                                <a class="nav-link" href="Search?search=nike" style="color: #53759A;">NIKE</a>
                            </li>
                            <li class="list-inline-item">
                                <a class="nav-link" href="Search?search=adidas" style="color: #53759A;">ADIDAS</a>
                            </li>
                            <li class="list-inline-item">
                                <a class="nav-link" href="Search?search=fila" style="color: #53759A;">FILA</a>
                            </li>
                        </ul> 
                    </div>
                </div>
            </div>  
        </div>

        <!--        popup-->
        <div id="login" class="modal">
            <div class="col-6 mx-auto">
                <form class="modal-content animate" action="LoginPopup" style="background-color: #53759A;color: #D5C4AD;">
                    <div class="imgcontainer">
                        <span onclick="Close()" class="close" title="Close Modal">&times;</span>
                    </div>
                    <div class="container" style="font-size: 18px;">
                        <h1 class="text-center">Login</h1>
                        <label for="uname">Username</label>
                        <input type="text" class="form-control" placeholder="Enter Username" name="username" required>
                        <!--<br>-->
                        <label for="psw">Password</label>
                        <input type="password" class="form-control" placeholder="Enter Password" name="password" required>
                        
                        <input type="hidden" name="path" value="<%= request.getRequestURL()%>">
                        <input type="hidden" name="productid" value="${product.productid}">
                        <br>
                        <button type="button submit" class="btn mb-3" style="font-size: 16px;width: 100%;background-color:#D5C4AD;color: #53759A"><b>Login</b></button>
                    </div>
                </form> 
            </div>

        </div>

        <script>
            //Scrolling Effect
            $(window).on('scroll', function () {
                if ($(window).scrollTop()) {
                    $('.navbarLogo').addClass('navbarLogoSlide');
                    $('.Logo').addClass('LogoSlide');
                } else {
                    $('.navbarLogo').removeClass('navbarLogoSlide');
                    $('.Logo').removeClass('LogoSlide');
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
