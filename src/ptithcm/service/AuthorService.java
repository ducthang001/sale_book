package ptithcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.entity.AuthorEntity;

@Service
public interface AuthorService {
	@Autowired
	public String findNameAuhthor(int id_author);
	
	@Autowired
	public List <AuthorEntity> dsAuthor();
	
	@Autowired
	public long getAuthorCount();
	
	@Autowired
	public List <AuthorEntity> authorPage (int pageNumber, int pagesize, String order, String dir);
	
	@Autowired
	public List<AuthorEntity> SearchAuthor(String key);
	
	@Autowired
	public int searchAuthorCount(String key);
	
	@Autowired
	public int addAuthor(AuthorEntity author);
	
	@Autowired
	public int editAuthor(int idAuthor, String authorName);
	
	@Autowired
	public int deleteAuthor(int idAuthor);
	
	@Autowired
	public boolean checkNameAuthor(int idAuthor, String authorName);
	
}
