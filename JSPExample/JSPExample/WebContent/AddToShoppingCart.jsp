<%@ page errorPage="errorpage.jsp" %>

<jsp:useBean id="cart" scope="session" class="beans.ShoppingCart" />
<html>
  <head>
    <title>DVD Catalog</title>
  </head>
  <body>
  <%
    String id = request.getParameter("id");
    if ( id != null ) {
      String desc = request.getParameter("desc");
      Float price = new Float(request.getParameter("price"));     
      cart.addItem(id, desc, price.floatValue(), 1);
    }
  %>
  <a href="ShoppingCart.jsp">Shopping Cart Quantity:</a>
    <%=cart.getNumOfItems() %>
  <hr>
  <center><h3>DVD Catalog</h3></center>
  <table border="1" width="300" cellspacing="0"
  cellpadding="2" align="center">
    <tr><th>Description</th><th>Price</th></tr>
    <tr>
      <form action="AddToShoppingCart.jsp" method="post">
        <td>Book</td>
        <td>$19.95</td>
        <td><input type="submit" name="Submit" value="Add"></td>
        <input type="hidden" name="id" value="1">
        <input type="hidden" name="desc" value="Book">
        <input type="hidden" name="price" value="19.95">
      </form>
    </tr>
    <tr>
      <form action="AddToShoppingCart.jsp" method="post">
        <td>Toy</td>
        <td>$19.95</td>
        <td><input type="submit" name="Submit" value="Add"></td>
        <input type="hidden" name="id" value="2">
        <input type="hidden" name="desc" value="Toy">
        <input type="hidden" name="price" value="19.95">
      </form>
    </tr>
    </table>
  </body>
</html>
