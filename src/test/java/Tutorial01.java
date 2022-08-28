import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class Tutorial01 {
    @Test
    public void GetPostDetails() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts/";
        // Get the RequestSpecification of the request to be sent to the server.
        RequestSpecification httpRequest = RestAssured.given();
        // specify the method type (GET) and the parameters if any.
        //In this case the request does not take any parameters
        Response response = httpRequest.request(Method.GET, "2");
        // Print the status and message body of the response received from the server
       // System.out.println("Status received => " + response.getStatusCode());
       // System.out.println("Response=>" + response.prettyPrint());

        JsonPath responseJsonData = response.jsonPath();
        String title = responseJsonData.get("body");
        System.out.print(title);

    }

    @Test
    public void addNewPost()
    {
        RestAssured.baseURI ="https://jsonplaceholder.typicode.com/";
        RequestSpecification request = RestAssured.given();

        // JSONObject is a class that represents a Simple JSON.
        // We can add Key - Value pairs using the put method
        JSONObject requestParams = new JSONObject();
        requestParams.put("title", "AutomationWithArnab");
        requestParams.put("body", "Learn API automation using RestAssured!");
        requestParams.put("userId",1);

        // Add a header stating the Request body is a JSON
        request.header("Content-Type", "application/json"); // Add the Json to the body of the request
        request.body(requestParams.toJSONString()); // Post the request and check the response

        Response response = request.post("/posts");
        ResponseBody body = response.getBody();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());

    }
    @Test
    public void editAPost()
    {
        RestAssured.baseURI ="https://jsonplaceholder.typicode.com/";
        RequestSpecification request = RestAssured.given();

        // JSONObject is a class that represents a Simple JSON.
        // We can add Key - Value pairs using the put method
        JSONObject requestParams = new JSONObject();
        requestParams.put("title", "AutomationWithArnab");
        requestParams.put("body", "Learn API automation using RestAssured and Java!");
        requestParams.put("userId",1);

        // Add a header stating the Request body is a JSON
        request.header("Content-Type", "application/json"); // Add the Json to the body of the request
        request.body(requestParams.toJSONString()); // Post the request and check the response

        int i=1;
        Response response = request.put("/posts/"+i);
        ResponseBody body = response.getBody();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());

    }

    @Test
    public void GetDeletePost() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        // Get the RequestSpecification of the request to be sent to the server.
        RequestSpecification httpRequest = RestAssured.given();


        Response response = httpRequest.delete("/posts/1");
        // Print the status and message body of the response received from the server
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());

    }

}
