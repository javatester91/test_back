package evrika;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

public class AntongetDataCenterMigration {

    @Test
    private void Title() throws IOException, ParseException {

        // Динамическая строка - менятся название метода
        URL obj = new URL("http://anton.aevrika.ru/proxyapi/getDataCenterMigration");

        JSONObject sendParams=new JSONObject();
        sendParams.put("UseDefault",new Integer(0));
        sendParams.put("ItemCount",new Integer(3));

        String str = SendRequest.sendHTTPRequest(sendParams, obj).toString();

        Object methodResponse = new JSONParser().parse(str);
        JSONObject jo = (JSONObject) methodResponse;

        System.out.println(jo);

        // Динамические строки - парсим ответ
        JSONArray StagesArray = (JSONArray) jo.get("Stages");
        JSONObject firstObjectElementReviews = (JSONObject) StagesArray.get(0);
        new assertType("Name", firstObjectElementReviews.get("Name"),"String");
        new assertType("ShortName", firstObjectElementReviews.get("ShortName"),"String");
        new assertType("ID", firstObjectElementReviews.get("ID"),"String");

        JSONArray DataArray = (JSONArray) jo.get("Data");
        JSONObject firstObjectElementData = (JSONObject) DataArray.get(0);
        new assertType("Name", firstObjectElementData.get("Name"),"String");
        JSONObject ClusterObject = (JSONObject) firstObjectElementData.get("Cluster");
        new assertType("Name", ClusterObject.get("Name"),"String");
        new assertType("ColorTheme", ClusterObject.get("ColorTheme"),"String");
        JSONObject ProductObject = (JSONObject) firstObjectElementData.get("Product");
        new assertType("Name", ProductObject.get("Name"),"String");
        new assertType("Icon", ProductObject.get("Icon"),"String");
        new assertType("ID", ProductObject.get("ID"),"String");
        new assertType("URL", ProductObject.get("URL"),"String");
        JSONObject CuratorObject = (JSONObject) firstObjectElementData.get("Curator");
        new assertType("Name", CuratorObject.get("Name"),"String");
        new assertType("URL", CuratorObject.get("URL"),"String");
        JSONArray StagesArray1 = (JSONArray) firstObjectElementData.get("Stages");
        JSONObject firstObjectElementStagesArray1 = (JSONObject) StagesArray1.get(0);
        new assertType("ID", firstObjectElementStagesArray1.get("ID"),"String");
        new assertType("State", firstObjectElementStagesArray1.get("State"),"Boolean");
        new assertType("PlanDate", firstObjectElementStagesArray1.get("PlanDate"),"Date");
        new assertType("FactDate", firstObjectElementStagesArray1.get("FactDate"),"Date");
        new assertType("Location", firstObjectElementStagesArray1.get("Location"),"String");
        new assertType("Comment", firstObjectElementStagesArray1.get("Comment"),"String");

        JSONObject ParametersObject = (JSONObject) jo.get("Parameters");
        Long ItemCount = (Long) ParametersObject.get("ItemCount");
        System.out.println(ParametersObject);
        System.out.println(ItemCount);
    }
}