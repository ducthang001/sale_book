package ptithcm.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.bean.CartDto;

@Service
public interface CartService {
	@Autowired
	public ArrayList<CartDto> addCart(int id, int quanty, ArrayList<CartDto> cart, int login);
	@Autowired
	public ArrayList<CartDto> editCart(int id, int quantity, ArrayList<CartDto> cart);
	@Autowired
	public ArrayList<CartDto> deleteCart(int id, ArrayList<CartDto> cart);
	@Autowired
	public long totalPrice(ArrayList<CartDto> cart);
}
