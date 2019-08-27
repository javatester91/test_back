package evrika;

import org.json.simple.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendRequest {
    public static StringBuffer sendHTTPRequest(JSONObject sendParams, URL obj) throws IOException {
        StringBuffer response = null;
        String json, inputLine;

        json=sendParams.toString();

        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(5000);
        connection.setRequestProperty("Cookie", "_ym_uid=15432520491065901984; mos_id=CllGxlv8KFIGclf2la8lAgA=; id_login=6ebe61cf-dd32-472f-bb4c-c40265730ff2; _ga=GA1.2.1639365559.1551548816; _ym_d=1561379384; cw");
        // connection.setRequestProperty("Authorization", "Basic dXNlcjpxd2Vhc2R6eGM=");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);
        connection.setDoInput(true);

        // For POST only - START
        OutputStream os = connection.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = connection.getResponseCode();
        // System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {

                response.append(inputLine + "\n");
            }
            in.close();


        } else {
            System.out.println("POST request not worked");
        }
        return response;
    }
}
