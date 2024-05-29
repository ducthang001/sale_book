package ptithcm.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.OrderDao;
import ptithcm.entity.OrderEntity;
import ptithcm.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDao orderDao;
	
	@Override
	public List<OrderEntity> dsOrder() {
		return orderDao.dsOrder();
	}

	@Override
	public List <OrderEntity> orderPage (int pageNumber, int pagesize, String order, String dir, int status){
		return orderDao.orderPage(pageNumber, pagesize, order, dir, status);
	}

	@Override
	public long getOrderCount(int status) {
		return orderDao.getOrderCount(status);
	}
	
	@Override
	public List<OrderEntity> searchOrder(Date dateA, Date dateB) {
		return orderDao.searchOrder(dateA, dateB);
	}
	
	@Override
	public boolean checkOldStatusExactly(int oldStatus, int idOrder) {
		return orderDao.checkOldStatusExactly(oldStatus, idOrder);
	}
	
	@Override
	public int saveOrder(int newStatus, int idOrder) {
		return orderDao.saveOrder(newStatus, idOrder);
	}
	
	@Override
	public List <OrderEntity> getOrderbyIdUser (int idUser){
		return orderDao.getOrderbyIdUser(idUser);
	}
	
	@Override
	public List<OrderEntity> searchOrderUser(Date dateA, Date dateB, int idUser){
		return orderDao.searchOrderUser(dateA, dateB, idUser);
	}
	
	@Override
	public boolean checkOrderOfUserExist(int idOrder, int idUser) {
		return orderDao.checkOrderOfUserExist(idOrder, idUser);
	}
	
	@Override
	public OrderEntity getOrderbyIDOrder (int idOrder) {
		return orderDao.getOrderbyIDOrder(idOrder);
	}

	@Override
	public int addOrder(OrderEntity order) {
		return orderDao.addOrder(order);
	}
	
	@Override
	public int getLastOrderID() {
		return orderDao.getLastOrderID();
	}
	
}
