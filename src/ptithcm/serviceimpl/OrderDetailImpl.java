package ptithcm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.OrderDetailDao;
import ptithcm.entity.OrderDetailEntity;
import ptithcm.service.OrderDetailService;

@Service
public class OrderDetailImpl implements OrderDetailService {
	@Autowired
	OrderDetailDao orderDetailDao;

	@Override
	public int addOrderDetail(OrderDetailEntity orderdetail) {
		return orderDetailDao.addOrderDetail(orderdetail);
	}
	
	@Override
	public List<Object> getTopTheBestBook() {
		return orderDetailDao.getTopTheBestBook();
	}
	
}
