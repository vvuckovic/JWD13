package servlet1;

import javax.servlet.http.*;
import java.io.*;
import java.util.List;

import servlet1.dao.ShoppingCartItemDAO;
import servlet1.webshop.*;

/**
 * Klasa koja obavlja listanje stavki u korpi, a ako je pozvana iz forme, dodaje
 * jednu stavku u korpu, pa onda lista).
 */
public class ShoppingCartServlet extends HttpServlet {

	private static final long serialVersionUID = -8628509500586512294L;

	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null){
			response.sendRedirect("login.html");
			return;
		}
		
		ShoppingCartItemDAO sciDAO = new ShoppingCartItemDAO();
		
		int userId = ((User) session.getAttribute("user")).getId();
		
		if ( request.getParameter("itemId") != null ) {
			try {
				sciDAO.insert(Integer.parseInt(request.getParameter("itemId")), userId,
							  Integer.parseInt(request.getParameter("itemCount")));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		List<ShoppingCartItem> sci = sciDAO.getShoppingCartItemsForUser(userId);
		session.setAttribute("sci", sci);
		
		response.sendRedirect("ShoppingCart.jsp");
		
	}
}
