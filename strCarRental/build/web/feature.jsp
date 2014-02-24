<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Features</title>
    </head>
    <body>
        <h1>Features</h1>
        <p></p>
        <s:url action="addFeature" id="url"><s:param name="feature.id"></s:param></s:url>
        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/add.png"/>" />New Feature</a><p/>
        <table border="1">
            <tr>
                <th></th>
                <th>Feature</th>
                <th>Actions</th>
            </tr>
            <%int i=0;%>
            <c:forEach items="${featureList}" var="featureOne">
                <tr>
                    <% i+=1;%>
                    <td><%=i%></td>
                    <td><c:out value="${featureOne.description}" /></td>
                    
                    <td> &nbsp;&nbsp;
                        <s:url action="editFeature" id="url"><s:param name="feature.id"><c:out value="${featureOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/update.png"/>" /></a>&nbsp;&nbsp;&nbsp;
                        <s:url action="deleteFeature" id="url"><s:param name="feature.id"><c:out value="${featureOne.id}" /> </s:param></s:url>
                        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/delete.png"/>" /></a>&nbsp;
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>