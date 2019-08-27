package evrika;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AntonItemCount {

    @Test
    private void Title() throws IOException, ParseException {
        String testMethod;

        String ItemCountString,getTypeString;
        Long ItemCountLong, count;
        Class getType;

        List<String> method=new ArrayList<String>();
        method.add("getDataCenterMigration");

        count = 3l;

        for (int i=0; i<method.size(); i++ ) {
        // Запрос метода
            testMethod="http://anton.aevrika.ru/proxyapi/"+method.get(i);
            URL obj = new URL(testMethod);

            JSONObject sendParams = new JSONObject();
            sendParams.put("UseDefault", 1);
            sendParams.put("ItemCount", count);

            String str = SendRequest.sendHTTPRequest(sendParams, obj).toString();

            Object methodResponse = new JSONParser().parse(str);
            JSONObject jo = (JSONObject) methodResponse;

            // Парсим ответ
            JSONObject ParametersObject = (JSONObject) jo.get("Parameters");
            getType = ParametersObject.get("ItemCount").getClass();

            getTypeString = String.valueOf(getType);

            if (getTypeString.equals("class java.lang.String")) {
                ItemCountString = (String) ParametersObject.get("ItemCount");
                if (!ItemCountString.equals("3")) {
                    System.out.println(method.get(i) + " - не сохранятся количество элементов подгрузки");
                } else {System.out.println(method.get(i)+" ItemCount: "+ItemCountString);}
            }
            if (getTypeString.equals("class java.lang.Long")) {
                ItemCountLong = (Long) ParametersObject.get("ItemCount");
                if (ItemCountLong !=count) {
                    System.out.println(method.get(i) + " - не сохранятся количество элементов подгрузки");
                } else {System.out.println(method.get(i)+" ItemCount: "+ItemCountLong);}
            }

            JSONArray dataArray = (JSONArray) jo.get("Data");
            if (dataArray.size() != count) {
                System.out.println("Неправильное количество элементов в подгрузке. Ожидали "
                    +count+" , а пришло - "+ dataArray.size());}
        }
    }
}
