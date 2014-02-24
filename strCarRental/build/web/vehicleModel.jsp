<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vehicle Models</title>
    </head>
    <body>
        <h1>Vehicle Models</h1>
        <p></p>
        <s:url action="addVehicleModel" id="url"><s:param name="vehicleModel.id"></s:param></s:url>
        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/add.png"/>" />New Vehicle Model</a><p/>
        <table border="1">
            <tr>
                <th></th>
                <th>Vehicle Model</th>
                <th>Make</th>
                <th>Actions</th>
            </tr>
            <%int i=0;%>
            <c:forEach items="${vehicleModelList}" var="vehicleModelOne">
                <tr>
                    <% i+=1;%>
                    <td><%=i%></td>
                    <td><c:out value="${vehicleModelOne.description}" /></td>
                    <td><c:out value="${vehicleModelOne.make.name}" /></td>
                    
                    <td> &nbsp;&nbsp;
                        <s:url action="editVehicleModel" id="url"><s:param name="vehicleModel.id"><c:out value="${vehicleModelOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/update.png"/>" /></a>&nbsp;&nbsp;&nbsp;
                        <s:url action="deleteVehicleModel" id="url"><s:param name="vehicleModel.id"><c:out value="${vehicleModelOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/delete.png"/>" /></a>&nbsp;
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>