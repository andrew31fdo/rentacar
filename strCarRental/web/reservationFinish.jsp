<%-- 
    Document   : reservationFinish
    Created on : Jan 17, 2014, 4:09:07 PM
    Author     : Ronal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservation Completed!</title>
        <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen"/>
    </head>
    <body>
        <div id="content6" align="center">
            <p></p>
                <div id="screen6" class="wrapper" style="width:600px !important;" > 
                    <h2 align="left" style="padding-left:5px;">Booking Completed</h2>
                    <hr color="#e1dada"  style="margin-top:3px;"/><br /><br />
                    <h4>Thank You!</h4><br />Your Booking is confirmed. <br/>
                    Reference is :<c:out value="${myReservation.referance}" /><br/>
                    The invoice is sent to your email address[<c:out value="${myReservation.customer.email}"/>].<p></p>
                    <a href="addReservation.action"> New Reservation</a><p/>
                    <a href="welcomeLink.action">Home</a>
<p/>                </div>
            </div>
    </body>
</html>
