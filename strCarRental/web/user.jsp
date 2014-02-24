<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
        <h1>Users</h1>
        <p></p>
        <s:url action="addUser" id="url"><s:param name="user.id"></s:param></s:url>
        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/add.png"/>" />New User</a><p/>
        <table border="1">
            <tr>
                <th></th>
                <th>User-ID</th>
                <th>Name</th>
                <th>Role</th>
                <th>Last Login</th>
                <th>Audit</th>
                <th>Actions</th>
            </tr>
            <%int i=0;%>
            <c:forEach items="${userList}" var="userOne">
                <tr>
                    <% i+=1;%>
                    <td><%=i%></td>
                    <td><c:out value="${userOne.userID}" /></td>
                    <td><c:out value="${userOne.name}" /></td>
                    <td><c:out value="${userOne.userRole}" /></td>
                    <td><c:out value="${userOne.lastLogin}" /></td>
                    <td><c:out value="${userOne.loginAudit}" /></td>
                    
                    <td> &nbsp;&nbsp;
                        <s:url action="editUser" id="url"><s:param name="user.id"><c:out value="${userOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/update.png"/>" /></a>&nbsp;&nbsp;&nbsp;
                        <s:url action="deleteUser" id="url"><s:param name="user.id"><c:out value="${userOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/delete.png"/>" /></a>&nbsp;
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>