package evrika;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AntonSortOrder {

    @Test
    private void Title() throws IOException, ParseException {
        String testMethod;

        String SortField, SortOrderString = null, getTypeString;
        Long SortOrderLong;
        Class getType;

        List<String> method=new ArrayList<String>();
        method.add("getDataCenterMigration");
        method.add("getICTReviews");
        method.add("getContracts");
        method.add("getExecutors");
        method.add("getKeyIndicators");
        method.add("getRequests2");
        method.add("getSystems");
        method.add("getEvents");

        for (int i=0; i<method.size(); i++ ) {
        // Запрос метода
            testMethod="https://cfc.mos.ru/proxyapi/"+method.get(i);
            URL obj = new URL(testMethod);

            JSONObject sendParams = new JSONObject();
            sendParams.put("UseDefault", 1);

            String str = SendRequest.sendHTTPRequest(sendParams, obj).toString();

            Object methodResponse = new JSONParser().parse(str);
            JSONObject jo = (JSONObject) methodResponse;

            // Парсим ответ
            JSONObject ParametersObject = (JSONObject) jo.get("Parameters");
            SortField = (String) ParametersObject.get("SortField");
            getType = ParametersObject.get("SortOrder").getClass();

            getTypeString = String.valueOf(getType);

            if (getTypeString.equals("class java.lang.String")) {
                SortOrderString = (String) ParametersObject.get("SortOrder");
                if ((!SortOrderString.equals("0")) && (!SortOrderString.equals("1"))) {
                    System.out.println(method.get(i) + " - пустое поле направления сортировки");
                } else { System.out.println(method.get(i)+" SortOrder: "+SortOrderString); }
            }
            if (getTypeString.equals("class java.lang.Long")) {
                SortOrderLong = (Long) ParametersObject.get("SortOrder");
                if ((SortOrderLong != 0) && (SortOrderLong !=1)) {
                    System.out.println(method.get(i) + " - пустое поле направления сортировки");
                } else {System.out.println(method.get(i)+" SortOrder: "+SortOrderLong);}
            }

            if (SortField.length() == 0) {
                System.out.println(method.get(i) + " - пустое поле сортировки");
            }


            System.out.println(method.get(i)+" SortField: "+SortField);
        }
    }
}