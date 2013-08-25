package app.support.home;

public class ElementReview {

	int stars;
	String nameUserReview;
	String userComment;
	int id;
	
	public ElementReview() {
		super();
	}

	public ElementReview(int stars, String nameUserReview, String userComment, int id) {
		super();
		this.stars = stars;
		this.nameUserReview = nameUserReview;
		this.userComment = userComment;
		this.id = id;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getNameUserReview() {
		return nameUserReview;
	}

	public void setNameUserReview(String nameUserReview) {
		this.nameUserReview = nameUserReview;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
