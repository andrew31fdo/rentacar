<%-- 
    Document   : uploadPicture
    Created on : Jan 26, 2014, 10:38:24 PM
    Author     : Ronal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>File Upload</title>
</head>
<body>
    <s:form action="uploadVehicle" method="post" enctype="multipart/form-data">
      <s:select name="vehicle.id" label="Vehicle" value="%{rate.vehicle.id}" list="vehicleList" listKey="id" listValue="name"/>
      <s:file name="secImage" label="File Name" ></s:file>
      <s:submit value="Upload" type="submit"/>
   </s:form>
</body>
</html>
