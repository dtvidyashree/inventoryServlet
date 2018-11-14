package inventory_dao;

import java.sql.SQLException;
import java.util.List;

import inventory_dto.InventoryDTO;

public interface InventoryDAO {

	public List<InventoryDTO> insertintoinventory(String itemname, Double costprice, Double sellingprice)
			throws SQLException;

	public void deleteFromInventory(String itemName) throws SQLException;

	public List<InventoryDTO> updateBuy(String itemname, int quantity);

	public List<InventoryDTO> updateSell(String itemname, int quantity);

	public List<InventoryDTO> report();

}
