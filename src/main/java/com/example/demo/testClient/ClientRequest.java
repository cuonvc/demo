package com.example.demo.testClient;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Random;

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

        int number = 20;  //size of table users (start with 0)
        Random random = new Random();
        int numRand = random.nextInt(number);  //user id
        String usename = "";  //get from user id
        String password = ""; //get from user id
        loginAccount(usename, password);

    }

    private static void loginAccount(String username, String password) {
        try {
            URL url = new URL("https://nvc-rest-blog.herokuapp.com/api/v1/auth/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject newJson = new JSONObject();
            newJson.put("usernameOrEmail", username);
            newJson.put("password", password);

            String stringJson = newJson.toString();

//            String input = "{\"usernameOrEmail\":\"cuongadminnvc\",\"password\":\"cuongnvc\"}";

            OutputStream os = conn.getOutputStream();
            os.write(stringJson.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = bufferedReader.readLine()) != null) {
                System.out.println(output);
                JSONObject jsonObject = new JSONObject(output);
//                System.out.println(jsonObject.get("accessToken").toString());
                createPost(jsonObject.get("accessToken").toString());
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

    private static void createPost(String token) {
        try {
            URL url = new URL("https://nvc-rest-blog.herokuapp.com/api/v1/category/4/post");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String accessToken = "Bearer " + token;
            conn.setRequestProperty("Authorization", accessToken);


            String input = "{\"title\":\"Test title1\",\"description\":\"Test description\",\"content\":\"test content\"}";

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
