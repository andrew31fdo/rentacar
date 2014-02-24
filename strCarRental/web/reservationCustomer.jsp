<%-- 
    Document   : customerForm
    Created on : Nov 26, 2013, 9:21:58 AM
    Author     : Ronal
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
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

        <div style="height: 800px; width: 100%">
            <div id="content1" align="center">
                <h1> CAR4Rent - Car Rental System </h1>

                <!--=======================================Screen 2=====================================================-->
                <div id="screen1" class="wrapper" style="width:800px !important;" >
                    <br/>
                        <div id="screen2" class="wrapper" style="width:400px !important;" > 
                            <h3 align="left" style="padding-left:5px;">Your Car </h3>
                            <hr color="#e1dada"  style="margin-top:3px;"/>
                            <table cellpadding="4" cellspacing="0" border="0" width="100%" align="left" id="getCarextras">
                                <tr>
                                    <td align="left"><strong><c:out value="${myReservation.vehicle.vehicleClass.description}" /></strong>
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
                                    <td align="right"><c:out value="${myReservation.optionTotal}" /></span> LKR</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td align="left">Pick/up Location</td>
                                    <td align="right"><c:out value="${myReservation.location.locName}" /></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    
                                </tr>
                            </table>
                        </div>
                              <div id="screen2" class="wrapper" style="width:300px !important;" >       
                                  <h3 align="left" style="padding-left:5px;">Car Image </h3>
                            <hr color="#e1dada"  style="margin-top:3px;"/>
                                    <img src="img/cars/<c:out value="${myReservation.vehicle.imagePath}" />" style="width: 100%;height: 190px" alt="<s:property value="%{reservation.vehicle.name}"/>">
                              </div>
                    <br/><br/>
                    <div id="screen2" class="wrapper" style="width:600px !important;" > 
                        
                        <s:if test="reservation.customer==null || reservation.customer.id == null">
                            <s:set name="title" value="%{'Add new Customer'}"/>
                        </s:if>
                        <s:else>
                            <s:set name="title" value="%{'Update Customer'}"/>
                        </s:else>
                        
                        <h3 align="left" style="padding-left:5px;"><s:property value="#title"/></h3>
                        <hr color="#e1dada"  style="margin-top:3px;"/>
                        <s:form action="saveCustomerReservation">
                            <s:textfield name="reservation.customer.name1"  label="First Name" value="%{reservation.customer.name1}"></s:textfield>
                            <s:textfield name="reservation.customer.name2"  label="Last Name" value="%{reservation.customer.name2}"></s:textfield>
                            <s:textfield name="reservation.customer.address1"  label="Street, NO" value="%{reservation.customer.address1}"></s:textfield>
                            <s:textfield name="reservation.customer.address2"  label="Address Line2" value="%{reservation.customer.address2}"></s:textfield>
                            <s:textfield name="reservation.customer.city"  label="City" value="%{reservation.customer.city}"></s:textfield>
                            <s:textfield name="reservation.customer.postCode"  label="Postal Code" value="%{reservation.customer.postCode}"></s:textfield>
                            <s:textfield name="reservation.customer.country"  label="Country" value="%{reservation.customer.country}"></s:textfield>
                            <s:textfield name="reservation.customer.nic"  label="NIC" value="%{reservation.customer.nic}"></s:textfield>
                            <s:textfield name="reservation.customer.passport"  label="Passport" value="%{reservation.customer.passport}"></s:textfield>
                            <s:textfield name="reservation.customer.drivingLicence"  label="Driving Licence" value="%{reservation.customer.drivingLicence}"></s:textfield>
                            <sj:datepicker name="reservation.customer.dob" id="date0" label="DOB" value="%{reservation.customer.dob}" displayFormat="yy-mm-dd" changeMonth="true" changeYear="true" />
                            <s:textfield name="reservation.customer.picture"  label="Picture" value="%{reservation.customer.picture}"></s:textfield>
                            <s:textfield name="reservation.customer.email"  label="Email" value="%{reservation.customer.email}"></s:textfield>
                            
                            <s:hidden name="reservation.customer.id" value="%{reservation.customer.id}" />
                            <!--<button id="btn_back4" type="button" onclick="window.history.back();" styl="float:left;">Back</button>-->
                            <s:submit value="Update!" name="submit" />
                        </s:form>
                    </div>
                        <br/>
                </div>
            </div>
        </div>
    </body>
</html>