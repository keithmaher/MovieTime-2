package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import asg.cliche.Command;
import asg.cliche.Param;
import models.movie;
import models.users;

public class DefaultMenu {

	  private String name;
	  private users user;
	  private MovieTimeAPI movietime;

	  public DefaultMenu(MovieTimeAPI movietime, users user) {
	    this.movietime = movietime;
	    this.setName(user.firstName);
	    this.user = user;
	  }
	  
	  public void setName(String name) {
			this.name = name;
	    }
	  
	  
	  //TreeSet instead of hash map, easier to use
	  //lists all users in order, second name first
	  @Command(description = "Get users and sort by full name")
	  public void GetAllUsers() {
		  TreeSet<users> sortedUsers = new TreeSet<users>();
			sortedUsers.addAll(movietime.getUsers());
			Iterator<users> iteratoR = sortedUsers.iterator();
			while(iteratoR.hasNext()) {
				users usrs = iteratoR.next();
				System.out.println(usrs.lastName + " " + usrs.firstName);  
	  }
	  }

//	  @Command(description = "Get a Users detail")
//	  public void getUser(@Param(name = "userID") Long id) {
//	    users user = movietime.getUser(id);
//	    System.out.println(user);
//	  }
	  
//	  @Command(description = "Create a new user")
//	  public void CreateUser(@Param(name = "first name") String fName, @Param(name = "last name") String lName, 
//	  @Param(name = "age") String age, @Param(name = "gender") String gender, @Param(name = "job") String job)
//			  {
//		  movietime.addUser(fName, lName, age, gender, job);
//			  }
//	  
	  @Command(description = "Search user by name")
	  public void getUserByName(String name) 
	  {
		  int i =0;
		  ArrayList<users> users = new ArrayList<users>();
		  users.addAll(movietime.getUsers());
		  while( i < users.size())
		  {
			  if(users.get(i).firstName.toUpperCase().contains(name.toUpperCase()))
			  {
				  System.out.println(users.get(i));
			  }
			  i++;
		  }
	  }
	  	  
	  
//	  @Command(description = "Add Movie")
//	  public void addMovies(@Param(name = "title") String title, @Param(name = "year") String year,
//	      @Param(name = "url") String url) {
//	    movietime.addMovie(title, year, url);
//	  }
	  
		//TreeSet instead of hash map, easier to use. Iterator is for loop using collections//
	  @Command(description = "Get movies and sort by title")
	  public void getAllMovies() {
		  TreeSet<movie> sortedMovies = new TreeSet<movie>();
			sortedMovies.addAll(movietime.getMovies());
			Iterator<movie> iteratoR = sortedMovies.iterator();
			System.out.println("List of movies sorted by the title and released date");
			while(iteratoR.hasNext()) {
				movie movie = iteratoR.next();
				System.out.println(movie.title + ",  Released on " + movie.videoYear);  
	  }
	  }
	  
	  @Command(description = "Get movie by title")
		public void getMovieByTitle(String title)
		{
			{
				  int i =0;
				  ArrayList<movie> movie = new ArrayList<movie>();
				  movie.addAll(movietime.getMovies());
				  while( i < movie.size())
				  {
					  if(movie.get(i).title.toUpperCase().contains(title.toUpperCase()))
					  {
						  System.out.println(movie.get(i));
					  }
					  i++;
				  }
			  }
		}
		  
	  
//	  @Command(description = "Get a movie by ID")
//	  public movie getMovie(@Param(name = "Movie ID") Long id)
//	  {
//	    return movietime.getMovie(id);
//	  }
	  
	
//	  @Command(description = "Get user ratings")
//	  public void getUserRatings(@Param(name = "User ID") long id)
//	  {
//		  System.out.println(movietime.getUserRating(id)); 
//	  }
	  
      //rating for spacific movie, not just by the user. 
	  @Command(description = "Get movie ratings")
	  public void getMovieRatings(@Param(name = "Movie Title") Long id)
	  {
		   System.out.println(movietime.getMovieRating(id)); 
	  }
	  
//	  @Command(description = "Get a ratings")
//	  public void getRatings(@Param(name = "Rating ID") Long id)
//	  {
//		  System.out.println(movietime.getRating(id)); 
//	  }
	  
	  
//	  @Command(description = "Get all ratings")
//	 public void getRatings()
//	  {
//		 System.out.println(movietime.getRatings()); 
//	  }
	
	
	@Command(description = "Add a ratings")
	  public void addRatings(@Param(name = "userID") long userid,
	      @Param(name = "movieID") long movieid,  @Param(name = "rating") int ratings) {
	      movietime.addRating(userid, movieid, ratings);
	  }
	
	
//	  @Command(description = "Delete a ratings")
//	  public void deleteRating(@Param(name = "Rating ID") Long id)
//	  {
//			  movietime.deleteRating(id);
//	  }
	  
	  @Command(description="Save File")
		public void save() throws Exception{
			movietime.store();
			System.out.println("Saving your details");
		}
	  
	  @Command(description="Logout")
		public void LogOut() throws Exception{
			movietime.logout();
			System.out.println("Logging out");
		}
	  
	  
}