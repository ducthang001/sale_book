package ptithcm.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "orders")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int order_id;
	
	@Column(name = "customer_name")
	private String customer_name;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "order_day")
	private Date order_day;
	
	@Column(name = "order_status")
	private int order_status;
	
	@Column(name = "customer_phone")
	private String customer_phone;
	
	@Column(name = "total_price")
	private long total_price;
	
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "user_id", updatable = false)
	private Integer user_id;
	
	@OneToMany(mappedBy = "orders", fetch = FetchType.EAGER)
	private Collection<OrderDetailEntity> detail;
	
//	@ManyToOne
//	@JoinColumn(name="user_id")
//	private AccountEntity user;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public Date getOrder_day() {
		return order_day;
	}

	public void setOrder_day(Date order_day) {
		this.order_day = order_day;
	}

	public int getOrder_status() {
		return order_status;
	}

	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public long getTotal_price() {
		return total_price;
	}

	public void setTotal_price(long total_price) {
		this.total_price = total_price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Collection<OrderDetailEntity> getDetail() {
		return detail;
	}

	public void setDetail(Collection<OrderDetailEntity> detail) {
		this.detail = detail;
	}

//	public AccountEntity getUser() {
//		return user;
//	}
//
//	public void setUser(AccountEntity user) {
//		this.user = user;
//	}

	
	
}
