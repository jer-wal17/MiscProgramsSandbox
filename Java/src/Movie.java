public class Movie {
    private String Title;
    private String Released;
    private String Runtime;

    Movie() {
        Title = "";
        Released = "";
        Runtime = "";
    }

    Movie(String t, String rY, String r) {
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
