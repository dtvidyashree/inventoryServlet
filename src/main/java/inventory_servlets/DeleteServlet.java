package inventory_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inventory_services.InventoryService;
import inventory_services.InventoryServiceImpl;

@WebServlet(name = "DeleteServlet", urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet initilized");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String itemname = req.getParameter("item_name");
		PrintWriter out = res.getWriter();

		InventoryService inventory = new InventoryServiceImpl();
		try {
			boolean value = inventory.delete(itemname);
			if (value) {
				out.write("item not found");
			} else {
				out.write("Delete Successfull");
			}
		}

		catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
}
