package com.example.demo.testClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ClientRequest {

    public static void main(String[] args) {
        //get data

//        try {
//            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Accept", "application/json");
//
//            if (connection.getResponseCode() != 200) {
//                throw new RuntimeException("Failed error code: " + connection.getResponseCode());
//            }
//
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//            String output;
//            System.out.println("Output from Server .... \n");
//            while ((output = bufferedReader.readLine()) != null) {
//                System.out.println(output);
//            }
//
//            connection.disconnect();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }

        //post data
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"title\":\"Test title1\",\"body\":\"Test body2\",\"userId\":1}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = bufferedReader.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
