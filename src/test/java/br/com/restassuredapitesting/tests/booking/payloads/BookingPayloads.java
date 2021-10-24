package br.com.restassuredapitesting.tests.booking.payloads;

import org.json.JSONException;
import org.json.JSONObject;

public class BookingPayloads {
    public static JSONObject payloadValidBooking() throws JSONException {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        payload.put("firstname", "Ronaldo");
        payload.put("lastname", "Rosa");
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("addiotnalneeds", "breakFast");

        return payload;
    }

    public static JSONObject payloadInvalidBooking() throws JSONException {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2014-01-01");
        bookingDates.put("checkout", "2050-01-01");

        payload.put("firsname","Michael" );
        payload.put("lastname", "Silveira");
        payload.put("totalprice",1234);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("addiotnalneeds", "breakfast");

        return payload;
    }

    public static JSONObject payloadBookingWIthAdditionalParameters() throws JSONException {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        payload.put("firstname", "Ronaldo");
        payload.put("lastname", "Rosa");
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("addiotnalneeds", "breakFast");
        payload.put("aditionalname","Maicom");
        payload.put("dateofbirth","1987-29-11");

        return payload;
    }
}
