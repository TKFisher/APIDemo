package files;


import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


public class ReusableMethods {

    public static XmlPath convertRawDataToXML(Response r) {

            String response = r.asString();
            XmlPath x = new XmlPath(response);
            return x;
    }

    public static JsonPath convertRawDataToJson(Response r) {

        String response = r.toString();
        JsonPath j = new JsonPath(response);
        return j;
    }

}
