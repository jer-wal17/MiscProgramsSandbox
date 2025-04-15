import java.io.IOException;
import java.util.logging.*;

public class Logging {
    private static Logger logger = Logger.getLogger(Class.class.getName());

    public static void main(String[] args) {
        FileHandler fh = null;
        try {
            fh = new FileHandler("mylog.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
        logger.setLevel(Level.FINE);
        logger.fine("Doing stuff");
        logger.info("Doing other stuff");
        fh.close();
    }
}
