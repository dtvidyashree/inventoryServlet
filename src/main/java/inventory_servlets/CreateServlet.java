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

@WebServlet(name = "CreateServlet", urlPatterns = "/inventoryCreate")

public class CreateServlet extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet initilized");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String itemname = req.getParameter("item_name");
		Double costprice = Double.valueOf(req.getParameter("cost"));
		Double sellprice = Double.valueOf(req.getParameter("sell"));
		PrintWriter out = res.getWriter();

		InventoryService inventory = new InventoryServiceImpl();
		try {
			List<InventoryDTO> dtolist = inventory.create(itemname, costprice, sellprice);
			if (CollectionUtils.isNotEmpty(dtolist)) {
				out.write("Insert Successfull");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
