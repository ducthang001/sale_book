package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="AUTHOR")
public class AuthorEntity {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id_author")
		private long id_author; 
		
		@Column(name="author_name",length=50)
		private String author_name;
		
		@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
		private Collection<BookEntity> books;

		public long getId_author() {
			return id_author;
		}

		public void setId_author(long id_author) {
			this.id_author = id_author;
		}

		public String getAuthor_name() {
			return author_name;
		}

		public void setAuthor_name(String author_name) {
			this.author_name = author_name;
		}

		public Collection<BookEntity> getBooks() {
			return books;
		}

		public void setBooks(Collection<BookEntity> books) {
			this.books = books;
		} 
}
