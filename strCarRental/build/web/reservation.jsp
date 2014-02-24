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
        <div id="screen2" class="wrapper" style="width:900px !important;" >
            <h1>Reservation</h1>
            <p></p>
            <s:url action="addReservation" id="url"><s:param name="Reservation.id"></s:param></s:url>
            <a href="<s:property value='#url'/>"><img src="<s:url value="/img/add.png"/>" />New Reservation</a><p/>
            <table class="gridtabler">
                <tr>
                    <th>&nbsp;</th>
                    <th><s:text name="Referance"/></th>
                    <th><s:text name="Location"/></th>
                    <th><s:text name="Vehicle"/></th>
                    <th><s:text name="Pickup Date"/></th>
                    <th><s:text name="Dropoff Date"/></th>
                    <th><s:text name="Customer"/></th>
                    <th><s:text name="Total Amount"/></th>
                    <th>Actions</th>
                </tr>
                <%int i = 0;%>
                <c:forEach items="${activeReservationList}" var="ReservationOne">
                    <tr>
                        <% i += 1;%>
                        <td><%=i%></td>
                        <td><c:out value="${ReservationOne.referance}" /></td>
                        <td><c:out value="${ReservationOne.location.locName}" /></td>
                        <td><c:out value="${ReservationOne.vehicle.name}" /></td>
                        <td><c:out value="${ReservationOne.startDate}" /></td>
                        <td><c:out value="${ReservationOne.endDate}" /></td>
                        <td><c:out value="${ReservationOne.customer.name1}" /></td>
                        <td><c:out value="${ReservationOne.net}" /></td>
                        <td> &nbsp;&nbsp;
                            <s:url action="editReservation" id="url"><s:param name="Reservation.id"><c:out value="${ReservationOne.id}" /> </s:param></s:url>
                            <a href="<s:property value='#url'/>"><img src="<s:url value="/img/update.png"/>" /></a>&nbsp;&nbsp;&nbsp;
                                <s:url action="deleteReservation" id="url"><s:param name="Reservation.id"><c:out value="${ReservationOne.id}" /> </s:param></s:url>
                            <a href="<s:property value='#url'/>"><img src="<s:url value="/img/delete.png"/>" /></a>&nbsp;
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <s:url id="url_pre" value="viewAllReservation.action"><s:param name="pageNow" value="pageNow-1"/></s:url> 
        <s:url id="url_next" value="viewAllReservation.action"><s:param name="pageNow" value="pageNow+1"/></s:url> 
        <s:a href="%{url_pre}"> Previous </s:a> 
        <s:iterator value="activeRateList" status="status">  
            <s:url id="url" value="viewAllReservation.action"><s:param name="pageNow" value="pageNow"/></s:url> 
        </s:iterator> 
        <s:a href="%{url_next}"> Next </s:a>
        </div>
    </body>
</html>