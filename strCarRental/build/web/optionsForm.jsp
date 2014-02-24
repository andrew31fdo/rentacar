<%-- 
    Document   : customerForm
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
        <title>Options</title>
    </head>
    <body>

        <h1>Options</h1>
        <s:if test="options==null || options.id == null">
            <s:set name="title" value="%{'Add Options'}"/>
        </s:if>
        <s:else>
            <s:set name="title" value="%{'Update Options'}"/>
        </s:else>
        <h2><s:property value="#title"/></h2>
        <s:form action="saveOptions">
            <s:textfield name="options"  label="Option" value="%{options.options}"></s:textfield>
            <s:textfield name="perDayCost"  label="Per Day Cost" value="%{options.perDayCost}"></s:textfield>
            <s:textfield name="unit"  label="Unit" value="%{options.unit}"></s:textfield>
            <s:hidden name="id" value="%{id}" />
            <s:submit value="Submit" name="submit" />
        </s:form>
    </body>
</html>
