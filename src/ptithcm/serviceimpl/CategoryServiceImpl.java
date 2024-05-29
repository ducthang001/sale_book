package ptithcm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.CategoryDao;
import ptithcm.entity.CategoryEntity;
import ptithcm.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<CategoryEntity> dsCategory() {
		return categoryDao.dsCategory();
	}

	@Override
	public String findNameCategory(int id_category) {
		return categoryDao.findNameCategory(id_category);
	}
	
	@Override
	public long getCategoryCount() {
		return categoryDao.getCategoryCount();
	}

	@Override
	public List<CategoryEntity> categoryPage(int pageNumber, int pagesize, String order, String dir) {
		return categoryDao.categoryPage(pageNumber, pagesize, order, dir);
	}

	@Override
	public List<CategoryEntity> searchCategory(String key) {
		return categoryDao.searchCategory(key);
	}

	@Override
	public int searchCategoryCount(String key) {
		return categoryDao.searchCategoryCount(key);
	}

	@Override
	public int editCategory(int idCategory, String nameCategory) {
		return categoryDao.editCategory(idCategory, nameCategory);
	}

	@Override
	public int deleteCategory(int idCategory) {
		return categoryDao.deleteCategory(idCategory);
	}
	
	@Override
	public int getIDcategorybyName(String nameCategory) {
		return categoryDao.getIDcategorybyName(nameCategory);
	}
	
	@Override
	public boolean checkNameCategory(int idCategory, String authorName) {
		return categoryDao.checkNameCategory(idCategory, authorName);
	}
	
	@Override
	public int addCategory(CategoryEntity category) {
		return categoryDao.addCategory(category);
	}
	
	@Override
	public CategoryEntity getCategorybyName(String category_name) {
		return categoryDao.getCategorybyName(category_name);
	}

}
