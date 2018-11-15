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
        <div id="sec2" class="pt-5">
            <div class="container">
                <div class="col-12">
                    <div class="row">
                        <c:forEach items="${pdc}" var = "pdc" >
                            <form action="Product" method="post" method="post" class="col-4 p-3 text-center"> 
                                <div onclick="javascript:this.form.submit();">
                                    <div style="background-color: white;">
                                        <p>${pdc.productname}</p>
                                        <p>${pdc.price} BATH</p>
                                        <button>ADD</button>
                                        <input type="hidden" name="productcode" value="${pdc.productcode}">
                                         <input type="hidden" name="producttype" value="cloth">
                                    </div>
                                </div>
                            </form>
                        </c:forEach>
                        <c:forEach items="${pds}" var = "pds">
                            <form action="Product" method="post" class="col-4 p-3 text-center">
                                <div onclick="javascript:this.form.submit();"> 
                                    <div style="background-color: white;">
                                        <p>${pds.productname}</p>
                                        <p>${pds.price} BATH</p> 
                                        <button>ADD</button>   
                                        <input type="hidden" name="productcode" value="${pds.productcode}">
                                         <input type="hidden" name="producttype" value="shoes">
                                    </div>
                                </div>
                            </form>
                        </c:forEach>
                    </div>
                </div>               
            </div>
        </div>
    </body>
</html>
