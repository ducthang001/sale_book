package ptithcm.bean;

public class CartDto {
	private int quanty;
	private int total_price;
	private Product product;
	
	public CartDto() {
		
	}
	public CartDto(int quanty, int total_price, Product product) {
		this.quanty = quanty;
		this.total_price = total_price;
		this.product = product;
	}
	public int getQuanty() {
		return quanty;
	}
	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
