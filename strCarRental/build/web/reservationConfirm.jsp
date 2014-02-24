<%-- 
    Document   : customerForm
    Created on : Nov 26, 2013, 9:21:58 AM
    Author     : Ronal
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <sj:head/>
        <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen"/>
        <script type="text/javascript">
            function validate() {
                if (!document.getElementById('tos').checked) {
                    alert("You didn't check the terms and conditions.");
                    return false;
                } 
            }
        </script>
    </head>
    <body>

        <div style="height: 800px; width: 100%">
            <div id="content1" align="center">
                <h1> CAR4Rent - Car Rental System </h1>

                <div id="screen4" class="wrapper" style="width:600px !important;" > 
                    <h2 align="left" style="padding-left:5px;">Rental Details</h2>
                    <hr color="#e1dada"  style="margin-top:3px;"/>
                    <s:form action="finalConfirmationReservation" theme="simple" onsubmit="return validate();">
                        <table cellpadding="4" cellspacing="1" border="0" width="100%" bgcolor="#FFFFFF" style="font-size:13px;">
                            <tr>
                                <td bgcolor="#f2f2f2" align="left" colspan="2"><strong>Pick-up Location</strong></td>
                                <td bgcolor="#f2f2f2" align="left" colspan="2"><strong>Booking Reference</strong></td>
                            </tr>
                            <tr>
                                <td bgcolor="#ffffff" align="left" colspan="2"><c:out value="${myReservation.location.locName}" /></td>
                                <td bgcolor="#ffffff" align="left" colspan="2"><c:out value="${myReservation.referance}" /></td>
                            </tr>
                            <tr>
                                <td bgcolor="#f2f2f2" align="left" colspan="1"><strong>Pick-up Date &amp; Time</strong></td>
                                <td bgcolor="#f2f2f2" align="left" colspan="1"><strong>Drop-off Date &amp; Time</strong></td>
                                <td bgcolor="#f2f2f2" align="left" colspan="2"></td>
                            </tr>
                            <tr>
                                <td bgcolor="#FFFFFF" align="left" colspan="1"><c:out value="${myReservation.startDate}"/></td>
                                <td bgcolor="#FFFFFF" align="left" colspan="1"><c:out value="${myReservation.endDate}"/></td>
                                <td bgcolor="#FFFFFF" align="center" colspan="2"><strong><c:out value="${myReservation.noOfDays}" /> Days </strong></td>
                            </tr>
                            <tr>
                                <td bgcolor="#f2f2f2" align="left"><strong>Car Type</strong></td>
                                <td bgcolor="#f2f2f2" align="left"><strong>Car Vendor</strong></td>
                                <td bgcolor="#f2f2f2" align="left"><strong>Car Model</strong></td>
                                <td bgcolor="#f2f2f2" align="right" style="padding-right:10px;"><strong>Gross Total</strong></td>
                            </tr>
                            <tr>
                                <td bgcolor="#FFFFFF" align="left"><c:out value="${myReservation.vehicle.vehicleClass.description}"/></td>
                                <td bgcolor="#FFFFFF" align="left"><c:out value="${myReservation.vehicle.vehicleModel.description}"/></td>
                                <td bgcolor="#FFFFFF" align="left"><c:out value="${myReservation.vehicle.name}"/></td>
                                <td bgcolor="#FFFFFF" align="right" style="padding-right:10px;"><c:out value="${myReservation.base}" /> LKR</td>
                            </tr> 
                            <tr>
                                <td bgcolor="#f2f2f2" align="left" colspan="4"><strong>Rental Options</strong></td>
                            </tr><tr >
                                <td bgcolor="#FFFFFF" align="left" colspan="3"> Optional Total</td>
                                <td bgcolor="#FFFFFF" align="right" style="padding-right:10px;"><c:out value="${myReservation.optionTotal}" /> LKR</td>
                            </tr>      <tr>
                                <td bgcolor="#f2f2f2" align="right" colspan="3"><strong>Sub Total</strong></td>
                                <td bgcolor="#f2f2f2" align="right" style="padding-right:10px;"><strong>    <c:out value="${myReservation.net}" />      LKR   </strong></td>
                            </tr>
                            <tr>
                                <td bgcolor="#FFFFFF" align="right" colspan="3"><strong>Tax &amp; Fees(0.00%)</strong></td>
                                <td bgcolor="#FFFFFF" align="right" style="padding-right:10px;">0.00</td>
                            </tr>
                            <tr>
                                <td bgcolor="#f2f2f2" align="right" colspan="3"><strong>Grand Total</strong></td>
                                <td bgcolor="#f2f2f2" align="right" style="padding-right:10px;"><strong>   <c:out value="${myReservation.net}" />    LKR      </strong></td>
                            </tr>


                            <tr>
                                <td>&nbsp;</td>
                                <td><input type="checkbox" name="tos" id="tos" value="" style="width:15px !important"   class="required"/>
                                    &nbsp;
                                    I agree with the <a href="javascript: ;" onclick="window.open('terms.html', 'terms', 'width=500px, height=300px, left=200, top=100, scrollbars=yes, resizable=yes'); return false;"> Terms & Conditions.</a></td>
                            </tr>
                            <tr>
                                <td height="50"></td>
                                <td><button id="btn_back4" type="button" onclick="window.history.back();" style="float:left;">Back</button></td>

                                <td><s:submit value="Confirm Booking"  name="submit"/></td>
                            </tr>

                        </table>
                    </s:form>
                </div>
            </div>
        </div>

    </body>
</html>
