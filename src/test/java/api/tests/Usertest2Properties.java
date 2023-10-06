package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPointsProperties;
import api.payload.User;

public class Usertest2Properties {
	
	Faker faker;
	User userpayload;
	public Logger logger; // Log
	
	@BeforeClass
	public void setupdata()
	{
		faker=new Faker();
		userpayload=new User();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setFirstName(faker.address().firstName());
		userpayload.setUsername(faker.name().username());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setPassword(faker.internet().password());
		userpayload.setPassword(faker.phoneNumber().cellPhone());
		
		//logger iintailization
		
		logger=LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	
	public void TestPostUser()
	{
		logger.info("*****Createing User***");
		
		Response response=UserEndPointsProperties.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority=2)
	
	public void TestGetUser()
	{
		logger.info("*****Getting User***");
		Response response=UserEndPointsProperties.readUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
	}
	@Test(priority=3)
	
	public void TestupadteUser()
	{
		logger.info("*****Upadting User***");
		userpayload.setFirstName(faker.address().firstName());
		
		userpayload.setLastName(faker.name().lastName());
		
		Response response=UserEndPointsProperties.upadteUser( this.userpayload.getUsername(),userpayload);
		response.then().log().all();
		
		Response afterUpadte=UserEndPointsProperties.upadteUser(this.userpayload.getUsername(), userpayload);
		Assert.assertEquals(afterUpadte.statusCode(), 200);
	}
	@Test(priority=4)
	public void TestdeleteUser()
	{
		logger.info("*****Deleting User***");
		Response response=UserEndPointsProperties.deleteUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
}
}