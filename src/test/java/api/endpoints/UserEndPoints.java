package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	
	public static Response createUser(User Payload)
	
	{
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(Payload)
				
				.when()
				.post(Routes.post_url);
		return response;
	}
	
public static Response readUser(String username)
	
	{
		
		Response response=given()
				.pathParams("username",username)
				
				.when()
				.get(Routes.get_url);
		return response;
	}

public static Response upadteUser(String username ,User Payload)

{
	
	Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(Payload)
			.pathParams("username",username)
			
			.when()
			.put(Routes.update_url);
	return response;
}

public static Response deleteUser(String username)

{
	
	Response response=given()
			.pathParams("username",username)
			
			.when()
			.delete(Routes.delete_url);
	return response;
}

}
