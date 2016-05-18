package baseClasses;

public class Rating {
	
	private String comment = "";
	private Integer rating = 0;
	private User author = new User();
	
	public Rating(){}
	
	public Rating(String comment, Integer rating, User author){
		this.comment = comment;
		this.rating = rating;
		this.author = author;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}

}
