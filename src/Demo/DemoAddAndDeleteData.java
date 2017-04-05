package Demo;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import files.resource;
import files.body;

public class DemoAddAndDeleteData {

    Properties properties = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream("/Users/tracyfisher/GIT_Projects/Training/APIDemo/src/files/env.properties");
        properties.load(fis);
    }

@Test
    public void addAndDeletePlace(){

    String b;

    RestAssured.baseURI = properties.getProperty("HOST");

    // Get the response
    Response response = given().
            queryParam("key", properties.getProperty("KEY")).
            body(body.getPostData()).
            when().
            post(resource.placePostData()).
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
