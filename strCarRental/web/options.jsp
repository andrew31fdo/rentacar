<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Options</title>
    </head>
    <body>
        <h1>Options</h1>
        <p></p>
        <s:url action="addOptions" id="url"><s:param name="Option.id"></s:param></s:url>
        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/add.png"/>" />New Option</a><p/>
        <table border="1">
            <tr>
                <th></th>
                <th>Option</th>
                <th>Per Day Cost</th>
                <th>Unit</th>
                <th>Actions</th>
            </tr>
            <%int i=0;%>
            <c:forEach items="${optionsList}" var="optionsOne">
                <tr>
                    <% i+=1;%>
                    <td><%=i%></td>
                    <td><c:out value="${optionsOne.options}" /></td>
                    <td><c:out value="${optionsOne.perDayCost}" /></td>
                    <td><c:out value="${optionsOne.unit}" /></td>
                    <td> &nbsp;&nbsp;
                        <s:url action="editOptions" id="url"><s:param name="options.id"><c:out value="${optionsOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/update.png"/>" /></a>&nbsp;&nbsp;&nbsp;
                        <s:url action="deleteOptions" id="url"><s:param name="options.id"><c:out value="${optionsOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/delete.png"/>" /></a>&nbsp;
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>