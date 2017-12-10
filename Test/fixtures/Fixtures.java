package fixtures;

import models.movie;
import models.ratings;
import models.users;

public class Fixtures {

	public static users [] users = { 
			new users("Keith", "Maher", "27", "M", "Student"),
			new users("Niamh", "Maher", "28", "F", "Student"),
			new users("Eoin", "Kelly", "32", "M", "Student") 
			};

	public static movie [] movies = { 
			new movie("Saw", "2017", "Saw.com"),
			new movie("Blade Runner", "2017", "Saw.com"), 
			new movie("Thor", "2017", "Thor.com") };

	public static ratings [] rating = { 
			new ratings(1L, 2L, 3), 
			new ratings(3L, 3L, 1) 
			};


}
