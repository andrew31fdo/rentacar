<%-- 
    Document   : ajax
    Created on : Jan 6, 2014, 10:01:13 AM
    Author     : Ronal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html> 
   <head> 
      <sj:head compressed='false'/> 
      <script type='text/javascript'> 
         $.subscribe('greetingComplete',function(event,data) { 
            alert("I am a a custom handler subscribed to the 'greetingComplete' topic"); 
         }); 
      </script> 
   </head> 
   <body> 
      <label>My Name:</label><s:textfield id="nameText" name="name"/> 
      <sj:a href="ajexLink.action" targets="greetingDiv1,greetingDiv2" indicatorId="greetIndicator" onCompleteTopics="greetingComplete" elementIds="nameText">Greet Me</sj:a> 
      <div class="indicator" id="greetIndicator" style="display:none"></div> 
      <sj:div cssClass="remoteDiv ui-widget-content" id="greetingDiv1"/> 
      <sj:div cssClass="remoteDiv ui-widget-content" id="greetingDiv2"/> 
   </body> 
</html>