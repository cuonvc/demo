package com.example.demo.testClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClientRequest {

    public static void main(String[] args) {
        //get data
        List<String> res = getUsers();
        System.out.println(res);
        //post data

//        int number = 20;  //size of table users (start with 0)
//        Random random = new Random();
//        int numRand = random.nextInt(number);  //user id
//        String usename = "";  //get from user id
//        String password = ""; //get from user id
//        loginAccount(usename, password);

    }

    private static List<String> getUsers() {
        try {
            URL url = new URL("https://nvc-rest-blog.herokuapp.com/api/v1/profiles/role/2");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            String accessToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjdW9uZ2FkbWlubnZjIiwiaWF0IjoxNjYyNDMxMzA5LCJleHAiOjE2NjMwNzIxMDl9.O8eQ-nRqEWEDp0ODyh7HB0FimUV8Sg42iJjEYkP0qVztdARizSR9ELM6P7De0TDX9BHdxIsuhkLKuYzg2JarcQ";
            connection.setRequestProperty("Authorization", accessToken);

            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed error code: " + connection.getResponseCode());
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = bufferedReader.readLine()) != null) {
                System.out.println(output);
                JSONObject jsonObject = new JSONObject(output);
                JSONArray jsonArray = new JSONArray(jsonObject.get("content").toString());
                return IntStream.range(0, jsonArray.length())
                        .mapToObj(index -> ((JSONObject) jsonArray.get(index)).optString("id"))
                        .collect(Collectors.toList());

            }

            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
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
