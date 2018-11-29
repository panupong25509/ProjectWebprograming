<%-- 
    Document   : Register
    Created on : Nov 15, 2018, 12:35:13 AM
    Author     : Joknoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <jsp:include page="Components/Navbar.jsp"/>
        <div class="col-10 mx-auto text-center" style="padding-top: 130px;">
            <form class="modal-content animate p-5" action="RegisterAccount" class="pt-5" style="background-color: #53759A;color: #D5C4AD;" >
                <h1>Register Comfort Pay</h1>
                <br>
                <div class="col-12 row m-0">
                    <div class="col">
                        
                <div class="col-12 row m-0">
                    <div class="col-5 text-right">
                        <p>Username :</p>
                    </div>
                    <div class="col-7 text-left">
                        <p><input type="text" class="form-control" placeholder="Enter Username" name="username" required></p>
                    </div>
                    <div class="col-5 text-right">
                        <p>Password :</p>
                    </div>
                    <div class="col-7 text-left">
                        <p><input type="password" class="form-control" placeholder="Enter Password" name="password" required></p>
                    </div>
                    <div class="col-5 text-right">
                        <p>Password :</p>
                    </div>
                    <div class="col-7 text-left">
                        <p><input type="password" class="form-control" placeholder="Enter Password Again" name="passwordAgain" required></p>
                    </div>
                    <div class="col-5 text-right">
                        <p>Firstname :</p>
                    </div>
                    <div class="col-7 text-left">
                        <p><input type="text" class="form-control" placeholder="Enter Firstname" name="fname" required></p>
                    </div>
                    <div class="col-5 text-right">
                        <p>Lastname :</p>
                    </div>
                    <div class="col-7 text-left">
                        <p><input type="text" class="form-control" placeholder="Enter Lastname" name="lname" required></p>
                    </div>
                    <div class="col-5 text-right">
                        <p>Email :</p>
                    </div>
                    <div class="col-7 text-left">
                        <p><input type="text"class="form-control" placeholder="Enter Email" name="email" required></p>
                    </div>
                </div>
                    </div>
                    <div class="col">
                        <div class="col-12 row m-0">
                            <div class="col-5 text-right">
                                <p>DOB :</p>
                            </div>
                            <div class="col-7 text-left">
                                <p><input class="form-control" type="date" name="dob" required></p>
                            </div>
                            <div class="col-5 text-right">
                                <p>Address :</p>
                            </div>
                            <div class="col-7 text-left">
                                <p><input class="form-control" type="text" placeholder="Enter Address" name="address" required></p>
                            </div>
                            <div class="col-5 text-right">
                                <p>District :</p>
                            </div>
                            <div class="col-7 text-left">
                                <p><input  class="form-control" type="text" placeholder="Enter District" name="district" required></p>
                            </div>
                            <div class="col-5 text-right">
                                <p>Province :</p>
                            </div>
                            <div class="col-7 text-left">
                                <p><input class="form-control" type="text" placeholder="Enter Province" name="province" required></p>
                            </div>
                            <div class="col-5 text-right">
                                <p>Postcode :</p>
                            </div>
                            <div class="col-7 text-left">
                                <p><input class="form-control" type="text" placeholder="Enter Postcode" name="postcode" required></p>
                            </div>
                        </div>
                        
                    </div>
                </div>
                <button type="button submit" class="btn btn-warning">CONFIRM</button>
            </form>
        </div>
    </body>
</html>
