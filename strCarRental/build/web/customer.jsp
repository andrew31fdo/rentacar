<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="screen2" class="wrapper" style="width:600px !important;" >
            <h1>Customer</h1>
            <p></p>
            <s:url action="addCustomer" id="url"><s:param name="customer.id"></s:param></s:url>
            <a href="<s:property value='#url'/>"><img src="<s:url value="/img/add.png"/>" />New Customer</a><p/>
            <table class="gridtable">
                <tr>
                    <th>&nbsp;</th>
                    <th><s:text name="Name"/></th>
                    <th><s:text name="Address"/></th>
                    <th><s:text name="Customer Type"/></th>
                    <th>Actions</th>
                </tr>
                <%int i = 0;%>
                <c:forEach items="${customerList}" var="customerOne">
                    <tr>
                        <% i += 1;%>
                        <td><%=i%></td>
                        <td><c:out value="${customerOne.name1}" /></td>
                        <td><c:out value="${customerOne.address1}" /></td>
                        <td><c:out value="${customerOne.custType.description}" /></td>
                        <td> &nbsp;&nbsp;
                            <s:url action="editCustomer" id="url"><s:param name="customer.id"><c:out value="${customerOne.id}" /> </s:param></s:url>
                            <a href="<s:property value='#url'/>"><img src="<s:url value="/img/update.png"/>" /></a>&nbsp;&nbsp;&nbsp;
                                <s:url action="deleteCustomer" id="url"><s:param name="customer.id"><c:out value="${customerOne.id}" /> </s:param></s:url>
                            <a href="<s:property value='#url'/>"><img src="<s:url value="/img/delete.png"/>" /></a>&nbsp;
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>