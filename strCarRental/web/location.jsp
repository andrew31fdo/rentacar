<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Locations</title>
    </head>
    <body>
        <h1>Locations</h1>
        <p></p>
        <s:url action="addLocation" id="url"><s:param name="location.id"></s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/add.png"/>" />New Location</a><p/>
        <table border="1">
            <tr>
                <th>&nbsp;</th>
                <th><s:text name="Location Name"/></th>
                <th><s:text name="Address1"/></th>
                <th><s:text name="Address2"/></th>
                <th><s:text name="City"/></th>
                <th><s:text name="Contact"/></th>
                <th>Actions</th>
            </tr>
            <%int i=0;%>
            <c:forEach items="${locationList}" var="locationOne">
                <tr>
                    <% i+=1;%>
                    <td><%=i%></td>
                    <td><c:out value="${locationOne.locName}" /></td>
                    <td><c:out value="${locationOne.address1}" /></td>
                    <td><c:out value="${locationOne.address2}" /></td>
                    <td><c:out value="${locationOne.city}" /></td>
                    <td><c:out value="${locationOne.contact}" /></td>
                    
                    <td> &nbsp;&nbsp;
                        <s:url action="editLocation" id="url"><s:param name="location.id"><c:out value="${locationOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/update.png"/>" /></a>&nbsp;&nbsp;&nbsp;
                        <s:url action="deleteLocation" id="url"><s:param name="location.id"><c:out value="${locationOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/delete.png"/>" /></a>&nbsp;
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>