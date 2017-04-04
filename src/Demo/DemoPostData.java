package Demo;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


public class DemoPostData {

    @Test
    public void postData() {

        RestAssured.baseURI = "https://maps.googleapis.com/";

        given().
                queryParam("key", "AIzaSyBde6fW-IAx1j-J5TqNOwmvx-_QPHozqRY").
                body(
                        "{" +
                                "\"location\": {" +
                                "\"lat\": -33.8669710," +
                                "\"lng\": 151.1958750" +
                                "}," +
                                "\"accuracy\": 50," +
                                "\"name\": \"Google Shoes!\"," +
                                "\"phone_number\": \"(02) 9374 4000\"," +
                                "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\"," +
                                "\"types\": [\"shoe_store\"]," +
                                "\"website\": \"http://www.google.com.au/\"," +
                                "\"language\": \"en-AU\"" +
                                "}").
                when().
                post("/maps/api/place/add/json").
                then().assertThat().statusCode(200).and().
                contentType(ContentType.JSON).and().
                body("status", equalTo("OK"));
    }

}
