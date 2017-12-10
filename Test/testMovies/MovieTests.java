package testMovies;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import models.movie;

	public class MovieTests
	{

	  movie test = new movie ("The Line",  "2015", "TheLine.com");
	  
	  //See if a movie can be created
	  @Test
	  public void testCreate()
	  {
	    assertEquals ("The Line",    test.title);
	    assertEquals ("2015",    test.videoYear);
	    assertEquals ("TheLine.com",   test.url);    
	  }

	  //test toString method
	  @Test
	  public void testToString()
	  {
	    assertEquals ("Movies{" + test.id + ", The Line, 2015, TheLine.com, []}", test.toString());
	  }
	}


