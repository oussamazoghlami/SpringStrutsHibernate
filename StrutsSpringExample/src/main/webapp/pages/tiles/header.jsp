<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<%@page import="com.tunisiana.tutorials.util.SpringSecurityUtil"%>
<%@page import="org.springframework.security.core.userdetails.UserDetails"%>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <td width="100%" height="34">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" height="13">
      <tr>
        <td width="8%" height="13" rowspan="2">
        	<img border="0" src="images/java.png" width="67" height="82"></td>
        <td width="54%" height="13" rowspan="2"></td>
        <td width="38%" height="32">
	        <table>
	        	<tr>
	        		<td>Logged user:</td>
	        		<td><%
			        		UserDetails authenticatedUser = SpringSecurityUtil.getAuthenticatedUser();
			        		if (authenticatedUser != null) {
			        			out.print(" "+authenticatedUser.getUsername());
			        		}
	        			%>
	        		</td>
	        	</tr>
			    <tr>
			      <td><a href="<spring:url value="/" htmlEscape="true" />">Home</a></td>
			      <td><a href="<spring:url value="/j_spring_security_logout" htmlEscape="true" />">Logout</a></td>
			    </tr>
			</table>
        </td>
      </tr>
    </table>
    </td>
  </tr>

</table>