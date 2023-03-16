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
    public static void main(String[] args) {
        // TODO: Call https://icanhazdadjoke.com/ API and display joke

        try {
            URL url = new URL("https://icanhazdadjoke.com/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Set the Accept header to "application/json"
            con.setRequestProperty("Accept", "application/json");

            // Reading response from the API
//            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parsing JSON response
//            JSONObject jsonObject = new JSONObject(response.toString());
            JSONObject jsonObject = new JSONObject(String.valueOf(response)); // another way of doing .toString()
            String id = jsonObject.getString("id");
            String joke = jsonObject.getString("joke");
            int status = jsonObject.getInt("status");

            // Printing parsed values
            System.out.println("ID: " + id);
            System.out.println("Joke: " + joke);
            System.out.println("Status: " + status);
        } catch (Exception e) {
            System.out.println("Error fetching API: " + e.getMessage());
        }
    }
}
