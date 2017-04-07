package Demo;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;


public class DemoPostXMLData {

    @Test
    public void postData() throws IOException {

        String postData = GenerateStringFromResource("/Users/tracyfisher/GIT_Projects/Training/FilesForProjects/postDataXML.xml");
        RestAssured.baseURI = "https://maps.googleapis.com/";

        Response returnPostData = given().
                queryParam("key", "AIzaSyBde6fW-IAx1j-J5TqNOwmvx-_QPHozqRY").
                body(postData).
                when().
                post("/maps/api/place/add/xml").
                then().assertThat().statusCode(200).and().
                contentType(ContentType.XML).
                extract().
                response();

        // convert response to string w/debug output
        String responseString = returnPostData.asString();
        System.out.println(responseString);

        // convert string response to xml w/debug output 
        XmlPath responseXML = new XmlPath(responseString);
        System.out.println((String) responseXML.get("PlaceAddResponse.place_id"));
    }

    // Method to handle xml string by reading characters as bytes and casting as a string.
    public static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));

    }
}
