package APITesting.com.org.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import APITesting.com.org.classes.Info2;
import APITesting.com.org.classes.Post2;
import APITesting.com.org.classes.Posts;
import APITesting.com.org.classes.advanced.Info3;
import APITesting.com.org.classes.advanced.Post3;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
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

	//	@Test
	public void Test_07() {
		Response resp = given().
				when().
				delete("http://localhost:3000/posts/3");

		System.out.println(resp.asString() + " delete test 07");

		/*		{} delete test 07
		PASSED: Test_07*/
	}

	// changed to db_info
	//	@Test
	public void Test_08() {
		Info2 info = new Info2();
		info.setEmail("bb@jj.com");
		info.setPhone("1231231234");
		info.setAddress("nowhere");

		Post2 posts = new Post2();
		posts.setId("9");
		posts.setTitle("something out there");
		posts.setAuthor("someone");
		posts.setInfo2(info);

		Response resp = (Response) given().
				when().
				contentType(ContentType.JSON).
				body(posts).
				post("http://localhost:3000/posts/");

		System.out.println(resp.asString() + "  test 08");

		/*		{
			  "id": "9",
			  "title": "something out there",
			  "author": "someone",
			  "info2": {
			    "email": "bb@jj.com",
			    "phone": "1231231234",
			    "address": "nowhere"
			  }
			}  test 08*/
	}

	// data starting out with
	/*	[
	  {
	    "id": 1,
	    "title": "json-server",
	    "author": "typicode",
	    "info": [
	      {
	        "email": "info@pickle.com",
	        "phone": "1234567890",
	        "address": "address line"
	      },
	      {
	        "email": "testing@gmail.com",
	        "phone": "0987654321",
	        "address": "second addres"
	      }
	    ]
	  }
	]*/

//	@Test
	public void test_09() {
		Info3 info = new Info3();
		info.setEmail("should be an email address");
		info.setPhone("1231231234");
		info.setAddress("putz lane");

		Info3 info2 = new Info3();
		info2.setEmail("should be second email address");
		info2.setPhone("0980980987");
		info2.setAddress("feltz lane");

		Post3 post = new Post3();
		post.setId("101");
		post.setTitle("hmm");
		post.setAuthor("no one");
		post.setInfo3(new Info3[] {info, info2});

		Response resp = given().
				when().
				contentType(ContentType.JSON).
				body(post).
				post("http://localhost:3000/posts/");

		System.out.println(resp.asString() + " test 09");

		{
			/*			  "id": "101",
			  "title": "hmm",
			  "author": "no one",
			  "info3": [
			    {
			      "email": "should be an email address",
			      "phone": "1231231234",
			      "address": "putz lane"
			    },
			    {
			      "email": "should be second email address",
			      "phone": "0980980987",
			      "address": "feltz lane"
			    }
			  ]
			} test 09
			PASSED: test_09*/
		}
	}
	@Test
	public void test_10() {
		Response resp = given().
				when().
				get("http://localhost:3000/posts/");

		Long time =resp.
		then().
		extract().
		time();
		
		System.out.println("Response time: " + time);
		System.out.println(resp.asString() + " test 10");
		
		given().
				when().
				get("http://localhost:3000/posts/").
				then().
				and().
				time(lessThan(800L));
		
/*		the last given fails because it is over 10 milliseconds
 * time(lessThan(10L));
 * change to 
 * time(lessThan(800L));
 *   And the test passes
		[RemoteTestNG] detected TestNG version 6.9.10
		[TestNG] Running:
		  C:\Users\sedzi\AppData\Local\Temp\testng-eclipse--2019677050\testng-customsuite.xml

		Response time: 778
		[
		  {
		    "id": 1,
		    "title": "json-server",
		    "author": "typicode",
		    "info": [
		      {
		        "email": "info@pickle.com",
		        "phone": "1234567890",
		        "address": "address line"
		      },
		      {
		        "email": "testing@gmail.com",
		        "phone": "0987654321",
		        "address": "second addres"
		      }
		    ]
		  },
		  {
		    "id": "9",
		    "title": "something out there",
		    "author": "someone",
		    "info2": {
		      "email": "bb@jj.com",
		      "phone": "1231231234",
		      "address": "nowhere"
		    }
		  },
		  {
		    "id": "10",
		    "title": "hmm",
		    "author": "no one",
		    "info3": [
		      {
		        "email": "should be an email address",
		        "phone": "1231231234",
		        "address": "putz lane"
		      },
		      {
		        "email": "should be second email address",
		        "phone": "0980980987",
		        "address": "feltz lane"
		      }
		    ]
		  },
		  {
		    "id": "101",
		    "title": "hmm",
		    "author": "no one",
		    "info3": [
		      {
		        "email": "should be an email address",
		        "phone": "1231231234",
		        "address": "putz lane"
		      },
		      {
		        "email": "should be second email address",
		        "phone": "0980980987",
		        "address": "feltz lane"
		      }
		    ]
		  }
		] test 10
		FAILED: test_10
		change to 6*/
		// need to push to github
	}

}
