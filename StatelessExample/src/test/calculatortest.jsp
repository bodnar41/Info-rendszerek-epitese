<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="org.ait.beans.CalculatorBeanLocal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		InitialContext ctx;
		CalculatorBeanLocal calculator = null;
		try {
			ctx = new InitialContext();
			calculator = (CalculatorBeanLocal) ctx
					.lookup("java:app/StatelessExampleDinWeb/CalculatorBean!org.ait.beans.CalculatorBeanLocal");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	%>
	1 + 1 =
	<%=calculator.add(1, 1)%>
	<br> 1 - 1 =
	<%=calculator.sub(1, 1)%>
</body>
</html>