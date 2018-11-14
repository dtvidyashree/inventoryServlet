package inventory_dto;

public class InventoryDTO {
	String itemname;
	Double costprice;
	Double sellingprice;
	int quantity;
	Double profit;
	Double diffProfit;

	public Double getDiffProfit() {
		return diffProfit;
	}

	public void setDiffProfit(Double diffProfit) {
		this.diffProfit = diffProfit;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	@Override
	public String toString() {
		return "InventoryDTO [itemname=" + itemname + ", costprice=" + costprice + ", sellingprice=" + sellingprice
				+ ", quantity=" + quantity + ", profit=" + profit + ", diffProfit=" + diffProfit + ", active=" + active
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((costprice == null) ? 0 : costprice.hashCode());
		result = prime * result + ((diffProfit == null) ? 0 : diffProfit.hashCode());
		result = prime * result + ((itemname == null) ? 0 : itemname.hashCode());
		result = prime * result + ((profit == null) ? 0 : profit.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((sellingprice == null) ? 0 : sellingprice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryDTO other = (InventoryDTO) obj;
		if (active != other.active)
			return false;
		if (costprice == null) {
			if (other.costprice != null)
				return false;
		} else if (!costprice.equals(other.costprice))
			return false;
		if (diffProfit == null) {
			if (other.diffProfit != null)
				return false;
		} else if (!diffProfit.equals(other.diffProfit))
			return false;
		if (itemname == null) {
			if (other.itemname != null)
				return false;
		} else if (!itemname.equals(other.itemname))
			return false;
		if (profit == null) {
			if (other.profit != null)
				return false;
		} else if (!profit.equals(other.profit))
			return false;
		if (quantity != other.quantity)
			return false;
		if (sellingprice == null) {
			if (other.sellingprice != null)
				return false;
		} else if (!sellingprice.equals(other.sellingprice))
			return false;
		return true;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public Double getCostprice() {
		return costprice;
	}

	public void setCostprice(Double costprice) {
		this.costprice = costprice;
	}

	public Double getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(Double sellingprice) {
		this.sellingprice = sellingprice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	boolean active;

}
