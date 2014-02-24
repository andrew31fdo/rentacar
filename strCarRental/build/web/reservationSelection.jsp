<%-- 
    Document   : reservationSelection
    Created on : Jan 10, 2014, 2:29:38 AM
    Author     : Ronal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html>
    <head>
        <sj:head compressed="false" jqueryui="true"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen"/>
        <script type="text/javascript" >        
                function validate() {
                   
                    if (document.getElementById('txtFromDate').value === "") {
                        alert('Please Enter Pick-up Date');
                        return false;
                    } else if (document.getElementById('txtToDate').value === "") {
                        alert('Please Enter Check Out Date');
                        return false;
                    } else {
                        return true;
                    }
                }
            
        </script>
        
    </head>
    <body>
        <div style="height: 800px; width: 100%">
            <div id="content1" align="center">
                <h1> CAR4Rent - Car Rental System </h1>

                <!--=======================================Screen 2=====================================================-->
                <div id="screen1"  class="wrapper" style="width:800px !important;" >
                    <h2 align="center" >Car Rental Reservations</h2> 
                  <hr color="#e1dada"  style="margin-top:3px;"/>
                  
                  <div id="screen1"  class="wrapper" style="width:400px !important;" >
                      <br/>
                      <s:form action="getVehicleReservation" onsubmit="return validate();">
                          <s:select id="pickuploc" id="myloc" name="myLocation.id" label="Pick-up Location" value="" list="locationList" listKey="id" listValue="locName"/>
                        <s:select id="vehicleClass" name="myVehicleClass.id" label="Vehicle Type" value="" list="vehicleClassList" listKey="id" listValue="description"/>
                        <sj:datepicker name="pickupDate" id="txtFromDate"  label="Pickup Date" value="%{reservation.startDate}" displayFormat="yy-mm-dd" timepickerFormat="hh:mm" timepickerAmPm="false" minDate="0" maxDate="+2m" timepickerStepMinute="15" timepickerGridHour="3" timepicker="true" />
                        <sj:datepicker name="DropOffDate" id="txtToDate" label="Dropoff Date" value="%{reservation.endDate}" displayFormat="yy-mm-dd" timepickerFormat="hh:mm" timepickerAmPm="false" minDate="0" maxDate="+6m"  timepickerStepMinute="15" timepickerGridHour="3" timepicker="true"  />
                        <s:submit cssClass="" value="Search Vehicles" name="btn_search" />
                    </s:form>
                      <br/>
                      <div id="screen1"  class="wrapper" style="width:300px !important;" >
                    <img src="img/car-home1.png" align="right"   />
                  </div>
                  </div>
                  
                </div>
            </div>
        </div>
    </body>
</html>
