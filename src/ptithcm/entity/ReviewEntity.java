package ptithcm.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "review")
public class ReviewEntity {
	@EmbeddedId
	private ReviewId id; 
	
	@Column(name = "comments")
	private String comments;

	@Column(name = "star")
	private int star; 
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "time")
	private Date time; 
	
	
//	@ManyToOne
//    @JoinColumn(name = "user_id",insertable = false, updatable = false)
//	private AccountEntity users; 
	
	@ManyToOne
    @JoinColumn(name = "id_book", insertable = false, updatable = false)
	private BookEntity book;
	

	public ReviewEntity(ReviewId id, String comments, Date time, int star, AccountEntity users, BookEntity book) {
		super();
		this.id = id;
		this.comments = comments;
		this.time = time;
		this.star = star;
//		this.users = users;
		this.book = book;
	}
	
	public ReviewEntity() {
		super();
	}

	public ReviewId getId() {
		return id;
	}

	public void setId(ReviewId id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public BookEntity getBook() {
		return book;
	}

	public void setBook(BookEntity book) {
		this.book = book;
	}

	
}


