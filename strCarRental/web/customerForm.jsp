<%-- 
    Document   : customerForm
    Created on : Nov 26, 2013, 9:21:58 AM
    Author     : Ronal
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <sj:head/>
    </head>
    <body>
<div id="screen2" class="wrapper" style="width:600px !important;" >
        <s:if test="customer==null || id == null">
            <s:set name="title" value="%{'Add new Customer'}"/>
        </s:if>
        <s:else>
            <s:set name="title" value="%{'Update Customer'}"/>
        </s:else>
        <h2><s:property value="#title"/></h2>
        <s:form action="saveCustomer" validate="true">
            <s:textfield name="name1"  label="Name1" value="%{customer.name1}"></s:textfield>
            <s:textfield name="name2"  label="Name2" value="%{customer.name2}"></s:textfield>
            <s:textfield name="address1"  label="Address1" value="%{customer.address1}"></s:textfield>
            <s:textfield name="address2"  label="Address2" value="%{customer.address2}"></s:textfield>
            <s:textfield name="city"  label="City" value="%{customer.city}"></s:textfield>
            <s:textfield name="postCode"  label="Postal Code" value="%{customer.postCode}"></s:textfield>
            <s:textfield name="country"  label="Country" value="%{customer.country}"></s:textfield>
            <s:textfield name="nic"  label="NIC" value="%{customer.nic}"></s:textfield>
            <s:textfield name="passport"  label="Passport" value="%{customer.passport}"></s:textfield>
            <s:textfield name="drivingLicence"  label="Driving Licence" value="%{customer.drivingLicence}"></s:textfield>
            <sj:datepicker name="dob" id="date0" label="DOB" value="%{customer.dob}" displayFormat="yy-mm-dd" changeMonth="true" changeYear="true" maxDate="-18y" />
            <s:textfield name="picture"  label="Picture" value="%{customer.picture}"></s:textfield>
            <s:textfield name="email"  label="Email" value="%{customer.email}"></s:textfield>
            <s:select name="custType.id" label="Customer Type" value="%{customer.custType.id}" list="custTypeList" listKey="id" listValue="description"/>
            <s:hidden name="id" value="%{id}" />
            <s:submit value="Submit" name="submit" />
        </s:form>
</div>
    </body>
</html>
