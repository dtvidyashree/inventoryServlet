package inventory_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import inventory_dto.InventoryDTO;

public class InventoryDAOImpl implements InventoryDAO {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/inventory";

	static final String USER = "root";
	static final String PASSWORD = "root";

	static Double previous_profit = 0.0;

	List<InventoryDTO> dtolist = new ArrayList<InventoryDTO>();
	InventoryDTO dto = new InventoryDTO();

	public List<InventoryDTO> insertintoinventory(String itemname, Double costprice, Double sellingprice)
			throws SQLException {

		Connection conn = null;
		Statement stmt = null;
		int max_inv_id = 1;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();

			String get_itemname = "Select item_name from inventory.inventory_items where item_name = '" + itemname
					+ "'";
			ResultSet i_names = stmt.executeQuery(get_itemname);
			if (i_names.isBeforeFirst()) {
				dtolist = null;
			}

			if (!i_names.isBeforeFirst()) {
				// get the max id and add one to provide new id for new item
				String max_id_query = "SELECT max(inventory_id) FROM inventory.inventory_items";
				ResultSet max_id = stmt.executeQuery(max_id_query);
				while (max_id.next()) {
					max_inv_id = max_id.getInt(1) + 1;
				}
				String insert_query = "INSERT INTO inventory.inventory_items (inventory_id, item_name, item_sell_price, item_buy_price) values ('"
						+ max_inv_id + "','" + itemname + "'," + sellingprice + "," + costprice + ")";
				stmt.executeUpdate(insert_query);
				String select_query = "select * from inventory_items";
				ResultSet rs = stmt.executeQuery(select_query);
				while (rs.next()) {
					System.out.println(rs.getString("item_buy_price") + " " + rs.getString("item_name") + " "
							+ rs.getString("item_sell_price"));
				}
				dto.setActive(true);
				dtolist.add(dto);
				max_id.close();
			}

			// Clean-up environment
			stmt.close();
			conn.close();

		} catch (

		ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dtolist;
	}

	public List<InventoryDTO> updateBuy(String itemName, int quantity) {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String update_query = "UPDATE inventory_items SET item_quantity = " + quantity + "  WHERE (item_name = '"
					+ itemName + "')";
			int rs = stmt.executeUpdate(update_query);
			dto.setActive(true);
			dtolist.add(dto);

			// Clean-up environment
			stmt.close();
			conn.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return dtolist;
	}

	public List<InventoryDTO> updateSell(String itemName, int quantity) {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String getItemQuantityQuery = "Select item_quantity from inventory_items where item_name = '" + itemName
					+ "'";
			ResultSet result_quantity = stmt.executeQuery(getItemQuantityQuery);
			int avaliable_Quantity = 0;
			while (result_quantity.next()) {
				avaliable_Quantity = result_quantity.getInt("item_quantity");
			}
			int updatedQuantity = avaliable_Quantity - quantity;

			String getSoldQuantityQuery = "Select sold_quantity from inventory_items where item_name = '" + itemName
					+ "'";
			ResultSet result_sold_quantity = stmt.executeQuery(getSoldQuantityQuery);
			int sold_Quantity = 0;
			while (result_sold_quantity.next()) {
				sold_Quantity = result_sold_quantity.getInt("sold_quantity");
			}
			int updatedSoldQuantity = sold_Quantity + quantity;

			String updateSellQuery = "UPDATE inventory.inventory_items SET item_quantity = " + updatedQuantity
					+ ", sold_quantity = " + updatedSoldQuantity + " WHERE (item_name = '" + itemName + "')";
			int rs = stmt.executeUpdate(updateSellQuery);
			dto.setActive(true);
			dtolist.add(dto);

			// Clean-up environment
			stmt.close();
			conn.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return dtolist;
	}

	// To Delete the item from the inventory
	// @args itemName
	public void deleteFromInventory(String itemname) throws SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();

			String get_itemname = "Select item_name from inventory.inventory_items where item_name = '" + itemname
					+ "'";
			ResultSet i_names = stmt.executeQuery(get_itemname);
//			if (!i_names.isBeforeFirst()) {
//				dto.setActive(false);
//			}
			if (i_names.isBeforeFirst()) {
				String delete_query = "UPDATE inventory.inventory_items SET active = 0 WHERE (item_name = '" + itemname
						+ "')";
				int rs = stmt.executeUpdate(delete_query);
				dto.setActive(true);
				// Clean-up environment
				stmt.close();
				conn.close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<InventoryDTO> report() {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stmt = conn.createStatement();

			String q = "SELECT * from inventory_items where active=1";
			ResultSet rs = stmt.executeQuery(q);
			while (rs.next()) {

				Double profit = 0.00;
				String itemName = rs.getString("item_name");
				Double buy_price = rs.getDouble("item_buy_price");
				Double sell_price = rs.getDouble("item_sell_price");
				Integer quantity = rs.getInt("item_quantity");
				Double spent_amt = rs.getDouble("item_buy_price") * rs.getInt("item_quantity");
				profit = profit + ((rs.getDouble("item_sell_price") - rs.getDouble("item_buy_price"))
						* rs.getInt("sold_quantity") * sell_price);
				Double diffProfit = (profit - InventoryDAOImpl.previous_profit);
				InventoryDAOImpl.previous_profit = profit - InventoryDAOImpl.previous_profit;

				dto.setItemname(itemName);
				dto.setCostprice(buy_price);
				dto.setSellingprice(sell_price);
				dto.setQuantity(quantity);
				dto.setProfit(profit);
				dto.setActive(true);

				dtolist.add(dto);
			}
			// Clean-up environment
			stmt.close();
			conn.close();
		} catch (

		Exception exc) {
			exc.printStackTrace();
		}
		return dtolist;
	}

}
