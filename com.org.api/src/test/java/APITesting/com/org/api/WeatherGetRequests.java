package APITesting.com.org.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*; // static is used so anobject doesn't need to be created

public class WeatherGetRequests {
	
	// get request - get weather request by city name
	// status code 200
	/*@Test
	public void Test_01() {
		
		Response resp = when().		
				get("http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=f66ba0146d2079315aad40e33fe1af62");
		
		System.out.println(resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);
	}
	
	// status code 401
		@Test
		public void Test_02() {
			
			Response resp = when().		
					get("http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=f66ba0146d2079315aad40e33fe1af61");
			
			System.out.println(resp.getStatusCode());// the last character of the appid was changed
			Assert.assertEquals(resp.getStatusCode(), 401);
		}
*/
	// how to use parameters with rest
	@Test
	public void Test_03() {
		
		Response resp = given().
				param("q", "London").
				param("appid", "f66ba0146d2079315aad40e33fe1af62").	
				when().		
				get("http://api.openweathermap.org/data/2.5/weather");
		
		System.out.println(resp.getStatusCode());
		Assert.assertEquals(resp.getStatusCode(), 200);
		
		if (resp.getStatusCode()==200) {
			System.out.println("API is working");
		} 
		else {
			System.out.println("Problem with API, should have returned a 200");
			System.out.println("resp.getStatusCode() " + resp.getStatusCode());
		}
	}
	
	// asset testcase in Rest assured api
	@Test
	public void Test_04() {
		
				given().
				param("q", "London").
				param("appid", "f66ba0146d2079315aad40e33fe1af62").	
				when().		
				get("http://api.openweathermap.org/data/2.5/weather").
				then().
				assertThat().statusCode(200);
	}
	
	@Test
	public void Test_05() {
		
		Response resp = given().
				param("q", "London").
				param("appid", "f66ba0146d2079315aad40e33fe1af62").	
				when().		
				get("http://api.openweathermap.org/data/2.5/weather");
		System.out.println(resp.asString());  // data is returned in a json format
	}
	
	@Test
	public void Test_06() {
		
		Response resp = given().
				param("id", "2172797").
				param("appid", "f66ba0146d2079315aad40e33fe1af62").	
				when().		
				get("http://api.openweathermap.org/data/2.5/weather");
		System.out.println(resp.asString());  
		Assert.assertEquals(resp.getStatusCode(), 200);
	}
	@Test
	public void Test_06a() {
		
		Response resp = given().
				param("zip", "45202,us").
				param("appid", "f66ba0146d2079315aad40e33fe1af62").	
				when().		
				get("http://api.openweathermap.org/data/2.5/weather");
		System.out.println(resp.asString());  
		Assert.assertEquals(resp.getStatusCode(), 200);
	}
	/*
	*  json data
	*  {
	*  		"store": {
	*  			"book": [{
	*  				"catagorie": "fiction",
	*  				"author": "Herman Melville",
	*  				"title": " Mobby Dick",
	* 				"isbin": "123-334",
	* 				"price": 10.99
	* 			},
	* 			{
	* 				 "catagorie": "fiction",
	*  				"author": "J. R. R. Tolken",
	*  				"title": "The Hobbit",
	* 				"isbin": "143-33",
	* 				"price": 22.88			
	* 			}],
	* 		"Bicycle": {
	* 			"color", "red",
	* 			"price", 200.87
	* 		}
	* }
	* 
	* $..author					--> all books
	* $.store.book[0].author	--> first author
	* $.store.book[*].author	--> all authors
	* $.store.book[1].author	--> second author
	* $.store.book[0]			--> all details of the first book 
	* $.store.book[0,1]			--> first and second book
	* $.store.book				--> all books
	* $.store.book[1:]			--> all books starting with the second book
	* $.store.book[-1:]			--> last book
	* $.store.book[?(@.isbin)]	--> '?' query '@isbin' get all with isbin
	* 
	* $.store.book[?(@.catagorie="reference)] --> all books with catagorie with 'reference'
	* $.store.book[?(@.price>15)]				--> all books with a price greater then 15
	* 
	* */
}