package ptithcm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface StatisticsService {
	@Autowired
	public long getProfitThisYear();
	
	@Autowired
	public long getOrdersCountThisYear();
	
	@Autowired
	public long getUsersCount();
	
	@Autowired
	public long[] getDetailProfitMonthOfYear(int year);
	
	@Autowired
	public long amountSpentbyIdUser (int idUser);
	
	@Autowired
	public long getPendingOrderCountbyIdUser (int idUser);
	
	@Autowired
	public long getOrderSuccessCountbyIdUser (int idUser);
	
}
