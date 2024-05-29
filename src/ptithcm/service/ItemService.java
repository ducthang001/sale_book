package ptithcm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.bean.CartDto;
import ptithcm.entity.ItemEntity;

@Service
public interface ItemService {
	@Autowired
	public List <ItemEntity> findItemByIDUser (int userID);
	
	@Autowired
	public int updateItem (ArrayList<CartDto> cart, int userID);
	
	@Autowired
	public int addItem(ItemEntity item);
	
	@Autowired
	public int deleteItem(int idBook, int idUser);
	
	@Autowired
	public boolean checkIDBookOfUserItemExits (int userID, int idBook);
	
	@Autowired
	public int deleteAllItemsOfUser(int userID);
}
