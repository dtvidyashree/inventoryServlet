package inventory_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;

import inventory_dto.InventoryDTO;
import inventory_services.InventoryService;
import inventory_services.InventoryServiceImpl;

@WebServlet(name = "UpdateSell", urlPatterns = "/updatesell")

public class UpdateSell extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet initilized");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String itemname = req.getParameter("item_name");
		Integer quantity = Integer.parseInt(req.getParameter("quantity"));

		PrintWriter out = res.getWriter();

		InventoryService inventory = new InventoryServiceImpl();
		try {
			List<InventoryDTO> dtolist = inventory.updateSell(itemname, quantity);
			if (CollectionUtils.isNotEmpty(dtolist)) {
				out.write("update Successfull");
			}
		}

		catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

}
