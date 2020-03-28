<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<%
			String sorok = request.getParameter("sorokSzama");
			if (sorok == null || "".equalsIgnoreCase(sorok.trim())) {
				out.print("Nem adott meg sort!!");
			} else {
				int sorokSzama = Integer.parseInt(sorok);
				for (int i = 0; i < sorokSzama; i++) {
		%>
		<tr>
			<td>Number</td>
			<td><%=i + 1%></td>
		</tr>
		<%
			}
			}
		%>
	</table>
</body>
</html>