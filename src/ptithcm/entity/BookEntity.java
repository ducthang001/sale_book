package ptithcm.entity;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BOOK")
public class BookEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_book")
	private int id_book;
	
	@Column(name = "book_name")
	private String book_name;
	
	
	@Column(name = "describe_book")
	private String describe_book;
	
	@Column(name = "picture")
	private String picture;
	
	@Column(name = "price")
	private int price;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "publish_day")
	private Date publish_day;
	
	@Column(name = "total_quantity")
	private int total_quantity;
	
	@Column(name = "id_author", updatable = false, insertable = false)
	private int id_author;
	
	@Column(name = "category_id", updatable = false, insertable = false)
	private int category_id;
	
	@Column(name = "id_company", updatable = false, insertable = false)
	private int id_company;
	
	@ManyToOne
	@JoinColumn(name="id_author", nullable = true)
	private AuthorEntity author; 
	
	@ManyToOne
	@JoinColumn(name="category_id", nullable = true)
	private CategoryEntity category;
	
	@ManyToOne
	@JoinColumn(name="id_company", nullable = true)
	private CompanyEntity company;
	
	
	@OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
	private Collection<ReviewEntity> reviews;
	

	public int getId_book() {
		return id_book;
	}

	public void setId_book(int id_book) {
		this.id_book = id_book;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getDescribe_book() {
		return describe_book;
	}

	public void setDescribe_book(String describe_book) {
		this.describe_book = describe_book;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getPublish_day() {
		return publish_day;
	}

	public void setPublish_day(Date publish_day) {
		this.publish_day = publish_day;
	}

	public int getTotal_quantity() {
		return total_quantity;
	}

	public void setTotal_quantity(int total_quantity) {
		this.total_quantity = total_quantity;
	}

	public AuthorEntity getAuthor() {
		return author;
	}

	public void setAuthor(AuthorEntity author) {
		this.author = author;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

	public Collection<ReviewEntity> getReviews() {
		return reviews;
	}

	public void setReviews(Collection<ReviewEntity> reviews) {
		this.reviews = reviews;
	}

	public int getId_author() {
		return id_author;
	}

	public void setId_author(int id_author) {
		this.id_author = id_author;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public int getId_company() {
		return id_company;
	}

	public void setId_company(int id_company) {
		this.id_company = id_company;
	}
	
	
}
