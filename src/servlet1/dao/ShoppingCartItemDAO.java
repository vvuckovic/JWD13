package servlet1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import servlet1.webshop.Product;
import servlet1.webshop.ShoppingCartItem;
import util.ConnectionManager;

public class ShoppingCartItemDAO {
	
	public boolean insert(int productId, int userId, int count){
		boolean retVal = false;
		try {
			String update = "INSERT INTO ShoppingCartItem (Product_id, User_id, count) values (?,?, ?)";
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(update);
			pstmt.setInt(1, productId);
			pstmt.setInt(2, userId);
			pstmt.setInt(3, count);
			if (pstmt.executeUpdate() == 1) {
				retVal = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	public boolean deleteById (int id){
		boolean retVal = false;
		try {
			String update = "DELETE from ShoppingCartItem where id = ?";
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(update);
			pstmt.setInt(1, id);
			
			if (pstmt.executeUpdate() == 1) {
				retVal = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	public List<ShoppingCartItem> getShoppingCartItemsForUser(int userId){
		List<ShoppingCartItem> retVal = new ArrayList<ShoppingCartItem>();
		try {
			String query = "SELECT p.*, sci.count, sci.id  FROM jwd13_14.ShoppingCartItem sci "+
							"left join jwd13_14.Product p on sci.Product_id = p.id " +
							"where sci.User_id = " + userId;
			Connection conn = ConnectionManager.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			
			while (rset.next()) {
				int id = rset.getInt(1);
				String name = rset.getString(2);
				double price = rset.getDouble(3);
				Product p = new Product(id, name, price);
				int count = rset.getInt(4);
				int sciId= rset.getInt(5);
				ShoppingCartItem sci = new ShoppingCartItem(p, count, sciId);
				retVal.add(sci);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
}
