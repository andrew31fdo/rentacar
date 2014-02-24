<%@taglib uri="/struts-tags" prefix="s"%>

<html>
    <head>
    </head>
    <body>
        <h2>Customer</h2>
        <a href="viewAllCustomerType.action">Customer Type</a><br/> 
        <a href="viewAllCustomer.action">Customer</a><br/>

        <h2>Vehicle</h2>
        <a href="viewAllFeature.action">Car Features</a><br/>   
        <a href="viewAllMake.action">Car Make</a><br/>   
        <a href="viewAllOptions.action">Extra Options</a><br/>   
        <a href="viewAllVehicleClass.action">Vehicle Class</a><br/>   
        <a href="viewAllVehicleModel.action">Vehicle Model</a><br/>
        <a href="viewAllVehicle.action">Vehicle</a><br/>
        <a href="pictureUploadVehicle.action">Upload Vehicle Image</a><br/>
        
        <h2>Reservations</h2>
        <a href="viewAllLocation.action">Locations</a><br/> 
        <a href="viewAllReservation.action">All Reservations</a><br/> 
        <s:url action="addReservation" id="url"></s:url>
        <a href="<s:property value='#url'/>"><img src="<s:url value="/img/add.png"/>" />Reservation</a><br/>

        <h2>Rates</h2>
        <a href="viewAllRate.action">Rate</a><br/>   
        <a href="viewAllRateType.action">Rate Type</a><br/>          
           
        <h2>Settings</h2>
        <a href="viewAllUser.action">User</a><br/>  
    </body>
</html>


