package inventory_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inventory_dto.InventoryDTO;
import inventory_services.InventoryService;
import inventory_services.InventoryServiceImpl;

@WebServlet(name = "ReportServlet", urlPatterns = "/report")
public class ReportServlet extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet initilized");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemName = request.getParameter("itemName");
		PrintWriter out = response.getWriter();

		try {
			InventoryService inventory = new InventoryServiceImpl();
			List<InventoryDTO> dtolist = inventory.getReport();
			int counter = 0;
			// out.write("Inventory Management Report ");
			out.write("<html><body>");
			out.write("\t INVENTORY REPORT \t\n");
			out.write("Item Name    " + "    " + "Bought At   " + "    " + "Sold At  " + "   " + "AvailableQty  " + "  "
					+ "Value  ");
			out.write("<br>------------" + "    " + "-------------" + "    " + "------------" + "   "
					+ "---------------" + "  " + "---------");

			for (InventoryDTO dto : dtolist) {

				out.write("<br>" + dto.getItemname() + "              " + dto.getCostprice() + "            "
						+ dto.getSellingprice() + "            " + dto.getQuantity() + "             "
						+ ((dto.getCostprice()) * (dto.getQuantity())));

				out.write("<br> profit for this item is " + dto.getProfit());

			}
		} catch (Exception e) {
			response.sendRedirect("inventory.html");
		}
	}
}
