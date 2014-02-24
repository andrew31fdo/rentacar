<%-- 
    Document   : reservationSelection
    Created on : Jan 10, 2014, 2:29:38 AM
    Author     : Ronal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html>
    <head>
        <sj:head compressed="false" jqueryui="true"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen"/>

    </head>
    <body>
        <div style="height: 800px; width: 100%">
            <div id="content1" align="center">
                <h1> CAR4Rent - Car Rental System </h1>

                <!--=======================================Screen 2=====================================================-->
                <div id="screen1" class="wrapper" style="width:800px !important;" >
                    <h2 align="left" style="padding-left:5px;">Car Rental Reservations</h2> 

                    <hr color="#e1dada"  style="margin-top:3px;"/>
                    <br/>
                    <s:form action="" theme="simple">

                        <div id="screen2" class="wrapper" style="width:500px !important;" > 
                            <h3 align="left" style="padding-left:5px;">Your Selection </h3>
                            <hr color="#e1dada"  style="margin-top:3px;"/>
                            <table cellpadding="4" cellspacing="0" border="0" width="100%" align="left" id="getCarextras">
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
                                    <td align="left">Pick/up Location</td>
                                    <td align="right"><c:out value="${myReservation.location.locName}" /></td>
                                    <td></td>
                                </tr>
<td><button id="btn_back4" type="button" onclick="window.history.back();" style="float:left;">Back</button></td>
                                <tr>
                                    <td colspan="3">&nbsp;</td>
                                </tr>
                            </table>
                        </div>

                        <sj:accordion id="accordion" heightStyle="content" animate="true" openOnMouseover="true">
                            <s:iterator value="vehicleList" var="idx" status="sta">     
                                <s:set name="title1"><s:property value="vehicleClass.description"/>(<s:property value="name"/>)</s:set> 
                                <sj:accordionItem title="%{title1}" >
                                    <div id="screen21" class="wrapper" style="height:250px; width:750px !important;" > 
                                        <br/>
                                        <div id="screen23" class="wrapper" style="width:350px !important;" > 
                                            <table cellpadding="4"  cellspacing="0" border="0" width="100%" align="left" id="getCarextras">
                                                <tr>
                                                    <td colspan="4" style="font-size: small ;text-align: center">
                                                        Name: <s:property value="name"/><br/>
                                                        Chase Number: <s:property value="chaseNumber"/><br/>
                                                        Milage: <s:property value="currentMilage"/><br/>
                                                        Daily Limit: <s:property value="dailyMilage"/><br/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="width:50%;font-size: small ;"><strong>Features </strong> <br/>
                                                        <s:iterator value="features">
                                                            <s:property value="description"/><br/>
                                                        </s:iterator></td>
                                                    <td style="font-size: small ;"><strong>Rates </strong> <br/>

                                                        <s:iterator value="rates">
                                                            <s:property value="rateType.description"/>&nbsp;-&nbsp; LKR <s:property value="perDayCost"/><br/>
                                                        </s:iterator>
                                                        <br/> </td>
                                                </tr>
                                                <tr>
                                                <br/>

                                                </tr>
                                            </table>
                                        </div> &nbsp;&nbsp;&nbsp;
                                        <div id="screen22" class="wrapper" style="height: available;max-height: 100%; width:200px !important;" > 
                                            <div id="screen2" class="wrapper" style="width:100px!important;background: #009900" > 
                                                <s:url action="getOptionReservation" id="url"><s:param name="vehicle.id"><s:property value="id"/> </s:param></s:url>
                                                <a href="<s:property value='#url'/>">Add me!</a>
                                            </div>
                                            <img src="img/cars/<s:property value="imagePath"/>" style="width: 100%;max-height: 100%" alt="<s:property value="name"/>">
                                        </div>
                                        <br/>
                                    </div>
                                </sj:accordionItem>
                            </s:iterator>

                        </sj:accordion>

                    </s:form>

                </div>
            </div>
        </div>
    </body>
</html>
