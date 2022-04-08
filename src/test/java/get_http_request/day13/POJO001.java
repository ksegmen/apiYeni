package get_http_request.day13;

import base_url.DummyBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Data;
import pojos.DummyPojo;

import static io.restassured.RestAssured.given;

public class POJO001 extends DummyBaseUrl {

 /*
GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
                           Status code is 200
{
 "status": "success",
 "data": {
   "id": 1,
   "employee_name": "Tiger Nixon",
   "employee_salary": 320800,
   "employee_age": 61,
   "profile_image": ""
   },
 "message": "Successfully! Record has been fetched."
 }

*/


    @Test
    public void test01(){
        spec02.pathParams("bir","api","iki","v1","uc","employee","dort",1);
        Data data=new Data(1,"Tiger Nixon",320800,61,"");
        DummyPojo expectedData=new DummyPojo("success",data, "Successfully! Record has been fetched.");


        Response response=given().contentType(ContentType.JSON).spec(spec02).when().get("/{bir}/{iki}/{uc}/{dort}");
        response.prettyPrint();
        DummyPojo actualData=response.as(DummyPojo.class);



        Assert.assertEquals(expectedData.getStatus(),actualData.getStatus());
        Assert.assertEquals(expectedData.getData().getId(),actualData.getData().getId());
        Assert.assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());
        Assert.assertEquals(expectedData.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
        Assert.assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
        Assert.assertEquals(expectedData.getData().getProfile_image(),actualData.getData().getProfile_image());
       Assert.assertEquals(expectedData.getMessage(),actualData.getMessage());

Gson gson = new Gson();
String jsonfromJava=gson.toJson(actualData);
System.err.println(jsonfromJava);
        System.out.println("jsonfromJava = " + jsonfromJava);


    }




}
