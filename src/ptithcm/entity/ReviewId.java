package ptithcm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ReviewId implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id_book;
	private int user_id;
	public ReviewId() {
		
	}
	public ReviewId(int user_id, int id_book) {
		super();
		this.user_id = user_id;
		this.id_book = id_book;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getId_book_review() {
		return id_book;
	}
	public void setId_book_review(int id_book) {
		this.id_book = id_book;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_book;
		result = prime * result + user_id;
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
		ReviewId other = (ReviewId) obj;
		if (id_book != other.id_book)
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	} 
	
	
}
