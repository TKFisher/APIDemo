package Demo;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


import org.testng.annotations.Test;

public class DemoGivenData2 {

    @Test
    public void givenData() {

        // Pass Base URL
        RestAssured.baseURI="https://maps.googleapis.com";

                Response returnGivenData = given().
                param("location", "-33.8670522,151.195736").
                param("radius", "500").
                param("key", "AIzaSyBde6fW-IAx1j-J5TqNOwmvx-_QPHozqRY").
                when().
                get("/maps/api/place/nearbysearch/json").
                then().assertThat().statusCode(200).and().
                contentType(ContentType.JSON).and().
                body("results[0].name", equalTo("Sydney")).and().
                body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
                header("Server", "pablo").
                extract().
                response();
        JsonPath responseJson = ReusableMethods.convertRawDataToJson(returnGivenData);

        // TODO: This next line cause an error that I need to fix.
//        int count=responseJson.get("results.size()");
//        System.out.println(count);
    }
}



