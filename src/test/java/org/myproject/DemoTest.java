package org.myproject;

import static org.junit.Assert.assertTrue;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

// Sample API test
public class DemoTest
{

    @Test
    public void restWeatherTest() {

        // Specifying the Base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";


        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step.
        RequestSpecification httpRequest = RestAssured.given();

        // Make a request to the server by specifying the method Type and the method URL.
        // This will return the Response from the server. Store the response in a variable.
        Response response = httpRequest.request(Method.GET, "/sacramento");

        // Now let us print the body of the message to see what response
        // we have received from the server
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        int statusCode = response.getStatusCode();
        System.out.println("Status Code ===>    "+statusCode);

        Assert.assertEquals(statusCode, 200);


        System.out.println(" Status Line ===>   "+response.getStatusLine());
        System.out.println(" content type ===>  "+response.contentType());
        System.out.println(" time ===>          "+response.getTime());
        // It gives list of all headers
        //System.out.println("Response Header==>  "+response.headers());

        // To get particular header, mention the header
        System.out.println("Getting a particular header ==> "+response.header("Content-Type"));

        // TO iterate over all headers one at time
        Headers totalHeaders = response.headers();
        for ( Header eachHeader:totalHeaders){
            System.out.println("Key = "+eachHeader.getName()+"  |   "+ "Value = "+eachHeader.getValue());

        }
    // TO validate a particular header
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json");

    // Validate body of the response for specific node using JSON PATH

        JsonPath  jsonPathEvaluator = response.jsonPath();
        String cityName = jsonPathEvaluator.get("City");
        System.out.println(" City Name ====> "+cityName);




    }
}
