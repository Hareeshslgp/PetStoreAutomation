package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTest {

	
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String UserID,String UserName,String FirstName	, String lastName,String Email	,String Password,String Phone
)
	{
		User userpayload=new User();
		userpayload.setId(Integer.parseInt(UserID));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FirstName);
		userpayload.setLastName(lastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		
		
		Response response=UserEndPoints.createUser(userpayload);
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority=2,dataProvider="Usernames",dataProviderClass=DataProviders.class)
	public void testDeleteUser(String UserName)
	{
		User userpayload=new User();
	    userpayload.setUsername(UserName);
		
		Response response=UserEndPoints.deleteUser(UserName);
	
		Assert.assertEquals(response.getStatusCode(),200);
		
	} 
	
}
