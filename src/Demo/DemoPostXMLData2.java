package Demo;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static files.ReusableMethods.convertRawDataToXML;
import static io.restassured.RestAssured.given;
import static java.lang.System.*;


public class DemoPostXMLData2 {

    @Test
    public void postData() throws IOException {

        String postData = GenerateStringFromResourcePath("/Users/tracyfisher/GIT_Projects/Training/FilesForProjects/postDataXML.xml");
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


        // use method to convert data to xml
        XmlPath responseXML = convertRawDataToXML(returnPostData);
        responseXML.get ("PlaceAddResponse.place_id");
    }

    // Method to handle xml string by reading characters as bytes and casting as a string.
    public static String GenerateStringFromResourcePath(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));

    }
}
