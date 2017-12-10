package models;

import com.google.common.base.Objects;

import utils.ToJsonString;

public class ratings{

	static Long counter = 0l;
	public Long id;
	public long userId;
	public long movieId;
	public int rating;

	public ratings(Long userid, Long movieid, int rating) {
		this.id = counter++;
		this.userId = userid;
		this.movieId = movieid;
		this.rating = rating;
	}

	
	public String toString() {
			 return new ToJsonString(getClass(), this).toString();
	}


	public int hashCode() {
		return Objects.hashCode(this.id, this.userId, this.movieId, this.rating);
	}
	

	  public boolean equals(final Object obj) {
	    if (obj instanceof ratings){
	      final ratings other = (ratings) obj;
	      return Objects.equal(userId, other.userId) 
	          && Objects.equal(movieId, other.movieId)
	          && Objects.equal(rating, other.rating);
	    }else{
	      return false;
	    }
	  }
}
