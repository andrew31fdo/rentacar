<%-- 
    Document   : customerTypeForm
    Created on : Nov 26, 2013, 9:21:58 AM
    Author     : Ronal
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            .error {
                background-color:red;
                border:1px solid #009900;
                width:400px;
            }
        </style>
    </head>
    <body>

        <h1>Customer Type</h1>

        <s:if test="custType==null || id == null">
            <s:set name="title" value="%{'New Customer Type'}"/>
        </s:if>
        <s:else>
            <s:set name="title" value="%{'Update Customer Type'}"/>
        </s:else>
        <h2><s:property value="#title"/></h2>
        <s:form action="saveCustomerType" validate="true" theme="simple">
            <s:textfield name="description"  label="Type" value="%{custType.description}"></s:textfield>
            <s:hidden name="id" value="%{custType.id}"></s:hidden>
            <s:submit value="Submit" name="submit" />
        </s:form>
    </body>
</html>
