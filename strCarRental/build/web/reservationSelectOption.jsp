<%-- 
    Document   : reservationSelection
    Created on : Jan 10, 2014, 2:29:38 AM
    Author     : Ronal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <sj:head compressed="false" jqueryui="true"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen"/>
        <script>
            function calculateAmount(ele) {
                var id = ele.id;
                var idNum = id.split("-")[1];
                var idAmount = "amount-" + idNum;
                var idCost = "cost-" + idNum;
                var optionTotal = ele.options[ele.selectedIndex].text * document.getElementById(idCost).value;
                var oldValue = parseInt(document.getElementById(idAmount).value);
                oldValue = (isNaN(oldValue)) ? 0 : oldValue;
                document.getElementById(idAmount).value = optionTotal;
                var currTot = parseInt(document.getElementById("optionTotal").value);
                currTot = (isNaN(currTot)) ? 0 : currTot;
                document.getElementById("optionTotal").value = currTot + optionTotal - oldValue;
                document.getElementById("optionLable").innerHTML = currTot + optionTotal - oldValue;
            }
        </script>
    </head>
    <body>
        <s:form action="getCustomerDetReservation" theme="simple">
            <div style="height: 800px; width: 100%">
                <div id="content1" align="center">
                    <h1> CAR4Rent - Car Rental System </h1>

                    <!--=======================================Screen 2=====================================================-->
                    <div id="screen1" class="wrapper" style="width:800px !important;" >

                        <br/>
                        <div id="screen2" class="wrapper" style="width:600px !important;" > 
                            <h3 align="left" style="padding-left:5px;">Your Car </h3>
                            <hr color="#e1dada"  style="margin-top:3px;"/>
                            <table cellpadding="4" cellspacing="0" border="0" width="100%" align="left" id="getCarextras">
                                <tr>
                                    <td align="left"><strong><c:out value="${myReservation.vehicle.vehicleClass.description}" /></strong><br />
                                        <span style="display:inline-block"><c:out value="${myReservation.vehicle.name}"/></span></td>
                                </tr>      
                                <tr>
                                    <td align="left">Pickup Date</td>
                                    <td align="right"><c:out value="${myReservation.startDate}" /></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td align="left">Dropoff Date</td>
                                    <td align="right"><c:out value="${myReservation.endDate}" /></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td align="left">Number Of Days</td>
                                    <td align="right"><c:out value="${myReservation.noOfDays}" /></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td align="left">Calculation</td>
                                    <td align="right"><c:out value="${myReservation.calcMethod}" /></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td align="left">Rental Amount</td>
                                    <td align="right"><c:out value="${myReservation.base}" /> LKR</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td align="left">Options Amount</td>
                                    <td align="right"><span id="optionLable"></span> LKR</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td align="left">Pick/up Location</td>
                                    <td align="right"><c:out value="${myReservation.location.locName}" /></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td colspan="3">&nbsp;</td>
                                </tr>
                            </table>
                        </div>
                        <br/>

                        <div id="screen5" class="wrapper" style="width:600px !important;" > 
                            <h3 align="left" style="padding-left:5px;">Extra Options</h3>
                            <hr color="#e1dada"  style="margin-top:3px;"/>
                            <table cellpadding="4" cellspacing="0" border="0" width="100%" align="left" id="getCarextras">
                                <tr><td>Option</td><td>Per Day Cost</td><td>Units</td><td>Amount</td></tr>
                                <s:iterator value="optionsList" var="a" status="status">
                                    <tr>
                                        <td><input type="hidden" id="id-${status.count}" name="reservationOptions[${status.count}].options.id" value="${id}" readonly="true"/>
                                            <input type="text" id="option-${status.count}" name="option${status.count}" value="${options}" readonly="true"/></td>
                                        <td> <input type="text" id="cost-${status.count}" name="reservationOptions[${status.count}].price" style="width: 8em" value="${perDayCost}" readonly="true"/></td>
                                        <td> <select id="unit-${status.count}" name="reservationOptions[${status.count}].quantity" onchange="calculateAmount(this);" style="width: 8em" ><option>0</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option></select></td>
                                        <td><input id="amount-${status.count}" name="amount${status.count}" type="text" style="width: 5em" readonly="true"/></td>
                                    </tr>
                                </s:iterator>
                                <tr><td>Total</td><td></td><td></td><td><input id="optionTotal" name="reservation.optionTotal" type="text" style="width: 5em" readonly="true"/></td><td></td></tr>
                            </table>
                        </div>
                        <br/>
                        <div id="screen41" class="wrapper" style="width:600px !important;" > 
                            <h3 align="left" style="padding-left:5px;">Customer Details</h3>
                            <hr color="#e1dada"  style="margin-top:3px;"/>
                            <br />
                            <table cellpadding="4" cellspacing="0" border="0" width="100%" align="left" id="getCarextras">
                                <tr>
                                    <td><button id="btn_back4" type="button" onclick="window.history.back();" style="float:left;">Back</button></td>
                                    <td width="160px"><strong>Email Address:</strong></td>
                                    <td><s:textfield name="reservation.customer.email" /></td> 
                                    <td><s:submit cssClass="" value="Next" name="btn_search" /></td>
                                </tr>

                            </table>
                        </div>  

                        <br/>

                    </div>
                </div>
            </div>
        </s:form>
    </body>
</html>
