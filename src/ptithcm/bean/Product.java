package ptithcm.bean;

public class Product {
	private int id;
	private String bookname;
	private int price;
	private String image;
	
	public Product() {
		
	}

	public Product(int id, String bookname, int price, String image) {
		this.id = id;
		this.bookname = bookname;
		this.price = price;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
}