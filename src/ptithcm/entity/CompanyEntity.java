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
@Table(name = "company")
public class CompanyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_company")
	private int id_company;
	
	@Column(name = "company_name")
	private String company_name;
	
	@OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
	private Collection<BookEntity> books;

	public int getId_company() {
		return id_company;
	}

	public void setId_company(int id_company) {
		this.id_company = id_company;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public Collection<BookEntity> getBooks() {
		return books;
	}

	public void setBooks(Collection<BookEntity> books) {
		this.books = books;
	}
	
}
