package controller;

import java.io.File;
import java.io.IOException;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;
import asg.cliche.ShellFactory;
import models.users;
import utils.FileLogger;
import utils.Serializer;
import utils.XMLSerializer;

public class Main implements ShellDependent{
	
	private static final String ADMIN = "admin";
	public MovieTimeAPI movietime = new MovieTimeAPI();
	private Shell theShell;
	
	public Main() throws Exception {
		File userList = new File ("./lib/usersList.xml");
		Serializer serializer = new XMLSerializer(userList);
		movietime= new MovieTimeAPI(serializer);
		if (userList.isFile()) 
		{
			movietime.load();
		}
		movietime.store();
	}
	
	public void cliSetShell(Shell theShell) {
	    this.theShell = theShell;
	  }
	
	@Command(description = "Log in")
	  public void logIn(@Param(name = "userId") Long userid, @Param(name = "Last name") String lastName)
	      throws IOException {
	
	    if (movietime.login(userid, lastName) && movietime.currentUser.isPresent()) {
	      users user = movietime.currentUser.get();
	      System.out.println("You are logged in as " + user.firstName);
	      if (user.role !=null && user.role.equalsIgnoreCase(ADMIN)) {
	        AdminMenu adminMenu = new AdminMenu(movietime, user.firstName);
	        ShellFactory.createSubshell(user.firstName, theShell, "Admin", adminMenu).commandLoop();
	      } else {
	        DefaultMenu defaultMenu = new DefaultMenu(movietime, user);
	        ShellFactory.createSubshell(user.firstName, theShell, "Default User", defaultMenu).commandLoop();
	      }
	    } else
	      System.out.println("Sorry");
	      System.out.println("Unknown username/password.");
	  }
	
	@Command(description = "Register")
	  public void Register(@Param(name = "first name") String firstName, @Param(name = "last name") String lastName, 
	  @Param(name = "age") String age, @Param(name = "gender") String gender, @Param(name = "occupation") String occupation, @Param(name = "role") String role) throws Exception
	  {
			movietime.addUser(firstName, lastName, age, gender, occupation, role);
			movietime.store();
			System.out.println("Saving your details");
			System.out.println(movietime.getUserByName(firstName));
			
	  }

	public static void main(String[] args) throws Exception {
		
		Main api = new Main();
		api.movietime.initisalLoad();
		FileLogger logger = FileLogger.getLogger();
	    logger.log("Creating - user list");
	    Shell shell = ShellFactory.createConsoleShell("user", "Welcome to MovieTime - ?help for instructions", api);
	    shell.commandLoop();
	    api.movietime.store();
	}
			     	
}
