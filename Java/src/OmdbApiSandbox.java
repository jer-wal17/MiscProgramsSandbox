import java.io.*;
import java.net.*;
import java.util.Scanner;

import com.google.gson.Gson;

public class OmdbApiSandbox {
    public static void main(String[] args) throws Exception {

        Gson gson = new Gson();
        String keepInputLine = "";

        Scanner keyboard = new Scanner(System.in);
        String userMovie = "";

        System.out.print("Enter Movie Title (Q/q to quit): ");
        userMovie = keyboard.nextLine();

        while (!userMovie.equalsIgnoreCase("q")) {
            userMovie = userMovie.toLowerCase();
            userMovie = userMovie.replaceAll(" ", "+");

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

            MyMovie TOJS = gson.fromJson(keepInputLine, MyMovie.class);
            System.out.println("Movie Info: " + TOJS);
            keepInputLine = "";
            System.out.print("Enter Movie Title (Q/q to quit): ");
            userMovie = keyboard.nextLine();
        }

        keyboard.close();
    }
}

class MyMovie {
    private String Title;
    private String Released;
    private String Runtime;

    MyMovie() {
        Title = "";
        Released = "";
        Runtime = "";
    }

    MyMovie(String t, String rY, String r) {
        Title = t;
        Released = rY;
        r = Runtime;
    }

    @Override
    public String toString() {
        String outString = "Title: " + Title + "; ";
        outString += "Released: " + Released + "; ";
        outString += "Runtime: " + Runtime + ";";

        return outString;
    }
}
