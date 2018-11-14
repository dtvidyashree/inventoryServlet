package inventory_services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inventory_dao.InventoryDAO;
import inventory_dao.InventoryDAOImpl;
import inventory_dto.InventoryDTO;

public class InventoryServiceImpl implements InventoryService {
	InventoryDAO dao = new InventoryDAOImpl();
	InventoryDTO dto = new InventoryDTO();
	List<InventoryDTO> dtolist = new ArrayList<InventoryDTO>();

	public List<InventoryDTO> create(String itemname, Double costprice, Double sellingprice) throws SQLException {
		dtolist = dao.insertintoinventory(itemname, costprice, sellingprice);
		if (dtolist == null) {
			return null;
		}
		return dtolist;
	}

	public List<InventoryDTO> updateBuy(String itemname, int quantity) throws SQLException {
		dtolist = dao.updateBuy(itemname, quantity);
//		if (dtolist == null) {
//			return null;
//		}
		return dtolist;
	}

	public List<InventoryDTO> updateSell(String itemname, int quantity) throws SQLException {
		dtolist = dao.updateSell(itemname, quantity);
//		if (dtolist == null) {
//			return null;
//		}
		return dtolist;
	}

	public boolean delete(String itemname) throws SQLException {
		dao.deleteFromInventory(itemname);
		return dto.isActive();
	}

	public List<InventoryDTO> getReport() {
		dtolist = dao.report();
		return dtolist;
	}

}
