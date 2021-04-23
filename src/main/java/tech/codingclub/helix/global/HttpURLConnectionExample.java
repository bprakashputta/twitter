package tech.codingclub.helix.global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionExample {
    private static  String USER_AGENT = "Mozilla/5.0";

    //Send HTTP REQUEST
    public static String sendGet(String urlStr) throws IOException {

        StringBuilder result = new StringBuilder();
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while((line = reader.readLine())!=null){
            result.append(line);
        }
        reader.close();
        return result.toString();
    }

    //Test Method is working or not
    //public static void main(String[] args) throws IOException {
    //    System.out.println(sendGet("https://codingclub.tech/test-get-request?name=BhanuPrakash"));
    //}
}

