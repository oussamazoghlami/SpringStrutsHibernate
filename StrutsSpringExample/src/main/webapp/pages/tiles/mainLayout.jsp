<%@ page language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts-menu.sf.net/tag" prefix="menu" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<html>

<head>
	<title>
		<tiles:getAsString name="title" ignore="true" />
	</title>
</head>

<body>

	<table border="1" cellpadding="0" cellspacing="0" width="100%">
		<!-- Header -->
		<tr>
			<td width="100%" colspan="2" valign="top">
				<tiles:insert attribute="header"/>
			</td>
		</tr>
		
		<!--Menu and Body -->
		<tr>
			<td>
				<table border="1" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="30%" colspan="2" valign="top">
							<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
								<security:authorize access="hasRole('ADMIN')">
									<menu:displayMenu name="adminCustomer" />
								</security:authorize>
								
								<security:authorize access="hasRole('ADMIN')">
									<menu:displayMenu name="contact" />
								</security:authorize>
							</menu:useMenuDisplayer>
						</td>
						
						<td width="70%" colspan="2" valign="top">
							<tiles:insert attribute="body" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<!-- Footer -->
		<tr>
			<td width="100%" colspan="2" valign="top">
				<tiles:insert attribute="footer" />
			</td>
		</tr>
	</table>

</body>

</html>