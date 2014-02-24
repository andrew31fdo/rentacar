<%-- 
    Document   : vehicleCalendar
    Created on : Jan 30, 2014, 10:08:30 PM
    Author     : Ronal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>

            $(document).ready(function() {
                var holiDays = (function() {
                    var vId = $('#vId').val();
                    var val = null;
                    $.ajax({
                        'async': false,
                        'global': false,
                        'url': 'getAllocDateReservation.action?vId='+vId,
                        'success': function(data) {
                            val = data;
                        }
                    });
                    return val;
                })();
                var natDays = holiDays.split('');

                $('datepicker').datepicker({
                    beforeShowDay: function(date) {
                        if ($.inArray(date.toString(), dates) != -1) {
                            return [true, 'highlight'];
                        }
                    }
                });
            });
        </script>
    </head>
    <body>
        <h1>Hello World!</h1>
        <input type="text" id="datepicker" />
    </body>
</html>
