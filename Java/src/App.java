import java.io.*;
import java.net.*;
import java.util.Scanner;

import com.google.gson.Gson;

public class App {
    public static void main(String[] args) throws Exception {

        Gson gson = new Gson();
        String keepInputLine = "";

        Scanner keyboard = new Scanner(System.in);
        String userMovie = "";

        System.out.print("Enter Movie Title: ");
        userMovie = keyboard.nextLine();
        System.out.println(userMovie);

        while (!userMovie.equalsIgnoreCase("q")) {
            userMovie = userMovie.toLowerCase();
            userMovie = userMovie.replaceAll(" ", "+");
            System.out.println(userMovie);

            try {
                URL myURL = new URL("http://www.omdbapi.com/?apikey=fad4d002&type=movie&t=" + userMovie);
                HttpURLConnection myURLConnection = (HttpURLConnection) myURL.openConnection();
                myURLConnection.connect();
                BufferedReader in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));

                String inputLine = "";
                while ((inputLine = in.readLine()) != null) {
                    keepInputLine += inputLine;
                }
                in.close();
                myURLConnection.disconnect();
            } catch (MalformedURLException e) {
                System.out.println("Malformed URL");
            } catch (IOException e) {
                System.out.println("IO Exception");
            }

            System.out.println(keepInputLine);
            Movie TOJS = gson.fromJson(keepInputLine, Movie.class);
            System.out.println(TOJS);
            keepInputLine = "";
            System.out.println(userMovie);
            System.out.print("Enter Movie Title: ");
            userMovie = keyboard.nextLine();
            System.out.println(userMovie);
        }

        keyboard.close();
    }
}
