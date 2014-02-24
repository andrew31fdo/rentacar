<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RateTypes</title>
    </head>
    <body>
        <h1>RateTypes</h1>
        <p></p>
        <s:url action="addRateType" id="url"><s:param name="rateType.id"></s:param></s:url>
        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/add.png"/>" />New RateType</a><p/>
        <table border="1">
            <tr>
                <th></th>
                <th>RateType</th>
                <th>No of Days</th>
                <th>Actions</th>
            </tr>
            <%int i=0;%>
            <c:forEach items="${rateTypeList}" var="rateTypeOne">
                <tr>
                    <% i+=1;%>
                    <td><%=i%></td>
                    <td><c:out value="${rateTypeOne.description}" /></td>
                    <td><c:out value="${rateTypeOne.noDays}" /></td>
                    
                    <td> &nbsp;&nbsp;
                        <s:url action="editRateType" id="url"><s:param name="rateType.id"><c:out value="${rateTypeOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/update.png"/>" /></a>&nbsp;&nbsp;&nbsp;
                        <s:url action="deleteRateType" id="url"><s:param name="rateType.id"><c:out value="${rateTypeOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/delete.png"/>" /></a>&nbsp;
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>