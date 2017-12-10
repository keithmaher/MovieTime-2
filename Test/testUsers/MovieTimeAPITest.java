package testUsers;

import static models.Fixtures.users;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.MovieTimeAPI;
import models.movie;
import models.users;

public class MovieTimeAPITest {

	private MovieTimeAPI movietime;

	@Before
	public void setup() {
		
		movietime = new MovieTimeAPI();
		for (users user : users) {
			movietime.addUser(user.firstName, user.lastName, user.age, 
					              user.gender, user.occupation);
		}
		
	}

	@After
	public void tearDown() {
		
		movietime = null;
		
	}

	@Test
	public void addUser() {
		
		assertEquals(users.length, movietime.getUsers().size());
		movietime.addUser("Keith", "Maher", "27", "M", "Student");
		assertEquals(users.length + 1, movietime.getUsers().size());
		assertEquals(users[0], movietime.getUserByName(users[0].firstName));
		
	}

	@Test
	public void testUsers() {
		
		assertEquals(users.length, movietime.getUsers().size());
		for (users user : users) {
			users eachUser = movietime.getUserByName(user.firstName);
			assertEquals(user, eachUser);
			assertNotSame(user, eachUser);
		}
	}

	public void testUserEmpty() {
		
		users keith = new users("Keith", "Maher", "27", "M", "Student");
		assertEquals(0, movietime.getUsers().size());
		movietime.addUser("Keith", "Maher", "27", "M", "Student");
		assertEquals(1, movietime.getUsers().size());
		
	}

	@Test
	public void testDeleteUsers() {
		
		assertEquals(users.length, movietime.getUsers().size());
		users keith = movietime.getUserByName("Keith");
		movietime.deleteUser(keith.id);
		assertEquals(users.length - 1, movietime.getUsers().size());
		
	}
	
	@Test
	public void testAddMovie() {
		
//		assertEquals(movie.length, movietime.getMovies().size());
//		movietime.addMovie(1L, "Saw", "2017", "Saw.com");
//		assertEquals(movie.length + 1, movietime.getMovies().size());
//		assertEquals(movie[0], movietime.getMovieByTitle(movie[0].title));
		
	}
	
}
