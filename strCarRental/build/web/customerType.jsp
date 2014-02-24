<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen"/>
        
    </head>
    <body>
        <h1>Customer Type</h1>
        <p></p>
        <s:url action="addCustomerType" id="url"><s:param name="customerType.id"></s:param></s:url>
        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/add.png"/>" />New CustomerType</a><p/>
        <table class="gridtable" >
            <tr>
                <th>&nbsp;</th>
                <th><s:text name="Description"/></th>
                <th>Actions</th>
            </tr>
            <%int i = 0;%>
            <c:forEach items="${custTypeList}" var="custTypeOne">
                <tr>
                    <% i += 1;%>
                    <td><%=i%></td>
                    <td><c:out value="${custTypeOne.description}" /></td>
                    <td> &nbsp;&nbsp;
                        <s:url action="editCustomerType" id="url"><s:param name="custType.id"><c:out value="${custTypeOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/update.png"/>" /></a>&nbsp;&nbsp;&nbsp;
                            <s:url action="deleteCustomerType" id="url"><s:param name="custType.id"><c:out value="${custTypeOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/delete.png"/>" /></a>&nbsp;
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>