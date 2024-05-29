package ptithcm.serviceimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.bean.CartDto;
import ptithcm.dao.CartDao;
import ptithcm.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	CartDao cartDao;
	
	@Override
	public ArrayList<CartDto> addCart(int id, int quanty, ArrayList<CartDto> cart, int login) {
		return cartDao.addCart(id, quanty, cart, login);
	}

	@Override
	public ArrayList<CartDto> editCart(int id, int quantity, ArrayList<CartDto> cart) {
		return cartDao.editCart(id, quantity, cart);
	}

	@Override
	public ArrayList<CartDto> deleteCart(int id, ArrayList<CartDto> cart) {
		return cartDao.deleteCart(id, cart);
	}
	
	@Override
	public long totalPrice(ArrayList<CartDto> cart) {
		return cartDao.totalPrice(cart);
	}
}
