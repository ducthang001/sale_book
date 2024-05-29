package ptithcm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.entity.OrderEntity;

@Service
public interface OrderService {
	@Autowired
	public List <OrderEntity> dsOrder ();
	
	@Autowired
	public List <OrderEntity> orderPage (int pageNumber, int pagesize, String order, String dir, int status);
	
	@Autowired
	public long getOrderCount(int status);
	
	@Autowired
	public List<OrderEntity> searchOrder(Date dateA, Date dateB);
	
	@Autowired
	public boolean checkOldStatusExactly(int oldStatus, int idOrder);
	
	@Autowired
	public int saveOrder(int newStatus, int idOrder);
	
	@Autowired
	public List <OrderEntity> getOrderbyIdUser (int idUser);
	
	@Autowired
	public List<OrderEntity> searchOrderUser(Date dateA, Date dateB, int idUser);
	
	@Autowired
	public boolean checkOrderOfUserExist(int idOrder, int idUser);
	
	@Autowired
	public OrderEntity getOrderbyIDOrder (int idOrder);
	
	@Autowired
	public int addOrder(OrderEntity order);
	
	@Autowired
	public int getLastOrderID();
	
}
