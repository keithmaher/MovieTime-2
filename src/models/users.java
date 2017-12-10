package models;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;

import utils.ToJsonString;


public class users implements Comparable<users>{
	
	static Long counter = 1L;
	public Long id = 1L;
	public String firstName;
	public String lastName;
	public String age;
	public String gender;
	public String occupation;
	public Map<Long, ratings> TheRatings = new HashMap<>();
	public String role;
	
	
	public users(String firstName, String lastName, String age, 
			String gender, String occupation)
	{
		this(firstName,lastName,age,gender,occupation, "default");
	}
	
	public users(String firstName, String lastName, String age, 
			String gender, String occupation, String role) {
		this.id = counter++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
		this.role = role;
	 }
	 
	 @Override
	  public String toString()
	  {
		 return new ToJsonString(getClass(), this).toString();
	  }

	  
	  public int hashCode()  
	  {  
	     return Objects.hashCode(this.firstName, this.lastName, this.age, this.gender, this.occupation);  
	  }
	
	
	public boolean equals(final Object obj)
	{
		if(obj instanceof users)
		{
			final users other=(users) obj;
			return Objects.equal(firstName, other.firstName)
				&& Objects.equal(lastName, other.lastName)
				&& Objects.equal(age, other.age)
				&& Objects.equal(gender, other.gender)
				&& Objects.equal(occupation, other.occupation);
		}
		else
		{
			return false;
		}
	}
	
	
	//return users sorted by last name
	public int compareTo(users user)
	{
		return this.lastName.compareTo(user.lastName);
	}

}
