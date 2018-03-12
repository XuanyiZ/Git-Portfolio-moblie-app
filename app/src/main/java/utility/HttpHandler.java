package utility;
/**
 * Created by Administrator on 2018/3/11.
 * citation: https://stackoverflow.com/questions/13196234/simple-parse-json-from-url-on-android-and-display-in-listview/13196301#13196301
 * http://www.vetbossel.in/android-json-parsing-from-url/
 * https://stackoverflow.com/questions/13784825/how-can-i-parse-a-json-object-and-display-it-in-a-list-view
 * https://stackoverflow.com/questions/36221795/android-parse-json-data-from-a-web-server-and-display-on-listview
 */
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import android.util.Log;



public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }

    /**
     * interact with api from a url, receive a string as output
     */
    public String makeServiceCall(String reqUrl, String method) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }


    /**
     * helper function for makeServiceCall
     * convert/parse the input steam format to string format
     */
    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
                                                        }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
                                      }
        }
        return sb.toString();
    }
}

