package Demo;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


public class basics {

    //public static void main(String[] args) {

        @Test
        public void Test () {
            // Pass Base URL
            RestAssured.baseURI = "https://maps.googleapis.com/";

        /*  Pass Header, params, cookies
            given()
                Request headers
                Parameters
                request cookies
                body
        *   Define type
            when()
                get (resource)
                post (resource)
                put (resource)
        *   State asserts
            then()
                assertThat()  */
            
            given().
                    param("location", "-33.8670522,151.195736").
                    param("radius", "500").
                    param("key", "AIzaSyBde6fW-IAx1j-J5TqNOwmvx-_QPHozqRY").
                    when().
                    get("/maps/api/place/nearbysearch/json").
                    then().assertThat().statusCode(200).and().
                    contentType(ContentType.JSON).and().
                    body("results[0].name", equalTo("Sydney")).and().
                    body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM"));
        }
    }



