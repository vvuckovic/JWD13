
<%@page import="servlet1.webshop.User"%>
<%@page import="java.util.List"%>
<%@page import="servlet1.webshop.ShoppingCartItem"%>
<%@page import="servlet1.dao.ShoppingCartItemDAO"%>
<html>
<head>
</head>
<body>

	Proizvodi u korpi:
	<table>
		<tr bgcolor="lightgrey">
			<th>Naziv</th><th>Jedinicna cena</th><th>Komada</th><th>Ukupna cena</th><th>&nbsp;</th>
		</tr>
		<%List<ShoppingCartItem> sci = (List<ShoppingCartItem>) session.getAttribute("sci");
		double total = 0;
		for ( ShoppingCartItem i : sci ) {%>
		<tr>
			<td><%=i.getProduct().getName()%></td>
			<td><%=i.getProduct().getPrice()%></td>
			<td><%=i.getCount()%></td> 
			<%double price =
			i.getProduct().getPrice() * i.getCount();%>
			<td><%=price%></td>
			<td><form action="DeleteServlet" method="post">
					<input type="hidden" name="id" value="<%=i.getId()%>">
					<input type="submit" value="delete">
				</form></td>
		</tr>
		<%total += price; }%>
	</table>

	<p>Ukupno:<%=total%> dinara.</p>

	<p>
		<a href="WebShopServlet">Povratak</a>
	</p>

</body>
</html>