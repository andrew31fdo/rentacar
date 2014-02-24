<%-- 
    Document   : cusIndex
    Created on : Jan 8, 2014, 11:05:40 AM
    Author     : Ronal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <sj:head/>
        <title>Car Rental System</title>
        <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen"/>
        <link rel="stylesheet" type="text/css" href="resources/css/jquery-ui-1.8.22.custom.css" />
        <link rel="stylesheet" type="text/css" href="resources/css/accordion.css"  />

        <script type="text/javascript" src="resources/js/jquery.min.js"></script>
        <script type="text/javascript" src="resources/js/jquery-ui-1.8.22.custom.min.js"></script>
        <script type="text/javascript" src="resources/js/jquery.ui.datepicker-en.js"></script>

        <script type="text/javascript">
            $(document).ready(function() {
                $.datepicker.setDefaults({dateFormat: 'mm/dd/yy'});
                var unavailableDates = ['2013-04-10', '2013-04-19', '2013-05-16', '2013-06-14', '2013-06-25', '2013-07-18', '2013-08-16', '2013-09-13', '2013-10-18', '2013-11-22', '2013-12-13', '2013-12-28', '2014-01-15', '2014-02-13'];
                function unavailable(date) {
                    ymd = date.getFullYear() + "-" + ("0" + (date.getMonth() + 1)).slice(-2) + "-" + ("0" + date.getDate()).slice(-2);
                    day = new Date(ymd).getDay();
                    if ($.inArray(ymd, unavailableDates) < 0) {
                        return [true, "enabled", ""];
                    } else {
                        return [false, "disabled", "we are closed"];
                    }
                }
                $("#txtFromDate").datepicker({
                    minDate: "+D",
                    maxDate: "+730D",
                    beforeShowDay: unavailable,
                    numberOfMonths: 2,
                    onSelect: function(selected) {
                        var date = $(this).datepicker('getDate');
                        if (date) {
                            date.setDate(date.getDate());
                        }
                        $("#txtToDate").datepicker("option", "minDate", date)
                    }
                });
                $("#txtToDate").datepicker({
                    minDate: 0,
                    beforeShowDay: unavailable,
                    maxDate: "+730D",
                    numberOfMonths: 2,
                    onSelect: function(selected) {
                        $("#txtFromDate").datepicker("option", "maxDate", selected)
                    }
                });

                $("#datepickerImage").click(function() {
                    $("#txtFromDate").datepicker("show");
                });
                $("#datepickerImage1").click(function() {
                    $("#txtToDate").datepicker("show");
                });

                //Initial hide
                $('#screen2').hide();
                $('#screen3').hide();
                $('#screen4').hide();
                $('#screen41').hide();
                $('#screen5').hide();
                $('#screen6').hide();

                $('#btn_search').click(function() {
                    if ($('#pickuploc').val() === 0) {
                        alert('Please select Pick-up location.');
                        return false;
                    } else if ($('#txtFromDate').val() === "") {
                        alert('Please Enter Pick-up Date');
                        return false;
                    } else if ($('#txtToDate').val() === "") {
                        alert('Please Enter Check Out Date');
                        return false;
                    } else {
                        var params = 'loc='+pickuploc+'&vclass='+vehicleClass; 
                        $.ajax({
                            url: 'getVehicleReservation.action',
                            type: 'GET',
                            data: params,
                            success: function(result) {
                                document.getElementById('showmsg').innerHTML = result;
                            }
                        });
                        $('#screen1').hide();
                        $('#screen2').show();
                        return true;
                    }
                });

                $('#btn_select').click(function() {
                    $('#screen2').hide();
                    $('#screen3').show();
                });

                $('#btn_option').click(function() {
                    $('#screen3').hide();
                    $('#screen4').show();
                    $('#screen41').show();
                });

                $('#btn_new_cust').click(function() {
                    $('#screen4').hide();
                    $('#screen41').hide();
                    $('#screen5').show();
                });

                $('#btn_book').click(function() {
                    $('#screen5').hide();
                    $('#screen6').show();
                });

                $('#btn_exisitng_cust').click(function() {
                    $('#screen4').hide();
                    $('#screen41').hide();
                    $('#screen5').show();
                    $('#exist_wait').html("<img src='img/ajax-loader.gif' border='0'>");
                    var querystr = 'actioncode=1&existing_email=' + $('#email_addr_existing').val();
                    $.post("ajax_processor.php", querystr, function(data) {
                        if (data.errorcode === 0) {
                            $('#title').html(data.title);
                            $('#fname').val(data.first_name);
                            $('#lname').val(data.surname);
                            $('#str_addr').val(data.street_addr);
                            $('#city').val(data.city);
                            $('#state').val(data.province);
                            $('#zipcode').val(data.zip);
                            $('#country').val(data.country);
                            $('#phone').val(data.phone);
                            $('#fax').val(data.fax);
                            $('#email').val(data.email);
                            $('#exist_wait').html("");
                        } else {
                            alert(data.strmsg);
                            $('#fname').val('');
                            $('#lname').val('');
                            $('#str_addr').val('');
                            $('#city').val('');
                            $('#state').val('');
                            $('#zipcode').val('');
                            $('#country').val('');
                            $('#phone').val('');
                            $('#fax').val('');
                            $('#email').val('');
                            $('#exist_wait').html("");
                        }
                    }, "json");
                });

                $('#btn_back1').click(function() {
                    $('#screen2').hide();
                    $('#screen1').show();
                });

                $('#btn_back2').click(function() {
                    $('#screen3').hide();
                    $('#screen2').show();
                });

                $('#btn_back3').click(function() {
                    $('#screen4').hide();
                    $('#screen41').hide();
                    $('#screen3').show();
                });

                $('#btn_back4').click(function() {
                    $('#screen5').hide();
                    $('#screen4').show();
                    $('#screen41').show();
                });

                // Content Box Accordion Config		
                $(".content_accordion").accordion({
                    collapsible: true,
                    active: false,
                    header: 'span.bar', // this is the element that will be clicked to activate the accordion 
                    autoHeight: false,
                    icons: false,
                    animated: true
                });
                $(".content_accordion input").click(function(e) {
                    e.stopPropagation();
                });

            });
            function myPopup2() {
                var width = 730;
                var height = 650;
                var left = (screen.width - width) / 2;
                var top = (screen.height - height) / 2;
                var url = 'terms-and-services.php';
                var params = 'width=' + width + ', height=' + height;
                params += ', top=' + top + ', left=' + left;
                params += ', directories=no';
                params += ', location=no';
                params += ', menubar=no';
                params += ', resizable=no';
                params += ', scrollbars=yes';
                params += ', status=no';
                params += ', toolbar=no';
                newwin = window.open(url, 'Chat', params);
                if (window.focus) {
                    newwin.focus()
                }
                return false;
            }
        </script>

    </head>
    <body>
        <div style="height: 600px; width: 100%">
            <div id="content1" align="center">
                <h1> CAR4Rent - Car Rental System </h1>

                <!--=======================================Screen 2=====================================================-->
                <div id="screen1" class="wrapper" style="width:600px !important;" >
                    <h2 align="left" style="padding-left:5px;">Car Rental Reservations</h2> 
                    <hr color="#e1dada"  style="margin-top:3px;"/>
                    <img src="img/car-home1.png" align="right"   />

                    <table id="tbl1" cellpadding="0"  cellspacing="7" border="0"  align="left" style="text-align:left;">
                        <tr>
                            <td><s:select id="pickuploc" name="myLocation.id" label="Pick-up Location" value="" list="locationList" listKey="id" listValue="locName"/>
                            </td>
                        </tr>

                        <tr>
                            <td><s:select id="vehicleClass" name="myVehicleClass.id" label="Vehicle Type" value="" list="vehicleClassList" listKey="id" listValue="description"/></td>
                        </tr>
                        <tr>
                            <td><strong>Pick-Up Date:</strong></td>
                            <td><input id="txtFromDate" name="pickup" style="width:68px" type="text" readonly="readonly" AUTOCOMPLETE=OFF />
                                <span style="padding-left:0px;"><a id="datepickerImage" href="javascript:;"><img src="img/month.png" height="16px" width="16px" style=" margin-bottom:-4px;" border="0" /></a></span> <select name="pickUpTime"  style="width:90px;">
                                    <option value="00:00:00">12:00 AM</option>
                                    <option value="00:30:00">12:30 AM</option>
                                    <option value="01:00:00">1:00 AM</option>
                                    <option value="01:30:00">1:30 AM</option>
                                    <option value="02:00:00">2:00 AM</option>
                                    <option value="02:30:00">2:30 AM</option>
                                    <option value="03:00:00">3:00 AM</option>
                                    <option value="03:30:00">3:30 AM</option>
                                    <option value="04:00:00">4:00 AM</option>
                                    <option value="04:30:00">4:30 AM</option>
                                    <option value="05:00:00">5:00 AM</option>
                                    <option value="05:30:00">5:30 AM</option>
                                    <option value="06:00:00">6:00 AM</option>
                                    <option value="06:30:00">6:30 AM</option>
                                    <option value="07:00:00">7:00 AM</option>
                                    <option value="07:30:00">7:30 AM</option>
                                    <option value="08:00:00">8:00 AM</option>
                                    <option value="08:30:00">8:30 AM</option>
                                    <option value="09:00:00" selected="selected">9:00 AM</option>
                                    <option value="09:30:00">9:30 AM</option>
                                    <option value="10:00:00">10:00 AM</option>
                                    <option value="10:30:00">10:30 AM</option>
                                    <option value="11:00:00">11:00 AM</option>
                                    <option value="11:30:00">11:30 AM</option>
                                    <option value="12:00:00">12:00 PM</option>
                                    <option value="12:30:00">12:30 PM</option>
                                    <option value="13:00:00">1:00 PM</option>
                                    <option value="13:30:00">1:30 PM</option>
                                    <option value="14:00:00">2:00 PM</option>
                                    <option value="14:30:00">2:30 PM</option>
                                    <option value="15:00:00">3:00 PM</option>
                                    <option value="15:30:00">3:30 PM</option>
                                    <option value="16:00:00">4:00 PM</option>
                                    <option value="16:30:00">4:30 PM</option>
                                    <option value="17:00:00">5:00 PM</option>
                                    <option value="17:30:00">5:30 PM</option>
                                    <option value="18:00:00">6:00 PM</option>
                                    <option value="18:30:00">6:30 PM</option>
                                    <option value="19:00:00">7:00 PM</option>
                                    <option value="19:30:00">7:30 PM</option>
                                    <option value="20:00:00">8:00 PM</option>
                                    <option value="20:30:00">8:30 PM</option>
                                    <option value="21:00:00">9:00 PM</option>
                                    <option value="21:30:00">9:30 PM</option>
                                    <option value="22:00:00">10:00 PM</option>
                                    <option value="22:30:00">10:30 PM</option>
                                    <option value="23:00:00">11:00 PM</option>
                                    <option value="23:30:00">11:30 PM</option>
                                </select></td>
                        </tr>
                        <tr>
                            <td><strong>Drop-Off Date:</strong></td>
                            <td><input id="txtToDate" name="dropoff" style="width:68px" type="text" readonly="readonly" AUTOCOMPLETE=OFF />
                                <span style="padding-left:0px;"><a id="datepickerImage1" href="javascript:;"><img src="img/month.png" height="18px" width="18px" style=" margin-bottom:-4px;" border="0" /></a></span>  <select name="dropoffTime"  style="width:90px;">
                                    <option value="00:00:00">12:00 AM</option>
                                    <option value="00:30:00">12:30 AM</option>
                                    <option value="01:00:00">1:00 AM</option>
                                    <option value="01:30:00">1:30 AM</option>
                                    <option value="02:00:00">2:00 AM</option>
                                    <option value="02:30:00">2:30 AM</option>
                                    <option value="03:00:00">3:00 AM</option>
                                    <option value="03:30:00">3:30 AM</option>
                                    <option value="04:00:00">4:00 AM</option>
                                    <option value="04:30:00">4:30 AM</option>
                                    <option value="05:00:00">5:00 AM</option>
                                    <option value="05:30:00">5:30 AM</option>
                                    <option value="06:00:00">6:00 AM</option>
                                    <option value="06:30:00">6:30 AM</option>
                                    <option value="07:00:00">7:00 AM</option>
                                    <option value="07:30:00">7:30 AM</option>
                                    <option value="08:00:00">8:00 AM</option>
                                    <option value="08:30:00">8:30 AM</option>
                                    <option value="09:00:00" selected="selected">9:00 AM</option>
                                    <option value="09:30:00">9:30 AM</option>
                                    <option value="10:00:00">10:00 AM</option>
                                    <option value="10:30:00">10:30 AM</option>
                                    <option value="11:00:00">11:00 AM</option>
                                    <option value="11:30:00">11:30 AM</option>
                                    <option value="12:00:00">12:00 PM</option>
                                    <option value="12:30:00">12:30 PM</option>
                                    <option value="13:00:00">1:00 PM</option>
                                    <option value="13:30:00">1:30 PM</option>
                                    <option value="14:00:00">2:00 PM</option>
                                    <option value="14:30:00">2:30 PM</option>
                                    <option value="15:00:00">3:00 PM</option>
                                    <option value="15:30:00">3:30 PM</option>
                                    <option value="16:00:00">4:00 PM</option>
                                    <option value="16:30:00">4:30 PM</option>
                                    <option value="17:00:00">5:00 PM</option>
                                    <option value="17:30:00">5:30 PM</option>
                                    <option value="18:00:00">6:00 PM</option>
                                    <option value="18:30:00">6:30 PM</option>
                                    <option value="19:00:00">7:00 PM</option>
                                    <option value="19:30:00">7:30 PM</option>
                                    <option value="20:00:00">8:00 PM</option>
                                    <option value="20:30:00">8:30 PM</option>
                                    <option value="21:00:00">9:00 PM</option>
                                    <option value="21:30:00">9:30 PM</option>
                                    <option value="22:00:00">10:00 PM</option>
                                    <option value="22:30:00">10:30 PM</option>
                                    <option value="23:00:00">11:00 PM</option>
                                    <option value="23:30:00">11:30 PM</option>
                                </select></td>
                        </tr>

                        <tr>
                            <td></td>
                            <td align="left"><button id="btn_search" type="button" style="float:left">Search Now</button></td>
                        </tr>
                    </table>
                </div>
            </div>

            <!--=======================================Screen 2=====================================================-->
            <div id="content2" align="center">
                <div id="screen2" class="wrapper" style="width:600px !important;" > 
                    <h2 align="left" style="padding-left:5px;">Search Result  </h2>
                    <hr color="#e1dada"  style="margin-top:3px; margin-bottom:3px;"/>
                    <h3 align="left" style="padding-left:5px;">
                        01/16/2014      9:00 AM      -
                        01/17/2014      9:00 AM      <span style="color:#63F">= 1 Days</span>
                    </h3>
                    <hr color="#e1dada"  style="margin-top:3px;"/>
                    <span id="showmsg"></span>
                    <table cellpadding="5" border="0" width="100%" style="background:#936; color:#FFF">
                        <tr style="height:30px;">
                            <th align="left" width="34%" style="padding-left:10px;">CAR CLASS / TYPE  </th>
                            <th  width="25%">TOTAL PRICE</th>
                            <th align="right"  width="31%" style="padding-right:10px;">AMOUNT PREPAID( 100% )</th>
                            <th width="10%"></th>
                        </tr>
                    </table>
                    <ul class="content_accordion">
                        <li><span class="bar" title="click here to expand/collapse !!!">
                                <table cellpadding="3"  cellspacing="3" border="0" width="100%">
                                    <tr>
                                        <td align="left" width="34%" style=" line-height:normal;"><strong>
                                                Subcompact              </strong><br />
                                            Hyundai              <span style="display:inline-block">
                                                Accent              </span></td>
                                        <td  width="25%" align="center"><strong>
                                                $40.00 USD              </strong></td>
                                        <td align="right"  width="31%"><strong>
                                                $40.00 USD              </strong>&nbsp;&nbsp;
                                        </td>
                                        <td  width="10%" align="center">
                                            <input type="radio" value="1" name="car[]" class="required" style=" width:auto !important;"  />
                                        </td>
                                    </tr>
                                </table>
                            </span>
                            <div class="content" align="left">
                                <table cellpadding="3"  cellspacing="0" border="0" width="100%">

                                    <tr>            
                                        <td width="240" rowspan="2" valign="top">            
                                            <img src="gallery/thumb_1363256223_hyundai_accent_2dr.gif" align="left" />              
                                        </td>
                                        <td align="left"><strong>Mileage: Unlimited</strong></td><td align="left"><strong>Fuel Type: Diesel</strong></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" align="left" >				
                                            <ul style="list-style:square; padding-left:0px;"><li>4 Door</li><li>5 Seater</li><li>Air Conditioning</li></ul>
                                        </td>
                                        <td valign="top">
                                            <ul style="list-style:square; padding-left:0px;"><li>AM/FM Radio</li><li>Automatic</li></ul>
                                        </td>            </tr>

                                </table>

                            </div>
                        </li>


                        <li><span class="bar" title="click here to expand/collapse !!!">
                                <table cellpadding="3"  cellspacing="3" border="0" width="100%">
                                    <tr>
                                        <td align="left" width="34%" style=" line-height:normal;"><strong>
                                                Premium              </strong><br />
                                            Ford              <span style="display:inline-block">
                                                Crown              </span></td>
                                        <td  width="25%" align="center"><strong>
                                                $50.00 USD              </strong></td>
                                        <td align="right"  width="31%"><strong>
                                                $50.00 USD              </strong>&nbsp;&nbsp;
                                        </td>
                                        <td  width="10%" align="center">
                                            <input type="radio" value="2" name="car[]" class="required" style=" width:auto !important;"  />
                                        </td>
                                    </tr>
                                </table>
                            </span>
                            <div class="content" align="left">
                                <table cellpadding="3"  cellspacing="0" border="0" width="100%">

                                    <tr>            
                                        <td width="240" rowspan="2" valign="top">            
                                            <img src="gallery/thumb_1363256403_1343207322_ford_crown_victoria_us.gif" align="left" />              
                                        </td>
                                        <td align="left"><strong>Mileage: Unlimited</strong></td><td align="left"><strong>Fuel Type: Petrol</strong></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" align="left" >				
                                            <ul style="list-style:square; padding-left:0px;"><li>4 Door</li><li>5 Seater</li><li>Air Conditioning</li><li>AM/FM Radio</li></ul>
                                        </td>
                                        <td valign="top">
                                            <ul style="list-style:square; padding-left:0px;"><li>Automatic</li><li>CD Player</li><li>Cruise Control</li></ul>
                                        </td>            </tr>

                                </table>

                            </div>
                        </li>


                        <li><span class="bar" title="click here to expand/collapse !!!">
                                <table cellpadding="3"  cellspacing="3" border="0" width="100%">
                                    <tr>
                                        <td align="left" width="34%" style=" line-height:normal;"><strong>
                                                Intermediate              </strong><br />
                                            Chevrolet              <span style="display:inline-block">
                                                Cruze              </span></td>
                                        <td  width="25%" align="center"><strong>
                                                $80.00 USD              </strong></td>
                                        <td align="right"  width="31%"><strong>
                                                $80.00 USD              </strong>&nbsp;&nbsp;
                                        </td>
                                        <td  width="10%" align="center">
                                            <input type="radio" value="4" name="car[]" class="required" style=" width:auto !important;"  />
                                        </td>
                                    </tr>
                                </table>
                            </span>
                            <div class="content" align="left">
                                <table cellpadding="3"  cellspacing="0" border="0" width="100%">

                                    <tr>            
                                        <td width="240" rowspan="2" valign="top">            
                                            <img src="gallery/thumb_1363256684_1343207576_chevrolet_cruze.gif" align="left" />              
                                        </td>
                                        <td align="left"><strong>Mileage: 500km</strong></td><td align="left"><strong>Fuel Type: Petrol</strong></td>
                                    </tr>
                                    <tr>
                                        <td valign="top" align="left" >				
                                            <ul style="list-style:square; padding-left:0px;"><li>4 Door</li><li>5 Seater</li><li>Air Conditioning</li></ul>
                                        </td>
                                        <td valign="top">
                                            <ul style="list-style:square; padding-left:0px;"><li>AM/FM Radio</li><li>Automatic</li></ul>
                                        </td>            </tr>

                                </table>

                            </div>
                        </li>


                    </ul>
                    <label class="error" generated="true" for="car[]" style="display:none;">please select at least one car.</label><br />
                    <button id="btn_back1" type="button" >Back</button>
                    <button id="btn_select" type="button" >Select a Car</button>

                </div>
            </div>
            <!--=======================================Screen 3=====================================================-->
            <div id="content3" align="center">
                <div id="screen3" class="wrapper" style="width:600px !important;" > 
                    <h3 align="left" style="padding-left:5px;">Selected Car (
                        01/16/2014      9:00 AM      -
                        01/17/2014      9:00 AM      
                        = 1 Days      )</h3>
                    <hr color="#e1dada"  style="margin-top:3px;"/>

                    <table cellpadding="4" cellspacing="0" border="0" width="100%" align="left" id="getCarextras">
                        <tr>
                            <td align="left"><strong>Subcompact</strong><br />
                                <span style="display:inline-block">Hyundai Accent</span></td>
                            <td align="right">$40.00 USD</td>
                            <td width="250"></td>
                        </tr>      <tr>
                            <td colspan="2"><hr color="#aaa"  tyle="height:1px !important;"/></td>
                            <td width="250"></td>
                        </tr>
                        <tr>
                            <td align="left">Amount Prepaid (100%)</td>
                            <td align="right">$40.00 USD</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="3">&nbsp;</td>
                        </tr>
                    </table>
                    <h3 align="left" style="padding-left:5px;">Rental Options</h3>
                    <hr color="#e1dada"  style="margin-top:3px;"/>
                    <img src="resources/img/Car-GPS.png" align="right" width="200" style="padding-right:10px; padding-top:10px;" />
                    <table cellpadding="4" cellspacing="0" border="0" width="64%" align="left">
                        <tr>

                            <td align="left"><strong> XM Radio</strong></td>
                            <td align="right"> $5.00</td><td align='left'> USD Per Day </td><td><strong>X</strong></td>
                            <td width="40" align="left">
                                <select name="carExtras[2]" style="width:auto;"><option value="0">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option></select>
                            </td>
                        </tr><tr>

                            <td align="left"><strong> GPS Navigation </strong></td>
                            <td align="right"> $16.00</td><td align='left'> USD Per Day </td><td><strong>X</strong></td>
                            <td width="40" align="left">
                                <select name="carExtras[3]" style="width:auto;"><option value="0">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option></select>
                            </td>
                        </tr><tr>

                            <td align="left"><strong> Child Safety Seats</strong></td>
                            <td align="right"> $14.00</td><td align='left'> USD Per Day </td><td><strong>X</strong></td>
                            <td width="40" align="left">
                                <select name="carExtras[4]" style="width:auto;"><option value="0">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option></select>
                            </td>
                        </tr><tr>

                            <td align="left"><strong> Car Driver</strong></td>
                            <td align="right"> $54.00</td><td align='left'> USD Per Day </td><td><strong>X</strong></td>
                            <td width="40" align="left">
                                <select name="carExtras[5]" style="width:auto;"><option value="0">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option></select>
                            </td>
                        </tr>        <tr>
                            <td colspan="3">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="3"><button id="btn_back2" type="button" >Back</button></td>
                            <td colspan="3"><button id="btn_option" type="button" >Select Options</button></td>
                        </tr>
                        <tr>
                            <td colspan="3">&nbsp;</td>
                        </tr>
                    </table>
                </div>
            </div> 
            <!--=======================================Screen 4=====================================================-->
            <div id="content4" align="center">
                <div id="screen4" class="wrapper" style="width:600px !important;" > 
                    <h2 align="left" style="padding-left:5px;">Rental Details</h2>
                    <hr color="#e1dada"  style="margin-top:3px;"/>
                    <table cellpadding="4" cellspacing="1" border="0" width="100%" bgcolor="#FFFFFF" style="font-size:13px;">
                        <tr>
                            <td bgcolor="#f2f2f2" align="left" colspan="2"><strong>Pick-up Location</strong></td>
                            <td bgcolor="#f2f2f2" align="left" colspan="2"><strong>Drop-off Location</strong></td>
                        </tr>
                        <tr>
                            <td bgcolor="#ffffff" align="left" colspan="2">55 Test Drive, Dallas, TX</td>
                            <td bgcolor="#ffffff" align="left" colspan="2">55 Test Drive, Dallas, TX</td>
                        </tr>
                        <tr>
                            <td bgcolor="#f2f2f2" align="left" colspan="1"><strong>Pick-up Date &amp; Time</strong></td>
                            <td bgcolor="#f2f2f2" align="left" colspan="1"><strong>Drop-off Date &amp; Time</strong></td>
                            <td bgcolor="#f2f2f2" align="left" colspan="2"></td>
                        </tr>
                        <tr>
                            <td bgcolor="#FFFFFF" align="left" colspan="1">01/16/2014          &nbsp;&nbsp;
                                9:00 AM</td>
                            <td bgcolor="#FFFFFF" align="left" colspan="1">01/17/2014          &nbsp;&nbsp;
                                9:00 AM</td>
                            <td bgcolor="#FFFFFF" align="center" colspan="2"><strong> = 1 Days 0 Hours</strong></td>
                        </tr>
                        <tr>
                            <td bgcolor="#f2f2f2" align="left"><strong>Car Type</strong></td>
                            <td bgcolor="#f2f2f2" align="left"><strong>Car Vendor</strong></td>
                            <td bgcolor="#f2f2f2" align="left"><strong>Car Model</strong></td>
                            <td bgcolor="#f2f2f2" align="right" style="padding-right:5px;"><strong>Gross Total</strong></td>
                        </tr>
                        <tr>
                            <td bgcolor="#FFFFFF" align="left">Premium</td>
                            <td bgcolor="#FFFFFF" align="left">Ford</td>
                            <td bgcolor="#FFFFFF" align="left">Crown</td>
                            <td bgcolor="#FFFFFF" align="right" style="padding-right:5px;">$50.00</td>
                        </tr> 
                        <tr>
                            <td bgcolor="#f2f2f2" align="left" colspan="4"><strong>Rental Options</strong></td>
                        </tr><tr >
                            <td bgcolor="#FFFFFF" align="left" colspan="3"> GPS Navigation  x <b>1</b> ( $16.00 x 1day(s) )</td>
                            <td bgcolor="#FFFFFF" align="right" style="padding-right:5px;">$16.00</td>
                        </tr>      <tr>
                            <td bgcolor="#f2f2f2" align="right" colspan="3"><strong>Sub Total</strong></td>
                            <td bgcolor="#f2f2f2" align="right" style="padding-right:5px;"><strong>
                                    $66.00          </strong></td>
                        </tr>
                        <tr>
                            <td bgcolor="#FFFFFF" align="right" colspan="3"><strong>Tax &amp; Fees(
                                    10.00%)</strong></td>
                            <td bgcolor="#FFFFFF" align="right" style="padding-right:5px;">$6.60</td>
                        </tr>
                        <tr>
                            <td bgcolor="#f2f2f2" align="right" colspan="3"><strong>Grand Total</strong></td>
                            <td bgcolor="#f2f2f2" align="right" style="padding-right:5px;"><strong>
                                    $72.60          </strong></td>
                        </tr>
                        <tr>
                            <td bgcolor="#FFFFFF" align="right" colspan="3"><strong>Prepaid Amount(
                                    100.00%)</strong></td>
                            <td bgcolor="#FFFFFF" align="right" style="padding-right:5px;"><strong>
                                    $72.60          </strong></td>
                        </tr>
                    </table>
                </div>
                <br />
                <div id="screen41" class="wrapper" style="width:600px !important;" > 
                    <h2 align="left" style="padding-left:5px;">Customer Details</h2>
                    <hr color="#e1dada"  style="margin-top:3px;"/>
                    <br />
                    <h3 align="left" style="padding-left:5px;  color:#999;">Existing Customer?</h3>
                    <table cellpadding="4" cellspacing="0" align="left" style="text-align:left;">
                        <tr>
                            <td width="160px"><strong>Email Address:</strong></td>
                            <td><input type="text" name="email_addr_existing" id="email_addr_existing"   /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><button id="btn_back3" type="button" style="float:left;">Back</button></td>
                            <td><button id="btn_exisitng_cust" type="button" style="float:left;">Fetch Details</button></td>
                            <td><button id="btn_new_cust" type="button" style="float:left;">New Customer?</button></td>
                        </tr>
                        <tr>
                            <td id="exist_wait" ></td>
                        </tr>
                    </table>
                </div>
            </div>
            <!--=======================================Screen 5=====================================================-->
            <div id="content5" align="center">
                <div id="screen5" class="wrapper" style="width:600px !important;" > 
                    <h2 align="left" style="padding-left:5px;">Customer Details</h2>
                    <hr color="#e1dada"  style="margin-top:3px;"/>        

                    <h1 align="left">OR</h1>
                    <h3 align="left" style="padding-left:5px; color:#999;"> Customer</h3>

                    <table cellpadding="4" cellspacing="0" width="100%" border="0" style="text-align:left;">
                        <tr>
                            <td width="120px"><strong>Title:</strong></td>
                            <td id="title"><select name="title" class="textbox3" style="width:60px;">
                                    <option value="Mr.">Mr.</option>
                                    <option value="Ms.">Ms.</option>
                                    <option value="Mrs.">Mrs.</option>
                                    <option value="Miss.">Miss.</option>
                                    <option value="Dr.">Dr.</option>
                                    <option value="Prof.">Prof.</option>
                                </select></td>
                        </tr>
                        <tr>
                            <td><strong>First Name:</strong></td>
                            <td><input type="text" name="fname" id="fname"  class="required" /></td>
                        </tr>
                        <tr>
                            <td><strong>Last Name:</strong></td>
                            <td><input type="text" name="lname" id="lname"  class="required" /></td>
                        </tr>
                        <tr>
                            <td><strong>Address:</strong></td>
                            <td><input type="text" name="str_addr" id="str_addr"  class="required" /></td>
                        </tr>
                        <tr>
                            <td><strong>City:</strong></td>
                            <td><input type="text" name="city"  id="city" class="required" /></td>
                        </tr>
                        <tr>
                            <td><strong>State:</strong></td>
                            <td><input type="text" name="state"  id="state" class="required" /></td>
                        </tr>
                        <tr>
                            <td><strong>Postal Code:</strong></td>
                            <td><input type="text" name="zipcode"  id="zipcode" class="required" /></td>
                        </tr>
                        <tr>
                            <td><strong>Country:</strong></td>
                            <td><input type="text" name="country"  id="country" class="required" /></td>
                        </tr>
                        <tr>
                            <td><strong>Phone:</strong></td>
                            <td><input type="text" name="phone"  id="phone" class="required" /></td>
                        </tr>
                        <tr>
                            <td><strong>Fax:</strong></td>
                            <td><input type="text" name="fax"  id="fax" /></td>
                        </tr>
                        <tr>
                            <td><strong>Email:</strong></td>
                            <td><input type="text" name="email"  id="email" class="required email" /></td>
                        </tr>
                        <tr>
                            <td valign="top"><strong>Payment by:</strong></td>
                            <td><input type="radio" name="payment_type" id="payment_type_pp" value="pp" class="required" />PayPal<br /><input type="radio" name="payment_type" id="payment_type_poa" value="poa" class="required" /> Call : 1800 000 000 for Payment<br />            <label class="error" generated="true" for="payment_type" style="display:none;">This field is required.</label></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><input type="checkbox" name="tos" id="tos" value="" style="width:15px !important"  class="required"/>
                                &nbsp;
                                I agree with the <a href="javascript: ;" onclick="javascript:myPopup2();"> Terms & Conditions.</a></td>
                        </tr>
                        <tr>
                            <td height="50"></td>
                            <td><button id="btn_back4" type="button" style="float:left;">Back</button></td>
                            <td><button id="btn_book" type="button" style="float:left;">Confirm Booking</button></td>
                        </tr>
                    </table>
                </div>
            </div>
            <!--=======================================Screen 5=====================================================-->
            <div id="content6" align="center">
                <div id="screen6" class="wrapper" style="width:600px !important;" > 
                    <h2 align="left" style="padding-left:5px;">Booking Completed</h2>
                    <hr color="#e1dada"  style="margin-top:3px;"/><br /><br />
                    <h4>Thank You!</h4><br />Your Booking confirmed. Invoice sent in your email address.
                </div>
            </div>
            <!--=======================================footer=====================================================-->
            <div id="footerContent" align="center">
                <br/>
                <div id="footer" class="wrapper" style="width:600px !important;clear:both;text-align:center;" > 
                    Copyright © 2013 CAR4Rent,  Web Site Developed by Andrew Fernando
                </div>
            </div>
        </div>
    </body>
</html>
