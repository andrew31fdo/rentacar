<%-- 
    Document   : dashboard
    Created on : Jan 18, 2014, 7:31:25 AM
    Author     : Ronal
--%>

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <sj:head/>
    <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen"/>
</head>
<body>

    <div style="width: 100%">
        <div id="content1" align="center">

            <!--=======================================Screen 2=====================================================-->
            <div id="screen1" class="wrapper" style="width:800px !important;" >
                <br/>
                <div id="screen2" class="wrapper" style="width:600px !important;" > 
                    <h3 align="left" style="padding-left:5px;">Today's Pickups </h3>
                    <hr color="#e1dada"  style="margin-top:3px;"/>
                    <table cellpadding="4" cellspacing="0" border="0" width="100%" align="left" id="getCarextras">
                        <tr>
                            <th>&nbsp;</th>
                            <th><s:text name="Customer"/></th>
                            <th><s:text name="startDate"/></th>
                            <th><s:text name="endDate"/></th>
                            <th>Actions</th>
                        </tr>
                        <%int i = 0;%>
                        <c:forEach items="${pickUpList}" var="reserPick">
                            <tr>
                                <% i += 1;%>
                                <td><%=i%></td>
                                <td><c:out value="${reserPick.customer.id}" /></td>
                                <td><c:out value="${reserPick.startDate}" /></td>
                                <td><c:out value="${reserPick.endDate}" /></td>
                                <td> &nbsp;&nbsp;
                                    <s:url action="editReservation" id="url"><s:param name="reserPick.id"><c:out value="${reserPick.id}" /> </s:param></s:url>
                                    <a href="<s:property value='#url'/>"><img src="<s:url value="/img/update.png"/>" /></a>&nbsp;&nbsp;&nbsp;
                                        <s:url action="deleteReservation" id="url"><s:param name="reserPick.id"><c:out value="${reserPick.id}" /> </s:param></s:url>
                                    <a href="<s:property value='#url'/>"><img src="<s:url value="/img/delete.png"/>" /></a>&nbsp;
                                </td>
                            </tr>
                        </c:forEach>     

                        <tr>
                            <td colspan="3">&nbsp;</td>
                        </tr>
                    </table>
                </div>
                <br/>
                <div id="screen2" class="wrapper" style="width:600px !important;" > 
                    <h3 align="left" style="padding-left:5px;">Today's Drop offs </h3>
                    <hr color="#e1dada"  style="margin-top:3px;"/>
                     <table cellpadding="4" cellspacing="0" border="0" width="100%" align="left" id="getCarextras">
                        <tr>
                            <th>&nbsp;</th>
                            <th><s:text name="Customer"/></th>
                            <th><s:text name="startDate"/></th>
                            <th><s:text name="endDate"/></th>
                            <th>Actions</th>
                        </tr>
                        <% i = 0;%>
                        <c:forEach items="${dropOffList}" var="reserDrop">
                            <tr>
                                <% i += 1;%>
                                <td><%=i%></td>
                                <td><c:out value="${reserDrop.customer.id}" /></td>
                                <td><c:out value="${reserDrop.startDate}" /></td>
                                <td><c:out value="${reserDrop.endDate}" /></td>
                                <td> &nbsp;&nbsp;
                                    <s:url action="editReservation" id="url"><s:param name="reserPick.id"><c:out value="${reserDrop.id}" /> </s:param></s:url>
                                    <a href="<s:property value='#url'/>"><img src="<s:url value="/img/update.png"/>" /></a>&nbsp;&nbsp;&nbsp;
                                        <s:url action="deleteReservation" id="url"><s:param name="reserPick.id"><c:out value="${reserDrop.id}" /> </s:param></s:url>
                                    <a href="<s:property value='#url'/>"><img src="<s:url value="/img/delete.png"/>" /></a>&nbsp;
                                </td>
                            </tr>
                        </c:forEach>     

                        <tr>
                            <td colspan="3">&nbsp;</td>
                        </tr>
                    </table>
                </div>
                <br/>
            </div>
        </div>
    </div>
</body>
</html>