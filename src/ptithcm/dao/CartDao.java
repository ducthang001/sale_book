package ptithcm.dao;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ptithcm.bean.CartDto;
import ptithcm.bean.Product;
import ptithcm.controller.AccountController;
import ptithcm.entity.ItemEntity;
import ptithcm.serviceimpl.HomeServiceImpl;
import ptithcm.serviceimpl.ItemServiceImpl;

@Controller
@Transactional
public class CartDao {
	@Autowired
	HomeServiceImpl homeServiceImpl;
	
	@Autowired
	ItemServiceImpl itemServiceImpl;
	
	
	public ArrayList<CartDto> addCart(int id, int quanty, ArrayList<CartDto> cart, int login) { // 1: login, 0: chưa login
		Product product = homeServiceImpl.productFindBookbyID(id);
		if(product == null) {
			return cart;
		}else {
			boolean check = false;
			for(int i = 0; i<cart.size(); i++) {
				if(cart.get(i).getProduct().getId() == id) {
					cart.get(i).setQuanty(cart.get(i).getQuanty() + quanty);
					cart.get(i).setTotal_price(cart.get(i).getQuanty() * cart.get(i).getProduct().getPrice());
					check = true;
					break;
				}
			}
			if(check == false) {
				int total_price = product.getPrice() * quanty;
				CartDto item = new CartDto(quanty, total_price, product);
				cart.add(item);	
			}
			
			if(check == true && AccountController.getUser() != null && login == 0) { //Đã có sách ni
				int result = itemServiceImpl.updateItem(cart, AccountController.getUser().getUser_id());
				if(result < 1) {
					System.out.println("UPDATE ITEM THAT BAI");
				}else {
					System.out.println("UPDATE ITEM THANH CONG");
				}
			}
			
			if(check == false && AccountController.getUser() != null && login == 0) { // Chưa có sách ni
				ItemEntity item = new ItemEntity();
				item.setId_book(id);
				item.setQuantity_books(quanty);
				item.setUser_id(AccountController.getUser().getUser_id());
				int result = itemServiceImpl.addItem(item);
				if(result < 1) {
					System.out.println("THEM ITEM THAT BAI");
				}else {
					System.out.println("THEM ITEM THANH CONG");
				}
			}
			
			return cart;
		}
	}
	
	
	public ArrayList<CartDto> editCart(int id, int quanty, ArrayList<CartDto> cart) {
		Product product = homeServiceImpl.productFindBookbyID(id);
		if(product == null || cart == null) {
			return cart;
		}else {
			CartDto item = new CartDto();
			for(int i = 0; i<cart.size(); i++) {
				if(cart.get(i).getProduct().getId() == id) {
					item = cart.get(i);
					item.setQuanty(quanty);
					item.setTotal_price(quanty * item.getProduct().getPrice());
					break;
				}
			}
			
			if(AccountController.getUser() != null) { //Đã có sách ni => UPDATE lại
				int result = itemServiceImpl.updateItem(cart, AccountController.getUser().getUser_id());
				if(result < 1) {
					System.out.println("UPDATE ITEM THAT BAI");
				}else {
					System.out.println("UPDATE ITEM THANH CONG");
				}
			}
			
			return cart;
		}
	}
	
	public ArrayList<CartDto> deleteCart(int id, ArrayList<CartDto> cart) {
		Product product = homeServiceImpl.productFindBookbyID(id);
		if(product == null || cart == null) {
			return cart;
		}else {
			for(int i = 0; i<cart.size(); i++) {
				if(cart.get(i).getProduct().getId() == id) {
					cart.remove(i);
					if(AccountController.getUser() != null) {
						int result = itemServiceImpl.deleteItem(id, AccountController.getUser().getUser_id());
						if(result < 1) {
							System.out.println("XOA ITEM THAT BAI");
						}else {
							System.out.println("XOA ITEM THANH CONG");
						}
					}
					break;
				}
			}
			return cart;
		}
	}
	
	public int totalQuanty(ArrayList<CartDto> cart){
		return cart.size();
	}
	
	public long totalPrice(ArrayList<CartDto> cart){
		long price = 0;
		for(int i = 0; i<cart.size(); i++) {
			price += cart.get(i).getTotal_price();
		}
		System.out.println("SUM MONEY = "+price);
		return price;
	}
	
}
