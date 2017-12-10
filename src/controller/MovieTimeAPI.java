package controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.common.base.Optional;


import models.movie;
import models.ratings;
import models.users;
import utils.Serializer;

public class MovieTimeAPI {
	
	 private Serializer serializer;

	  public MovieTimeAPI()
	  {
	  }

	  public MovieTimeAPI(Serializer serializer)
	  {
	    this.serializer = serializer;
	  }

	  @SuppressWarnings("unchecked")
	  public void load() throws Exception
	  {
	    serializer.read();
	    userIndex 	=  (Map<Long, users>)   serializer.pop();
		movieIndex  =  (Map<Long, movie>)   serializer.pop();
		ratingIndex =  (Map<Long, ratings>) serializer.pop();
	  }

	  void store() throws Exception
	  {
		serializer.push(ratingIndex);
		serializer.push(movieIndex);
		serializer.push(userIndex);	
		serializer.write();
	  }
	
	////////////////////////////////////////////////////////
	/////////////// USER DETAILS  //////////////////////////
	////////////////////////////////////////////////////////
	 
	  private  Map<Long, users>  userIndex = new HashMap<Long, users>();
	  private  Map<String, users> nameIndex = new HashMap<>();
	  Optional<users> currentUser;
	  
	  //add a user
	  public users addUser(String firstName, String lastName, String age, String gender, String occupation) 
	  {
	    users user = new users (firstName, lastName, age, gender, occupation);
	    userIndex.put(user.id, user);
	    nameIndex.put(firstName, user);
	    return user;
	  }
	  
	  public users addUser(String firstName, String lastName, String age, String gender, String occupation, String role) 
	  {
	    users user = new users (firstName, lastName, age, gender, occupation, role);
	    userIndex.put(user.id, user);
	    nameIndex.put(firstName, user);
	    return user;
	  }
	  
	  
	  //get all users
	  public Collection<users> getUsers ()
	  {
	    return userIndex.values();
	  }
	  
	  
	  //delete all users
	  public  void deleteUsers() 
	  {
	    userIndex.clear();
	  }
	  
	  //get user by name
	public users getUserByName(String firstName) 
	  {
	    users user = nameIndex.get(firstName);
	    
	    return userIndex.get(user.id);
	  }
	  
	  //get user by id
	  public users getUser(Long id)
	  {
			return userIndex.get(id);
	  }
	  
	  // delete user by id
	  public void deleteUser(Long id) 
	  {
	    users user = userIndex.remove(id);
	    nameIndex.remove(user.firstName);
	  }
	    
	  
	  
/////////////////////////////////////////////////////////////////
/////////////// MOVIE DETAILS  ///////////////////////////////////
/////////////////////////////////////////////////////////////////
	  
	  private  Map<Long, movie> movieIndex = new HashMap<Long, movie>();
	  private  Map<String, movie> titleIndex = new HashMap<String, movie>();
	  
	  public movie addMovie(String title, String year, String url) {
		        movie movies = null;
				movies = new movie(title, year, url);
				movieIndex.put(movies.id, movies);
				titleIndex.put(movies.title, movies);
		
			return movies;	
		}
	  
	  public Collection<movie> getMovies ()
	  {
	    return movieIndex.values();
	  }
	  
	  
	public movie getMovieByTitle(String title) 
		  {
		return titleIndex.get(title);
		  }

		
		
		
	//////////////////////
	//////  Ratings  /////
	//////////////////////
		
	
		private Map<Long, ratings> ratingIndex = new HashMap<>();
		
		public void addRating(Long userid, Long movieid, int rating) {
			ratings ratings;
			Optional<users> user=Optional.fromNullable(userIndex.get(userid));
		    Optional<movie> movie=Optional.fromNullable(movieIndex.get(movieid));
		    if(movie.isPresent()&& user.isPresent())
		    {
		    	ratings = new ratings(userid, movieid, rating); 
		    	user.get().TheRatings.put(ratings.id, ratings); 
		    	movie.get().theratings.put(ratings.id, ratings); 
		    	ratingIndex.put(ratings.id, ratings);
		    }
		}
		
		
		public Map<Long, ratings> getUserRating(long id)
		{
			Optional<users> user = Optional.fromNullable(userIndex.get(id));
			return user.get().TheRatings;
		}
		
		public Map<Long, ratings> getMovieRating(long id)
		{
			Optional<movie> movies = Optional.fromNullable(movieIndex.get(id));
			return movies.get().theratings;
		}
		
		public void deleteRating(long id)
		{
			ratingIndex.remove(id);
		}
		
		
		
/////////////////////////////////
////////  Loaders   /////////////
/////////////////////////////////
		
		
		
		public void initisalLoad() throws IOException{  
			  String delims = "[|]";
			     Scanner scanner = new Scanner(new File("movies/users5.dat"));
			     while (scanner.hasNextLine()) {
			         String userDetails = scanner.nextLine();
			         // parse user details string
			         String[] userTokens = userDetails.split(delims);

			         if (userTokens.length == 7) {
			        	 String firstname = userTokens[1];
			        	 String lastname = userTokens[2];
			        	 String age = userTokens[3];
			        	 String gender = userTokens[4];
			        	 String occupation = userTokens[5];
			        	 
			        	 addUser(firstname, lastname, age, gender, occupation);     
			             
			         } else {
			             scanner.close();
			             throw new IOException("Invalid member length: " + userTokens.length);
			         }
			     }
			     
			     scanner = new Scanner(new File("movies/items5.dat"));
					while (scanner.hasNextLine()) {
						String userDetails = scanner.nextLine();
						// parse user details string
						String[] userTokens = userDetails.split(delims);

						if (userTokens.length == 23) {
						 
						 String title = userTokens[1];
			        	 String year = userTokens[2];
			        	 String url = userTokens[3];
			        	 
			        	 
			        	 addMovie(title, year, url);
			        	 
						} else {
							scanner.close();
							throw new IOException("Invalid member length: " + userTokens.length);
						}
					}
					
					scanner = new Scanner(new File("movies/ratings5.dat"));
					while (scanner.hasNextLine()) {
						String userDetails = scanner.nextLine();
						// parse user details string
						String[] userTokens = userDetails.split(delims);

						if (userTokens.length == 4) {
							
							String userid = userTokens[0];
				        	String movieid =userTokens[1];
				        	String rating = userTokens[2];
							
							
							addRating(Long.valueOf(userid), Long.valueOf(movieid), Integer.valueOf(rating));
						} else {
							scanner.close();
							throw new IOException("Invalid member length: " + userTokens.length);
						}
					}
					scanner.close();
				}
		
		
		public boolean login(Long userID, String lastName) {
		    Optional<users> user = Optional.fromNullable(userIndex.get(userID));
		    if (user.isPresent() && user.get().lastName.equals(lastName)) {
		      currentUser = user;    
		      return true;
		    }
		    return false;
		  }
		

		
		public void logout() {
			Optional<users> user = currentUser;
			if (user.isPresent()) {
				currentUser = Optional.absent();
				currentUser = null;
			}
		}

}
