package ptithcm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.ReviewDao;
import ptithcm.entity.ReviewEntity;
import ptithcm.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	ReviewDao reviewDao;
	
	@Override
	public List<ReviewEntity> dsReview() {
		return reviewDao.dsReview();
	}
	
	@Override
	public List<int[]> getListStarCount(int idbook){
		return reviewDao.getListStarCount(idbook);
	}
	
	@Override
	public List<float []> getAVGBookStar(int idbook){
		return reviewDao.getAVGBookStar(idbook);
	}
	
	@Override
	public boolean checkUserReview(int userID, int bookID) {
		return reviewDao.checkUserReview(userID, bookID);
	}
	
	@Override
	public ReviewEntity getReviewUserCommentABook (int userID, int bookID) {
		return reviewDao.getReviewUserCommentABook(userID, bookID);
	}
	
	@Override
	public int addReview(ReviewEntity review){
		return reviewDao.addReview(review);
	}
	
	@Override
	public int updateReview(String comments, int star, int userID, int bookID) {
		return reviewDao.updateReview(comments, star, userID, bookID);
	}

	@Override
	public int deleteReview(int idBook, int idUser) {
		// TODO Auto-generated method stub
		return reviewDao.deleteReview(idBook, idUser);
	}
}
