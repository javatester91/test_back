package evrika;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

public class AntongetDataCenterMigrationFilters {

    @Test
    private void Title() throws IOException, ParseException {

        // Динамическая строка - менятся название метода
        URL obj = new URL("http://anton.aevrika.ru/proxyapi/getDataCenterMigrationFilters");

        JSONObject sendParams=new JSONObject();
        String str = SendRequest.sendHTTPRequest(sendParams, obj).toString();

        Object methodResponse = new JSONParser().parse(str);
        JSONObject jo = (JSONObject) methodResponse;

        System.out.println(jo);

        // Динамические строки - парсим ответ
        JSONArray ProductsArray = (JSONArray) jo.get("Products");
        JSONObject firstObjectElementReviews = (JSONObject) ProductsArray.get(0);
        new assertType("Type", firstObjectElementReviews.get("Type"),"String");
        new assertType("Name", firstObjectElementReviews.get("Name"),"String");
        new assertType("ShortName", firstObjectElementReviews.get("ShortName"),"String");
        new assertType("ID", firstObjectElementReviews.get("ID"),"String");
        new assertType("Icon", firstObjectElementReviews.get("Icon"),"String");
        new assertType("Order", firstObjectElementReviews.get("Order"),"String");

        JSONObject ClusterObject = (JSONObject) firstObjectElementReviews.get("Cluster");
        new assertType("Name", ClusterObject.get("Name"),"String");
        new assertType("ColorTheme", ClusterObject.get("ColorTheme"),"String");


        JSONArray breakDeadLinesArray = (JSONArray) jo.get("BreakDeadLines");
        JSONObject firstElementObjectBreakDeadLines = (JSONObject) breakDeadLinesArray.get(0);
        new assertType("Name", firstElementObjectBreakDeadLines.get("Name"),"String");
        new assertType("ID", firstElementObjectBreakDeadLines.get("ID"),"String");

        JSONArray locationsArray = (JSONArray) jo.get("Locations");
        JSONObject firstElementObjectlocations = (JSONObject) locationsArray.get(0);
        new assertType("Name", firstElementObjectlocations.get("Name"),"String");
        new assertType("ID", firstElementObjectlocations.get("ID"),"String");

    }
}