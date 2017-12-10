package models;

import java.util.HashMap;
import com.google.common.base.Objects;
import utils.ToJsonString;

public class movie implements Comparable<movie>{
	
	 public static Long counter = (long) 01;
	 public Long id;
	 public String title;
	 public String videoYear;
	 public String url;
	 
	 public HashMap<Long, ratings> theratings  = new HashMap<>();
	 
	 public movie(String title, String year, String url)
	 {
	 this.id = counter++;;
	 this.title = title;
	 this.videoYear = year;
	 this.url = url;
	 }
	 
	 @Override
	  public String toString()
	  {
		 return new ToJsonString(getClass(), this).toString();
	  }
	 
	 public int hashCode()
		{
			return Objects.hashCode(this.id, this.title, this.videoYear, this.url);
		}
		
		@Override
		public boolean equals(final Object obj)
		{
			if(obj instanceof movie)
			{
				final movie other=(movie) obj;
				return Objects.equal(id, other.id)
					&& Objects.equal(title, other.title)
					&& Objects.equal(videoYear, other.videoYear)
					&& Objects.equal(url, other.url);
			}
			else
			{
				return false;
			}
		}


	
	@Override
	public int compareTo(movie movie) {
		
		return title.compareTo(movie.title);
	}

}
