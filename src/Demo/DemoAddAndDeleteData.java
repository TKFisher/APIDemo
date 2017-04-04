package Demo;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class DemoAddAndDeleteData {

@Test
    public void addAndDeletePlace(){

    String body =
            "{"+
            "\"location\": {"+
            "\"lat\": -33.8669710,"+
            "\"lng\": 151.1958750"+
            "},"+
            "\"accuracy\": 50,"+
            "\"name\": \"Google Shoes!\","+
            "\"phone_number\": \"(02) 9374 4000\","+
            "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
            "\"types\": [\"shoe_store\"],"+
            "\"website\": \"http://www.google.com.au/\","+
            "\"language\": \"en-AU\""+
            "}";

    RestAssured.baseURI = "https://maps.googleapis.com/";

    // Get the response
    Response response = given().
            queryParam("key", "AIzaSyBde6fW-IAx1j-J5TqNOwmvx-_QPHozqRY").
            body(body).
            when().
            post("/maps/api/place/add/json").
            then().assertThat().statusCode(200).and().
            contentType(ContentType.JSON).and().
            body("status", equalTo("OK")).
            extract().response();

    // Convert string to json format and get the placeID
    String responseString = response.asString();
    System.out.println(responseString);
    JsonPath jsonFormat = new JsonPath(responseString);
    String placeid = jsonFormat.get("place_id");
    System.out.println(placeid);

    // Use the placeID in the Delete request
    given().
            queryParam("key", "AIzaSyBde6fW-IAx1j-J5TqNOwmvx-_QPHozqRY").
            body("{"+
                    "\"place_id\": \""+ placeid +"\""+
                    "}"
            ).
            when().
            post("/maps/api/place/delete/json").
            then().assertThat().statusCode(200).and().
            contentType(ContentType.JSON).and().
            body("status", equalTo("OK"));
}
}
