package APITesting.com.org.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import APITesting.com.org.classes.Posts;

import static com.jayway.restassured.RestAssured.*;

public class JasonServerRequests {

	
	//@Test
	public void test_01() {
		Response resp = given().
		when().
		get("http://localhost:3000/posts");
		
		System.out.println("resp " + resp.asString() + " test 01");
		
/*		[
		  {
		    "id": 1,
		    "title": "json-server",
		    "author": "typicode"
		  }
		]*/
	}
	
//	@Test
	public void test_02() {
		
		Response resp = given().
		body("  {\"id\":\"2\","
				+ "\"title\":\"dummyTitle\","
				+ "\"author\":\"who\"}  ").
		when().
		contentType(ContentType.JSON).
		post("http://localhost:3000/posts");
		
		System.out.println("resp " + resp.asString() + " test 02");
		
/*		[
		  {
		    "id": 1,
		    "title": "json-server",
		    "author": "typicode"
		  },
		  {
		    "id": "2",
		    "title": "dummyTitle",
		    "author": "who"
		  }
		]*/
	}
	
//	@Test
	public void Test_03() {
		Posts posts = new Posts();
		posts.setId("3");
		posts.setTitle("posts requests by object");
		posts.setAuthor("someone");
		
		Response resp = given().
		when().
		contentType(ContentType.JSON).
		body(posts).
		post("http://localhost:3000/posts");
		
		System.out.println("resp " + resp.asString() + " test 03");
		
/*		resp {
			  "id": "3",
			  "title": "posts requests by object",
			  "author": "someone"
			} test 03
			PASSED: Test_03*/
	}
	
	//@Test
	public void test_04() {
		Response resp = given().
		when().
		get("http://localhost:3000/posts/3");
		
		System.out.println(resp.asString() + " test 04");

/*		{
			  "id": "3",
			  "title": "posts requests by object",
			  "author": "someone"
			} test 04
			PASSED: test_04*/
	}
	
	//@Test
	public void test_05() {
		Posts posts = new Posts();
		posts.setId("3");
		posts.setAuthor("someone else");
		posts.setTitle(" updated");
		
		Response resp = given().
		when().
		contentType(ContentType.JSON).
		body(posts).
		put("http://localhost:3000/posts/3");
		
		System.out.println(resp.asString() + " test 05");
		
/*		{
		    "id": "3",
		    "title": " updated",
		    "author": "someone else"
		  }*/
	}
	
	// put request updates all, and all values must be sent
	// patch request only updates certain values
	//@Test
	public void Test_06() {
		
		Response resp = given().
		body("{  \"title\":\"updated by put request\" }").
		when().
		contentType(ContentType.JSON).
		patch("http://localhost:3000/posts/3");
		
		System.out.println(resp.asString() + " patch request test 06");
		
/*		{
			  "id": "3",
			  "title": "updated by put request",
			  "author": "someone else"
			} patch request test 06
			PASSED: Test_06*/
	}
	
	@Test
	public void Test_07() {
		Response resp = given().
		when().
		delete("http://localhost:3000/posts/3");
		
		System.out.println(resp.asString() + " delete test 07");
		
/*		{} delete test 07
		PASSED: Test_07*/
	}
}
