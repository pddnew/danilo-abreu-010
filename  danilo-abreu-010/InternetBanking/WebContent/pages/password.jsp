<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="Stylesheet" href="../css/style-general.css" type="text/css"/>
<link rel="short icon" type="image/x-icon" href="../img/ico/favicon.ico"/>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${msg.title}</title>
</head>
<body>
<f:view locale="#{lang.userLocale}">

	<div id="content-wrapper">
		<div id="welcome-message">${msg.welcome}</div>		
		<h:form>
			<div id="lang">
				<h:commandLink action="#{lang.doViewPortuguese}" value="">${msg.portuguese}					
				</h:commandLink>
				&nbsp;&nbsp;
				<h:commandLink action="#{lang.doViewEnglish}">${msg.english}
				</h:commandLink>				
			</div>
		</h:form>
		
		<h:form>	
			<div id="input-account">
			<h:panelGrid columns="5">
				<h:outputFormat value="#{msg.welcomeClient}">
					<f:param value="#{AccountLoginBean.account.client.firstName}"></f:param>
				</h:outputFormat>
				${msg.password}&nbsp;
				<h:inputSecret id="password" value="#{AccountLoginBean.typedPassword}" 
					required="true" requiredMessage="#{msg.requiredField}"/> 
				<h:commandButton styleClass="buttons" value="#{msg.ok}" action="#{AccountLoginBean.doCheckPassword }" />
				<h:message for="password" style="color: red"/>			
			</h:panelGrid>
			</div>
		</h:form>
	</div>
	
</f:view>
</body>
</html>