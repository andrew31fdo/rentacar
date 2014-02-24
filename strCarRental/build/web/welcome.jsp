<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>

    </head>
    <body>

        <div id="content6" align="center">
            <p></p>
            <div id="screen6" class="wrapper" style="width:600px !important;" > 
                <br /><br />
                <hr color="#e1dada"  style="margin-top:3px;"/>
                <h4>Welcome <c:out value="${logUserName}" /> !</h4><br />
                    Last Login :<c:out value="${lastLogin}" /><br/>
                    <hr color="#e1dada"  style="margin-top:3px;"/><br /><br />
                    <p/>               
            </div>
        </div>
    </body>
</html>