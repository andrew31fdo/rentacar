<%-- 
    Document   : login
    Created on : Jan 18, 2014, 4:04:42 AM
    Author     : Ronal
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Administrator Login</title>
        
        <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen"/>
    </head>
    <body>
        <div style="height: 800px; width: 100%">
            <div id="content1" align="center">
                <h1> CAR4Rent - Car Rental System </h1>
                <br/>
               
                <div id="screen1" class="wrapper" style="width:800px !important;" >

                     <br/>
                    <br/>
                    <br/>
                      <br/>
                    <br/>
                    <br/>
                    <div id="screen2" class="wrapper" style="width:600px !important;" > 
                        <s:form action="loginLink.action"  method="post" theme="xhtml">
                            <s:textfield name="userId" label=" User ID"/>
                            <s:password name="passwd" label="Password"/>
                            <s:submit value="Admin Login"/>
                        </s:form>

                        <s:actionerror/>
                    </div>
                      <br/>
                    <br/>
                    <br/>
                      <br/>
                    <br/>
                    <br/>
                </div>
                  
            </div>
        </div>
    </body>

</html>