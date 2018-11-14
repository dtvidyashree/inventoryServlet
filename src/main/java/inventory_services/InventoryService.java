package inventory_services;

import java.sql.SQLException;
import java.util.List;

import inventory_dto.InventoryDTO;

public interface InventoryService {

	public List<InventoryDTO> create(String itemname, Double costprice, Double sellingprice) throws SQLException;

	public boolean delete(String itemname) throws SQLException;

	public List<InventoryDTO> updateBuy(String itemname, int quantity) throws SQLException;

	public List<InventoryDTO> updateSell(String itemname, int quantity) throws SQLException;

	public List<InventoryDTO> getReport();
}
