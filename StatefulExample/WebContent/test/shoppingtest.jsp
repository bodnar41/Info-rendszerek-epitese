<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="org.ait.beans.ShoppingCartBeanLocal"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ShoppingCart test</title>
</head>
<body>
	<%
		InitialContext ctx = new InitialContext();
		ShoppingCartBeanLocal cart = (ShoppingCartBeanLocal) ctx
				.lookup("java:app/StatefulExample/ShoppingCartBean!org.ait.beans.ShoppingCartBeanLocal");
	%>
	Buying 1 memory stick
	<br>
	<%
		cart.buy("Memory stick", 1);
	%>
	Buying another memory stick
	<br>
	<%
		cart.buy("Memory stick", 1);
	%>
	Buying a laptop
	<br>
	<%
		cart.buy("Laptop", 1);
	%>
	<br> Print cart:
	<br>
	<%
		HashMap<String, Integer> fullCart = cart.getCartContent();
		for (String product : fullCart.keySet()) {
	%>
	<%=(String) (fullCart.get(product) + " " + product)%>
	<br>
	<%
		}
	%>
	<br> Checkout
	<br>
	<%
		cart.checkout();
	%>
	<br> Should throw an object not found exception by invoking on
	cart after @Remove method
	<br>
	<%
		try {
			cart.getCartContent();
		} catch (javax.ejb.NoSuchEJBException e) {
	%>
	<br>Successfully caught no such object exception.
	<br>
	<%
		}
	%>
</body>
</html>