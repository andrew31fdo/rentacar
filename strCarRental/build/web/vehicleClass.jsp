<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vehicle Classes</title>
    </head>
    <body>
        <h1>Vehicle Classes</h1>
        <p></p>
        <s:url action="addVehicleClass" id="url"><s:param name="vehicleClass.id"></s:param></s:url>
        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/add.png"/>" />New Vehicle Class</a><p/>
        <table border="1">
            <tr>
                <th></th>
                <th>Description</th>
                <th>Remarks</th>
                <th>Actions</th>
            </tr>
            <%int i=0;%>
            <c:forEach items="${vehicleClassList}" var="vehicleClassOne">
                <tr>
                    <% i+=1;%>
                    <td><%=i%></td>
                    <td><c:out value="${vehicleClassOne.description}" /></td>
                    <td><c:out value="${vehicleClassOne.remarks}" /></td>                    
                    <td> &nbsp;&nbsp;
                        <s:url action="editVehicleClass" id="url"><s:param name="vehicleClass.id"><c:out value="${vehicleClassOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/update.png"/>" /></a>&nbsp;&nbsp;&nbsp;
                        <s:url action="deleteVehicleClass" id="url"><s:param name="vehicleClass.id"><c:out value="${vehicleClassOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/delete.png"/>" /></a>&nbsp;
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>