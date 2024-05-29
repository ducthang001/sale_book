package ptithcm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.AuthorDao;
import ptithcm.entity.AuthorEntity;
import ptithcm.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{
	@Autowired
	AuthorDao authorDao;
	
	@Override
	public String findNameAuhthor(int id_author) {
		return authorDao.findNameAuhthor(id_author);
	}

	@Override
	public List<AuthorEntity> dsAuthor() {
		return authorDao.dsAuthor();
	}
	
	@Override
	public long getAuthorCount() {
		return authorDao.getAuthorCount();
	}
	
	@Override
	public List <AuthorEntity> authorPage (int pageNumber, int pagesize, String order, String dir){
		return authorDao.authorPage(pageNumber, pagesize, order, dir);
	}
	
	@Override
	public List<AuthorEntity> SearchAuthor(String key){
		return authorDao.SearchAuthor(key);
	}
	
	@Override
	public int searchAuthorCount(String key) {
		return authorDao.searchAuthorCount(key);
	}
	
	@Override
	public int addAuthor(AuthorEntity author) {
		return authorDao.addAuthor(author);
	}
	
	@Override
	public int editAuthor(int idAuthor, String authorName) {
		return authorDao.editAuthor(idAuthor, authorName);
	}
	
	@Override
	public int deleteAuthor(int idAuthor) {
		return authorDao.deleteAuthor(idAuthor);
	}
	
	@Override
	public boolean checkNameAuthor(int idAuthor, String authorName) {
		return authorDao.checkNameAuthor(idAuthor, authorName);
	}
}

