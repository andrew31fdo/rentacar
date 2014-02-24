<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rates</title>
    </head>
    <body>
        <div id="screen2" class="wrapper" style="width:600px !important;" >
        <h1>Rates</h1>
        <p></p>
        <s:url action="addRate" id="url"><s:param name="rate.id"></s:param></s:url>
        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/add.png"/>" />New Rate</a><p/>
        <table class="gridtable">
            <tr>
                <th></th>
                <th>Rate Type</th>
                <th>Vehicle</th>
                <th>Per Day Cost</th>
                <th>Actions</th>
            </tr>
            <%int i = 0;%>
            <c:forEach items="${activeRateList}" var="rateOne">
                <tr>
                    <% i += 1;%>
                    <td><%=i%></td>

                    <td><c:out value="${rateOne.rateType.description}" /></td>
                    <td><c:out value="${rateOne.vehicle.name}" /></td>
                    <td><c:out value="${rateOne.perDayCost}" /></td>
                    <td> &nbsp;&nbsp;
                        <s:url action="editRate" id="url"><s:param name="rate.id"><c:out value="${rateOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/update.png"/>" /></a>&nbsp;&nbsp;&nbsp;
                            <s:url action="deleteRate" id="url"><s:param name="rate.id"><c:out value="${rateOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/delete.png"/>" /></a>&nbsp;
                    </td>
                </tr>
            </c:forEach>
        </table>
        <s:url id="url_pre" value="viewAllRate.action"><s:param name="pageNow" value="pageNow-1"/></s:url> 
        <s:url id="url_next" value="viewAllRate.action"><s:param name="pageNow" value="pageNow+1"/></s:url> 
        <s:a href="%{url_pre}"> Previous </s:a> 
        <s:iterator value="activeRateList" status="status">  
            <s:url id="url" value="viewAllRate.action"><s:param name="pageNow" value="pageNow"/></s:url> 
        </s:iterator> 
        <s:a href="%{url_next}"> Next </s:a>
        </div>
    </body>
</html>