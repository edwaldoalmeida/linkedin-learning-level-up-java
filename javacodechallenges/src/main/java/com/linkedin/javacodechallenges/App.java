package com.linkedin.javacodechallenges;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App {

    public static StringBuilder getJoke (String url) {
        StringBuilder response = new StringBuilder();
        try {
            URL _url = new URL(url);
            HttpURLConnection con = (HttpURLConnection) _url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            // Reading response from the API
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Something went wrong when trying to get a joke: " + e.getMessage());
        }
        return response;
    }

    public static Joke parseJoke(StringBuilder responseBody) {
//        JSONObject jsonObject = new JSONObject(response.toString());  // usual way of doing .toString()
        JSONObject jsonObject = new JSONObject(String.valueOf(responseBody)); // another way of doing .toString()
        String id = jsonObject.getString("id");
        String joke = jsonObject.getString("joke");
        int status = jsonObject.getInt("status");
        Joke _jokeObj = new Joke(jsonObject.getString("id"), jsonObject.getString("joke"), jsonObject.getInt("status"));
        return _jokeObj;
    }

    public static void main(String[] args) {
        // TODO: Call https://icanhazdadjoke.com/ API and display joke

        StringBuilder response = getJoke("https://icanhazdadjoke.com/");
        Joke jokeObj = parseJoke(response);
        System.out.println(jokeObj);

    }
}
