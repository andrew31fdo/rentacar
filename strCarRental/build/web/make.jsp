<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Makes</title>
    </head>
    <body>
        <h1>Makes</h1>
        <p></p>
        <s:url action="addMake" id="url"><s:param name="make.id"></s:param></s:url>
        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/add.png"/>" />New Make</a><p/>
        <table border="1">
            <tr>
                <th></th>
                <th>Make</th>
                <th>Remarks</th>
                <th>Actions</th>
            </tr>
            <%int i=0;%>
            <c:forEach items="${makeList}" var="makeOne">
                <tr>
                    <% i+=1;%>
                    <td><%=i%></td>
                    <td><c:out value="${makeOne.name}" /></td>
                    <td><c:out value="${makeOne.remarks}" /></td>
                    
                    <td> &nbsp;&nbsp;
                        <s:url action="editMake" id="url"><s:param name="make.id"><c:out value="${makeOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/update.png"/>" /></a>&nbsp;&nbsp;&nbsp;
                        <s:url action="deleteMake" id="url"><s:param name="make.id"><c:out value="${makeOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/delete.png"/>" /></a>&nbsp;
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>