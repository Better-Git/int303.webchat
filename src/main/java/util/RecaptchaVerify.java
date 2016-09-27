package util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

public class RecaptchaVerify {

    public static final String url = "https://www.google.com/recaptcha/api/siteverify";
    public static final String secretKey = "6LcSyAcUAAAAAMajbMZDg-XKnlM9veKCuD9iQvL7";

    public static boolean verify(String gRecaptchaResponse) throws IOException {
        if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
            return false;
        }

        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String ip = inetAddress.getHostAddress();
            URL obj = new URL(url + "?secret=" + secretKey + "&response=" + gRecaptchaResponse + "&remoteip=" + ip);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            // Set request header
            con.setRequestMethod("GET");
//            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//            String postParams = "?secret=" + secretKey + "&response=" + gRecaptchaResponse + "&remoteip=" + ip;

            // Send post request
//            con.setDoOutput(true);
//            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
//                wr.writeBytes(postParams);
//                wr.flush();
//            }

            // Get post response
            StringBuffer response;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String line;
                response = new StringBuffer();
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
            }

            // Convert response into JSON Object and return 'success' value
            JsonObject jsonObject;
            try (
                JsonReader jsonReader = Json.createReader(new StringReader(response.toString()))) {
                jsonObject = jsonReader.readObject();
            }
            return jsonObject.getBoolean("success");
        } catch (Exception e) {
            return false;
        }
    }
}
