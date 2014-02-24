<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vehicle</title>
    </head>
    <body>
        <h1>Vehicle</h1>
        <p></p>
        <s:url action="addVehicle" id="url"><s:param name="vehicle.id"></s:param></s:url>
        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/add.png"/>" />New Vehicle</a><p/>
        <table border="1">
            <tr>
                <th></th>
                <th>Number Plate</th>
                <th>Chase Number</th>
                <th>Class</th>
                <th>Model</th>
                <th>Location</th>
                <th>Actions</th>
            </tr>
            <%int i=0;%>
            <c:forEach items="${vehicleList}" var="vehicleOne">
                <tr>
                    <% i+=1;%>
                    <td><%=i%></td>
                    <td><c:out value="${vehicleOne.numberPlate}" /></td>
                    <td><c:out value="${vehicleOne.chaseNumber}" /></td>                    
                    <td><c:out value="${vehicleOne.vehicleClass.description}" /></td>                    
                    <td><c:out value="${vehicleOne.vehicleModel.description}" /></td>                    
                    <td><c:out value="${vehicleOne.location.locName}" /></td>                    
                    <td> &nbsp;&nbsp;
                        <s:url action="editVehicle" id="url"><s:param name="vehicle.id"><c:out value="${vehicleOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/update.png"/>" /></a>&nbsp;&nbsp;&nbsp;
                        <s:url action="deleteVehicle" id="url"><s:param name="vehicle.id"><c:out value="${vehicleOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/delete.png"/>" /></a>&nbsp;
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>