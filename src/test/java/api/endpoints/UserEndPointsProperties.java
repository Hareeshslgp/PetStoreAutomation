package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPointsProperties {

	
	//Method for loading properties
	static ResourceBundle getURL()
	
	{
		 	ResourceBundle routes=ResourceBundle.getBundle("routes");// Load the properties file 
		 	return routes;
	}
	
	
	public static Response createUser(User Payload)
	
	{
		String Post_URL=getURL().getString("post_url");
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(Payload)
				
				.when()
				.post(Post_URL);
		return response;
	}
	
public static Response readUser(String username)
	
	{
	String Get_URL=getURL().getString("get_url");
		Response response=given()
				.pathParams("username",username)
				
				.when()
				.get(Get_URL);
		return response;
	}

public static Response upadteUser(String username ,User Payload)

{
	
	String Update_URL=getURL().getString("put_url");
	Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(Payload)
			.pathParams("username",username)
			
			.when()
			.put(Update_URL);
	return response;
}

public static Response deleteUser(String username)

{
	String Delete_URL=getURL().getString("delete_url");
	
	Response response=given()
			.pathParams("username",username)
			
			.when()
			.delete(Delete_URL);
	return response;
}

}
