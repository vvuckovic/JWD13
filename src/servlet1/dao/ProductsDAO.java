package servlet1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import servlet1.webshop.Product;
import util.ConnectionManager;

public class ProductsDAO {

	//preuzima sve proizvode iz baze, ubaci ih u listu i vrati listu
	public List<Product> getAll(){
		String query = "select * from Product";
		Statement stmt;
		List<Product> retVal = null;
		try {
			stmt = ConnectionManager.getConnection().createStatement();
			ResultSet rset = stmt.executeQuery(query);
			retVal = new ArrayList<Product>();
			while (rset.next()) {
				int id = rset.getInt(1);
				String name = rset.getString(2);
				double price = rset.getDouble(3);
				Product p = new Product(id, name, price);
				retVal.add(p);
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVal;
	}

	public boolean insertProduct(int id, String name, double price) {
		boolean retVal = false;
		try {
			Connection conn = ConnectionManager.getConnection();
			String insert = "INSERT INTO products (id, name, price) VALUES (?, ?, ?)";

			PreparedStatement pstmt = conn.prepareStatement(insert);

			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setDouble(3, price);

			if (pstmt.executeUpdate() == 1) {
				retVal = true;
			}

			pstmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return retVal;
	}

	public boolean deleteProduct(int id) {
		boolean retVal = false;
		try {
			Connection conn = ConnectionManager.getConnection();

			String selectSQL = "DELETE FROM products WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(selectSQL);
			pstmt.setInt(1, id);
			if (pstmt.executeUpdate() == 1)
				retVal = true;
			pstmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return retVal;
	}

	public Product getProductByID(int id1) {
		Product p = null;
		try {
			Connection conn = ConnectionManager.getConnection();
			String selectSQL = "SELECT id, name, price FROM products WHERE id = ?";
			PreparedStatement preparedStatement = conn
					.prepareStatement(selectSQL);

			preparedStatement.setInt(1, id1);
			ResultSet rset = preparedStatement.executeQuery();

			if (rset.next()) {
				int id = rset.getInt(1);
				String name = rset.getString(2);
				double price = rset.getDouble(3);
				p = new Product(id, name, price);

			}
			rset.close();
			preparedStatement.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return p;

	}

	public static void main(String[] args){
		ProductsDAO p = new ProductsDAO();
		List<Product> prod = p.getAll();
		System.out.println(prod);


	}

}
