
<%@page import="java.util.List"%>
<%@page import="servlet1.webshop.Products"%>
<%@page import="servlet1.webshop.Product"%>
<html>
<head>
</head>
<body>
	Raspolozivi proizvodi:

	<table border="1">
		<tr bgcolor="lightgrey"><th>Naziv</th><th>Cena</th><th>&nbsp</th></tr>
		<% List<Product> products = (List<Product>) session.getAttribute("products");

		for(Product p:products) { %>
		<tr>
			<form method= "get" action="ShoppingCartServlet">
				<td>  <%= p.getName() %> </td>
				<td> <%= p.getPrice() %> </td>
				<td><input type= "text" size="3" name="itemCount">
					<input type= "hidden" name= "itemId" value="<%= p.getId() %>">
					<input type= "submit" value="Dodaj">
			</form>
			</td>
		</tr>
		<% }%>
	</table>

	<p>
		<a href="ShoppingCartServlet">Pregled sadrzaja korpe</a>
	</p>

</body>
</html>