package get_http_request.day13;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;

public class PostRequestPojo002 extends HerOkuAppBaseUrl {

     /*
      {
     "bookingid": 11,
            "booking": {
                    "firstname": "Ali",
                    "lastname": "Can",
                    "totalprice": 500,
                    "depositpaid": true,
                    "bookingdates": {
                                    "checkin": "2022-03-01",
                                    "checkout": "2022-03-01",
                              }
                          }
                      }
     */



    @Test
    public void test01(){

        spec05.pathParam("1","booking");
        BookingDatesPojo bookingdates=new BookingDatesPojo("2022-03-01","2022-03-11");
        System.out.println("bookingdates = " + bookingdates);

        BookingPojo  bookingPojo=new BookingPojo("Ali", "Can",500,true,bookingdates);
        System.out.println("bookingPojo = " + bookingPojo);
        Response response=given().contentType(ContentType.JSON).spec(spec05).auth().basic("admin","password123").
                body( bookingPojo).when().post("/{1}");
        response.prettyPrint();

BookingResponsePojo actualData=response.as(BookingResponsePojo.class);
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(bookingPojo.getFirstname(), actualData.getBooking().getFirstname());
        Assert.assertEquals(bookingPojo.getLastname(), actualData.getBooking().getLastname());
        Assert.assertEquals(bookingPojo.getTotalprice(), actualData.getBooking().getTotalprice());
        Assert.assertEquals(bookingPojo.isDepositpaid(), actualData.getBooking().isDepositpaid());
        Assert.assertEquals(bookingPojo.getBookingdates().getCheckin(),
                actualData.getBooking().getBookingdates().getCheckin());

        Assert.assertEquals(bookingPojo.getBookingdates().getCheckout(),
                actualData.getBooking().getBookingdates().getCheckout());


    }
}
