<%-- 
    Document   : customerForm
    Created on : Nov 26, 2013, 9:21:58 AM
    Author     : Ronal
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vehicle</title>
        <sj:head/>
    </head>
    <body>

        <h1>Vehicle</h1>
        <s:if test="vehicleClass==null || id == null">
            <s:set name="title" value="%{'Add Vehicle'}"/>
        </s:if>
        <s:else>
            <s:set name="title" value="%{'Update Vehicle'}"/>
        </s:else>
        <h2><s:property value="#title"/></h2>
        <s:form action="saveVehicle">
            <s:textfield name="numberPlate"  label="Number Plate" value="%{vehicle.numberPlate}"></s:textfield>
            <s:textfield name="chaseNumber"  label="Chase Number" value="%{vehicle.chaseNumber}"></s:textfield>
            <s:textfield name="color"  label="Color" value="%{vehicle.color}"></s:textfield>
            <sj:datepicker name="firstRegistration" id="date0" label="First Registration" value="%{vehicle.firstRegistration}" displayFormat="yy-mm-dd" />
            <s:textfield name="dailyMilage"  label="Daily Limit" value="%{vehicle.dailyMilage}"></s:textfield>
            <s:textfield name="currentMilage"  label="Current Milage" value="%{vehicle.currentMilage}"></s:textfield>
            <s:textfield name="imagePath"  label="ImagePath" value="%{vehicle.imagePath}"></s:textfield>
            <s:radio name="status" label="Status" list="#{'AC':'Active','IN':'Inactive'}" value="vehicle.status"/>
            <s:select name="vehicleClass.id" label="Vehicle Class" value="%{vehicle.vehicleClass.id}" list="vehicleClassList" listKey="id" listValue="description"/>
            <s:select name="vehicleModel.id" label="Vehicle Model" value="%{vehicle.vehicleModel.id}" list="vehicleModelList" listKey="id" listValue="description"/>
            <s:select name="location.id" label="Location" value="%{vehicle.location.id}" list="locationList" listKey="id" listValue="locName"/>
            <s:checkboxlist
            label="Features"
            list="featureList"
            listKey="id"
            listValue="description"
            value="vehicle.features.{id}"
            name="features.id"/>
            <s:hidden name="id" value="%{id}" /> 
            <s:submit value="Submit" name="submit" />
        </s:form>
    </body>
</html>
