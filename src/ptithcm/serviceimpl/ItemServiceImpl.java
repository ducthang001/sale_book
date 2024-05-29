package ptithcm.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.bean.CartDto;
import ptithcm.dao.ItemDao;
import ptithcm.entity.ItemEntity;
import ptithcm.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	ItemDao itemDao;
	
	@Override
	public List<ItemEntity> findItemByIDUser(int userID) {
		return itemDao.findItemByIDUser(userID);
	}
	
	@Override
	public int updateItem (ArrayList<CartDto> cart, int userID) {
		return itemDao.updateItem(cart, userID);
	}
	
	@Override
	public int addItem(ItemEntity item) {
		return itemDao.addItem(item);
	}
	
	@Override
	public int deleteItem(int idBook, int idUser) {
		return itemDao.deleteItem(idBook, idUser);
	}
	
	@Override
	public boolean checkIDBookOfUserItemExits (int userID, int idBook) {
		return itemDao.checkIDBookOfUserItemExits(userID, idBook);
	}
	
	@Override
	public int deleteAllItemsOfUser(int userID) {
		return itemDao.deleteAllItemsOfUser(userID);
	}
}
