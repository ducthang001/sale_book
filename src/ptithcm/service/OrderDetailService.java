package ptithcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.entity.OrderDetailEntity;

@Service
public interface OrderDetailService {
	@Autowired
	public int addOrderDetail(OrderDetailEntity orderdetail);
	
	@Autowired
	public List<Object> getTopTheBestBook();
}
