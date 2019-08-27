package evrika;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.URL;

public class AntonGetICTReviews_assert {

    @Test
    private void Title() throws IOException, ParseException {

        // Динамическая строка - меняется название метода
        URL obj = new URL("http://anton.aevrika.ru/proxyapi/getICTReviews");

        JSONObject sendParams=new JSONObject();

        // Динамические строки - отправляемые параметры. Строки могут отсутсвовать, если ничего не отправляем.
        sendParams.put("testBoolean",new Boolean(true));
        sendParams.put("UseDefault",new Integer(0));
        sendParams.put("ItemCount",new Integer(3));
        sendParams.put("LastItemID","3dc3c662-8c2a-11e8-811a-00155da00e04");
        sendParams.put("DateFrom","2014-12-18T00:00:00");

        String str = SendRequest.sendHTTPRequest(sendParams, obj).toString();

        Object methodResponse = new JSONParser().parse(str);
        JSONObject jo = (JSONObject) methodResponse;

        // Динамические строки - парсим ответ
        JSONArray ReviewsArray = (JSONArray) jo.get("Reviews");
        JSONObject firstObjectElementReviews = (JSONObject) ReviewsArray.get(0);
        new assertType("ID", firstObjectElementReviews.get("ID"),"String");
        new assertType("Name", firstObjectElementReviews.get("Name"),"String");
        new assertType("Description", firstObjectElementReviews.get("Description"),"String");
        new assertType("Tags", firstObjectElementReviews.get("Tags"),"Array");
        new assertType("Period", firstObjectElementReviews.get("Period"),"String");
        new assertType("Link", firstObjectElementReviews.get("Link"),"String");
        JSONArray MailToArray = (JSONArray) firstObjectElementReviews.get("MailTo");
        JSONObject firstObjectElementMailTo = (JSONObject) MailToArray.get(0);
        new assertType("EMail", firstObjectElementMailTo.get("Email"),"String");
        new assertType("EMailTitle", firstObjectElementMailTo.get("EmailTitle"),"String");



        JSONArray Tags = (JSONArray) jo.get("Tags");
        JSONObject firstElementObjectTags = (JSONObject) Tags.get(0);
        new assertType("Name", firstElementObjectTags.get("Name"),"String");
        new assertType("Count", firstElementObjectTags.get("Count"),"Long");

        JSONObject parameters = (JSONObject) jo.get("Parameters");
        new assertType("DateFrom", parameters.get("DateFrom"),"Date");
        new assertType("ItemCount", parameters.get("ItemCount"),"Long");
        new assertType("UseDefault", parameters.get("UseDefault"),"Long");
        new assertType("testBoolean", parameters.get("testBoolean"),"Boolean");

        new assertType("TotalCount", jo.get("TotalCount1"),"Long");
    }
}