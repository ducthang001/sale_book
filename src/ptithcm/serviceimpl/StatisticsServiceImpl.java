package ptithcm.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.StatisticsDao;
import ptithcm.service.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService {
	@Autowired
	StatisticsDao statisticsDao;
	
	@Override
	public long getProfitThisYear() {
		return statisticsDao.getProfitThisYear();
	}

	@Override
	public long getOrdersCountThisYear() {
		return statisticsDao.getOrdersCountThisYear();
	}

	@Override
	public long getUsersCount() {
		return statisticsDao.getUsersCount();
	}
	
	@Override
	public long[] getDetailProfitMonthOfYear(int year) {
		return statisticsDao.getDetailProfitMonthOfYear(year);
	}

	@Override
	public long amountSpentbyIdUser(int idUser) {
		return statisticsDao.amountSpentbyIdUser(idUser);
	}

	@Override
	public long getPendingOrderCountbyIdUser(int idUser) {
		return statisticsDao.getPendingOrderCountbyIdUser(idUser);
	}

	@Override
	public long getOrderSuccessCountbyIdUser(int idUser) {
		return statisticsDao.getOrderSuccessCountbyIdUser(idUser);
	}
}
