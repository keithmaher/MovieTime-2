package testUsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import models.users;
import static models.Fixtures.users;

public class UserTest {

	users keith = new users ("Keith", "Maher", "27", "M", "Student", "ssdd234");
	
	
	
	@Test
	public void testCreate() {
		assertEquals("Keith", keith.firstName);
		assertEquals("Maher", keith.lastName);
		assertEquals("27", keith.age);
		assertEquals("M", keith.gender);
		assertEquals("Student", keith.occupation);
	}
	
	@Test
	public void testEquals() {
		users keith = new users("Keith", "Maher", "27", "M", "Student", "ssdd234");
		users niamh = new users("Niamh", "Maher", "28", "F", "Student", "ssdd234");
		assertEquals(keith, keith);
		assertEquals(niamh, niamh);
		assertNotEquals(keith, niamh);
	}
	
	@Test
	  public void testIds()
	  {
	    Set<Long> ids = new HashSet<>();
	    for (users user : users)
	    {
	      ids.add(user.id);
	    }
	    assertEquals (users.length, ids.size());
	  }
	
	@Test
	public void testToString() {
		assertEquals("Users{" + keith.id + ", Keith, Maher, 27, M, Student, ssdd234}", keith.toString());
	}
	
}
