package ptithcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.entity.ReviewEntity;

@Service
public interface ReviewService {
	@Autowired
	public List <ReviewEntity> dsReview ();
	@Autowired
	public List<int[]> getListStarCount(int idbook);
	@Autowired
	public List<float []> getAVGBookStar(int idbook);
	@Autowired
	public boolean checkUserReview(int userID, int bookID);
	@Autowired
	public ReviewEntity getReviewUserCommentABook (int userID, int bookID);
	@Autowired
	public int addReview(ReviewEntity review);
	@Autowired
	public int updateReview(String comments, int star, int userID, int bookID);
	
	@Autowired
	public int deleteReview(int idBook, int idUser);
}
