package ptithcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.entity.CategoryEntity;

@Service
public interface CategoryService {
	@Autowired
	public List <CategoryEntity> dsCategory ();
	@Autowired
	public String findNameCategory(int id_category);
	@Autowired
	public long getCategoryCount();
	@Autowired
	public List <CategoryEntity> categoryPage (int pageNumber, int pagesize, String order, String dir);
	@Autowired
	public List<CategoryEntity> searchCategory(String key);
	@Autowired
	public int searchCategoryCount(String key);
	@Autowired
	public int editCategory(int idCategory, String nameCategory);
	@Autowired
	public int deleteCategory(int idCategory);
	@Autowired
	public boolean checkNameCategory(int idCategory, String authorName);
	@Autowired
	public int addCategory(CategoryEntity category);
	@Autowired
	public int getIDcategorybyName(String nameCategory);
	@Autowired
	public CategoryEntity getCategorybyName(String category_name);
}
